//Ozan Gazi Onder
//Page replacement algorithm with FIFO
#include<stdio.h>
#include<stdlib.h>
#include<time.h>

int n = 50; //NUMBER OF REFERANCE STRINGS FROM pages(in our example pgaes is 16)
int pages = 16; //Number Of Pages
int NUMBER_OF_FRAMES = 3;
int page_fault=0;
int a[100];

int flag;//flag shows the availability
int frame[10];
int frame_Index;



main()
{
  int i;
  //generate a reference
  //string of n random page references
  //in this program n=100
  srand ( time(NULL) );
    for(i=1;i<=n;i++) { a[i] = rand() % pages + 1; }
    
//INITIALIZE THE FRAMES to -1
for(i=0;i<NUMBER_OF_FRAMES;i++) {frame[i]= -1;}
int j=0;

printf("REFERENCE STRING \t PAGE FRAMES\n");
for(i=1;i<=n;i++)
{
    printf("%d\t\t",a[i]);
    flag=0;
    for(frame_Index=0;frame_Index<NUMBER_OF_FRAMES;frame_Index++)
       if(frame[frame_Index]==a[i])
       {
            flag=1;
       }
       else if (flag==0) //Page fault occurs
       {
            frame[j]=a[i];
            j=(j+1)%NUMBER_OF_FRAMES;
            page_fault++;
            for(frame_Index=0;frame_Index<NUMBER_OF_FRAMES;frame_Index++)
               printf("%d\t",frame[frame_Index]);
       }
        printf("\n");
}
printf("\nNumber of Page Fault:  %d",page_fault);

}
