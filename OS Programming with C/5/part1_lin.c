/*Author: Ozan Gazi Onder
 *This program take the name of a text file as command line argument
 *creates the desired number of threads, and computes the nunber of words
 *in an array with specified buffer size which contains each charater in the text file
 *There are two different counters for this process. Local Counters and global counters
 *they are n and eachCount[] respectively. IN the local count each thread calculates the number of the words  
 *and store them in the each index of the array and at the end each array index contents are added and displayed to the user

 */

#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>
//#include<string.h>
#define MAX 50000 //BUFFER SIZE
#define THREADCOUNT 10  // NUMBER OF THREADS

/*Reads file as command line argument and stores each character
 *to an array of chars with specified buffer size*/


static int n = 1; //WORD COUNTER VARIABLE
int countIndex = 0;
static int eachCount[THREADCOUNT];



   struct workArea
   {
    int *buffer;
    int initial;
    int final;
   };

//returns true if it is a valid character or digit
//it can be uppercase or lower case
//Other sysmbols such as &, *, ( are not considered valid!!
int character(int c)
{
	if(c>=48 && c<=57) return 1;
	else if (c>=65 && c<=90) return 1;
	else if (c>=97 && c<=122) return 1;
	else return 0;
}

//receives the struct with given parameters indicating the beginning and ending index of each thread
//to process the array then count words
void *countWord( void *ptr )
{
        eachCount[countIndex]=0;
        
        char *msg;
	struct workArea *args = ptr;
	int in = args->initial;
        int end = args->final;
        msg = (char *) args->buffer;
	int flag = 0;
	
	

	while(in<end)//scan as far as buffer size
	{
       if((msg[in]==' ') && flag)
	   {
	     n++;
             eachCount[countIndex]++;
		 flag=0;
	   }

	   if(character(msg[in]))
	   {
		  flag=1;
		 
	   }
	   in++;
	
	}//scanning and counting is complete
       countIndex++;
	
		 
}//end of function



int main(int argc, char *argv[])
{

   int buffer[MAX];
  

   //opes the desired file and stores the characters to the array
   //then closes the file
   FILE *fp;
   int c; int i=0;

   fp = fopen(argv[1],"r");
   while(1)
   {
      c = fgetc(fp);
      if(feof(fp) )
      {
          break ;//breaks the while loop
      }
	 buffer[i]=c; i++;	  
   }
   fclose(fp);

   //now all the contents are in myArray we will process this
  int *myArray = buffer;


   int begin=0;
   int end=0;
   int index;

   for(index=0; index<THREADCOUNT; index++)
   {
         pthread_t this_thread;
		 int  iret;
		 begin= end;
		 end= begin+(MAX/THREADCOUNT);

		  //instantiate the struct desired values
          struct workArea *args = malloc(sizeof *args);
          args->initial = begin;
          args->final = end;
          args->buffer = buffer;

		 
          iret = pthread_create( &this_thread, NULL, countWord, (void*) args);
          pthread_join(this_thread, NULL);

   }

         int sum=0;
         int x=0;
         for(x; x<THREADCOUNT; x++)
         {
            sum += eachCount[x];
         }

	 //display the result
	 printf("Number of Words in the global Counter: %d\n",n);
         printf("Number of Words in the local Counter: %d\n",sum);
        
	 exit(1);

   return 0;
}






