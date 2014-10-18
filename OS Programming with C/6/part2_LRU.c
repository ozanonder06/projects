//Ozan Gazi Onder
//Page replacement algorithm with LRU
#include<stdio.h>
#include<stdlib.h>
#include<time.h>

int n = 30; //NUMBER Oframes REFERANCE STRINGS FROM pages(in our example pgaes is 16)
int pages = 16;
int a[100];//Reference strings
int frames = 3; //NUMBER OF FRAMES
int page_fault=0; //counter for number of faults
int index = 0;
int counter;

int queue[20];//queue to insert pages
int d,j,r,b[20],c2[20];

//swap
void swap(int  *first, int  *second)
{
  int t = *first;
  *first = *second;
  *second = t;
}

main()
{
 int i;
 //generate a reference
  //string of n random page references
  //in this program n=100
  srand ( time(NULL) );
    for(i=1;i<=n;i++) { a[i] = rand() % pages + 1; }

queue[index]=a[index];
printf("\n\t%d\n",queue[index]);

//initialize page fault
page_fault++;
index++;
for(i=1;i<n;i++)
{
    counter=0;
    for(j=0;j<frames;j++)
    {
        if(a[i]!=queue[j])
            counter++;
    }
    //if counter equals to number of frames 
    //replace the buffer into queue and display pages
    if(counter==frames)
    {
        page_fault++;
        if(index<frames)
        {
            queue[index]=a[i];
            index++;
            for(j=0;j<index;j++)
            printf("\t%d",queue[j]);
            printf("\n");
        }
        else //if the buffers value is not the same increment the counter array index
        {
            for(r=0;r<frames;r++)
            { 
                c2[r]=0;
                for(j=i-1;j<n;j--)
                {
                    if(queue[r]!=a[j]) c2[r]++;
                    else break;
                }
           }
           
           //Retrieve the least recently used page
		   // by counter value
           for(r=0;r<frames;r++) b[r]=c2[r];
           
           //Select the least recently used page by counter value
           for(r=0;r<frames;r++)
           {
               for(j=r;j<frames;j++)
                   if(b[r]<b[j]) swap(&b[r], &b[j]);
  
           }
           
           //According to the select,
           //stack the values of b in queue
          for(r=0;r<frames;r++)
          {
            if(c2[r]==b[0])
            queue[r]=a[i];
            printf("\t%d",queue[r]);
          }
          printf("\n");
        }
    }
}
//DISPLAY THE PAGE FAULT RESULTS
printf("\nNumber page faults: %d",page_fault);
}
