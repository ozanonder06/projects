/*Author: Ozan Gazi Onder
 *This program take t32-bit virtual address as command line argument
 *and calaculates the corresponding page number and offset for a systen with 4-KB page
 */

#include<stdio.h>
#include<stdlib.h>
#include<math.h>
#define PAGE_SIZE 4096  // 4KB PAGE = 2^12 BYTES

//calculates the offset for a given address in decimal
int getOffset(int decimalAddress)
{
    return (int)(decimalAddress % PAGE_SIZE);
}

//calculates the page number and returns
int getPage(int decimalAddress)
{
    return (int)(decimalAddress / PAGE_SIZE);
}


//Main program
int main ( int argc, char *argv[] )
{
    
    
    
    
    //read the value from command line
    int value;
    value = atoi(argv[1]);
    
    //calculate the page number and offset
    int pageNumber = getPage(value);
    int offset = getOffset(value);
    
    
    printf("Corresponding Page Number %d\n", pageNumber);
    printf("Offset %d\n", offset);
    //printf(offset);
    exit(1);
    
}

