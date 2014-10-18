/*Author: Ozan Gazi Onder(WINDOWS VERSION)
 *This program take the name of a text file as command line argument
 *creates the desired number of threads, and computes the nunber of words
 *in an array with specified buffer size which contains each charater in the text file
 *There are two different counters for this process. Local Counters and global counters
 *they are n and eachCount[] respectively. IN the local count each thread calculates the number of the words  
 *and store them in the each index of the array and at the end each array index contents are added and displayed to the user

 */

#include<stdio.h>
#include<stdlib.h>
#include<windows.h>
#include <tchar.h>

//#include<string.h>
#define MAX 50000 //BUFFER LIMIT
#define THREADCOUNT 10  // NUMBER OF THREADS
/*Reads file as command line argument and stores each character
 *to an array of chars with specified buffer size*/

int sum=0;
int x=0;
static int n = 0; 
int offset=MAX/THREADCOUNT;//number of indexes that each thread has to process
int start=0;//start index of each thread
int finish=0;//finish index of each thread
int index;
int *arr;
HANDLE thread;
int countIndex = 0;
static int eachCount[THREADCOUNT];//LOCAL COUNTER FOR EACH ARRAY INDEX

   struct thread_args
   {
    int *myArray;
    int begin;
    int end;
   };
   
int character(int c)
{
	if(c>=48 && c<=57) return 1;
	else if (c>=65 && c<=90) return 1;
	else if (c>=97 && c<=122) return 1;
	else return 0;
}

DWORD WINAPI process(void* ptr)
{
        eachCount[countIndex]=0;
	struct thread_args *args = ptr;
	int in = args->begin;//get begin variable of the struct as index
    int end = args->end;//get end varibale of struct as end limit of the partition process
	int indicator = 0;
	char *msg;
	msg = (char *) args->myArray;

	while(in<end)//scan as far as buffer size
	{

       if((msg[in]==' ') && indicator)
	   {
	         n++;
             eachCount[countIndex]++;
		 indicator=0;
	   }

	   if(character(msg[in]))
	   {
		  indicator=1;
		  	 
	   }
	   in++;
	
	}
        countIndex++;
	
		 
}//end of function

 


   struct thread_args *args;

int main(int argc, char *argv[])
{

   int source[MAX];
  

   //opes the desired file and stores the characters to the array
   //then closes the file
   FILE *fp;
   int c;
   int i=0;

  fp = fopen(argv[1],"r");
   while(1)
   {
      c = fgetc(fp);
      if(feof(fp) )
      {
          break ;//breaks the while loop
      }
	 source[i]=c; i++;	
	 //putchar(source[i]);
   }
   fclose(fp);

   //now all the contents are in myArray we will process this
   arr = source;

  //create threads as many as the number of threadss
  //assign struct with specified begin and end indexes.
  //CREATE THREADS AND START PROCESSING
  

   for(index=0; index<THREADCOUNT; index++)
   {
         
		 start= finish;
		 finish= start+offset;

		  //instantiate the struct desired values
          args = malloc(sizeof *args);
          args->begin = start;
          args->end = finish;
          args->myArray = source;

          // Create the threads each of which will execute function */
	  thread = CreateThread(NULL, 0, process, args, 0, NULL);


   }

   //COUNT THE LOCAL VARIABLES
   for(x; x<THREADCOUNT; x++)
   {
      sum += eachCount[x];
   }


	 
	 printf("Number of Words in Global Counter: %d\n",n);
	  printf("Number of Words in Local Counter: %d\n",sum);
	 exit(1);

   return 0;
}

