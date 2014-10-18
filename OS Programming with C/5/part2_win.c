
#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <time.h>
#include <math.h>

CRITICAL_SECTION critical_section;
HANDLE mReady;
HANDLE mutex;
HANDLE sem_full;
HANDLE sem_empty;

static int* buffer;
static int max_queue = 0; //max item count in buffer
static int in = 0;
static int out = 0;
static int pt = 0; //spin time for producers
static int ct = 0; //spin time for consumers
static int item_number = 0;



struct producer {
	HANDLE handle;
	DWORD ID; //thread ID
	int newID; 
	int NP; //product numbers
};

struct consumer {
	HANDLE handle;
	DWORD ID;
	int newID;
	int NC;//number_to_consume; //same logic as producer
};

//book logic and slides 
DWORD WINAPI producer_function(LPVOID param) {
	struct producer *producers = param;
	int item;
	int pt_local = pt;
	//EnterCriticalSection(&critical_section);
	WaitForSingleObject(sem_empty, INFINITE);
	WaitForSingleObject(mutex, INFINITE);
	while(producers->NP-- > 0) {
		while(pt_local > 0) {
			pt_local--;//busy waiting for pt
		}
		item = ++item_number;
		buffer[in] = item;
		in = (in + 1) % max_queue;
		printf("Producer number %d produces %d.\n", producers->newID, item);
	}
	ReleaseMutex(mutex);
	ReleaseSemaphore(sem_full, 1, NULL);

	return 0;
}

DWORD WINAPI consumer_function(LPVOID param) {
	struct consumer *consumers = param;
	int item;
	int ct_local = ct;
	WaitForSingleObject(sem_full, INFINITE);
	WaitForSingleObject(mutex, INFINITE);
	while(consumers->NC-- > 0) {
		while(ct_local > 0) {
			ct_local--;
		}
		
		item = buffer[out];
		out = (out + 1) % max_queue;
		printf("Consumer number %d consumes %d.\n", consumers->newID, item);
	}
	ReleaseMutex(mutex);
	ReleaseSemaphore(sem_empty, 1, NULL);
	return 0;
}


int main(int argc, char* argv[]) {
	int P; 
	int C; 
	int i;
	int X; 
	int NC; 
	struct producer *producers;
	struct consumer *consumers;
	struct timeval time;
	int retvalue;
	SYSTEMTIME st;


	if(argc != 7) { //if command line arguments are not 7, exit
		printf("command line arguement has to be 6\n");
		exit(-1);
	}
	
	//retrieving the data from the command line argumnets
	max_queue = atoi(argv[1]);
	P = atoi(argv[2]);
	C = atoi(argv[3]);
	X = atoi(argv[4]);
	pt = atoi(argv[5]);
	ct = atoi(argv[6]);
	NC = P*X/C;

	sem_full = CreateSemaphore(NULL, 0, max_queue, NULL);
	sem_empty = CreateSemaphore(NULL, max_queue, max_queue, NULL);


	buffer = calloc(max_queue,sizeof(int));
	producers = calloc(P,sizeof(struct producer));
	consumers = calloc(C,sizeof(struct consumer));//initiation

	GetLocalTime(&st);
	printf("current time: %02d:%02d\n", st.wHour, st.wMinute); 

	for (i = 0; i < P; i++) {
		producers[i].newID = i;//setting newID
		producers[i].NP = X;
		producers[i].handle = CreateThread(NULL,0,producer_function,&producers[i],0,&producers[i].ID);
		if (producers[i].handle == NULL){
			fprintf(stderr, "Cant create %d thread.\n", i );
			return -1;
		}     
    }

	for (i = 0; i < C; i++) {
		consumers[i].newID = i;
		consumers[i].NC = NC;
		consumers[i].handle = CreateThread(NULL,0,consumer_function,&consumers[i],0,&consumers[i].ID);
		if (consumers[i].handle == NULL){
			fprintf(stderr, "Cant create %d thread.\n", i );
			return -1;
		} 
    }
    

	for(i = 0; i < P; i++){
		WaitForSingleObject(producers[i].handle, INFINITE);
		CloseHandle(producers[i].handle);
	}
	for(i = 0; i < C; i++){
		WaitForSingleObject(consumers[i].handle, INFINITE);
		CloseHandle(consumers[i].handle);
	}

	//DeleteCriticalSection(&critical_section);
	//CloseHandle(mReady);
	
	return 0;
}
