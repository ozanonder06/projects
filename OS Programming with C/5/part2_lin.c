# include <stdio.h>
# include <pthread.h>

int BufferSize;// buffer size
void *Producer_Function();
void *Consumer_Function();

int BufferIndex=0;
int sleepTime = 10;

char *BUFFER;

pthread_cond_t Buffer_Not_Full=PTHREAD_COND_INITIALIZER;
pthread_cond_t Buffer_Not_Empty=PTHREAD_COND_INITIALIZER;
pthread_mutex_t mVar=PTHREAD_MUTEX_INITIALIZER;


//Takes 2 command line arguments
// first -> the buffer size
// second -> sleepTime(amount of time after each cons.produc. process)
int main(int argc, char* argv[])
{  
    if(argc != 3)
	{
	    //if command line arguments are not 2, exit
		printf("command line arguement has to be 2\n");
		exit(-1);
	}
	
	BufferSize = atoi(argv[1]);
	
	sleepTime = atoi(argv[2]);
	
	

    pthread_t ptid,ctid;//thread for consumer and proucers
    
    BUFFER=(char *) malloc(sizeof(char) * BufferSize);            
    
    pthread_create(&ptid,NULL,Producer_Function,NULL);
    pthread_create(&ctid,NULL,Consumer_Function,NULL);
    
    pthread_join(ptid,NULL);
    pthread_join(ctid,NULL);
        
    
    return 0;
}

void *Producer_Function()
{    
    while(1)
    {
        pthread_mutex_lock(&mVar);
        if(BufferIndex==BufferSize)
        {                        
            pthread_cond_wait(&Buffer_Not_Full,&mVar);
        }                        
        BUFFER[BufferIndex]='@';
        printf("Producer number %d produces \n",BufferIndex);
        pthread_mutex_unlock(&mVar);
        pthread_cond_signal(&Buffer_Not_Empty);   
		BufferIndex++;     
		sleep(sleepTime);
    }    
    
}

void *Consumer_Function()
{
    while(1)
    {
        pthread_mutex_lock(&mVar);
        if(BufferIndex==-1)
        {            
            pthread_cond_wait(&Buffer_Not_Empty,&mVar);
        }                
        printf("Consume : %d \n",BufferIndex--);        
        pthread_mutex_unlock(&mVar);        
        pthread_cond_signal(&Buffer_Not_Full);       
	        
    }    
}

