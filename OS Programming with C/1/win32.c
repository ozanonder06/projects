
#include<windows.h>
#include<stdio.h>
 
#define NAME "ozan onder"
 
/*
  * Main application entry point
  */
int main()
{
     char *buffer;
     buffer = (char *) calloc(64, sizeof(char));
     int size, bytesWritten;
 
     size = sprintf(buffer, "Hello 415, it's me %s!\n", NAME);
 
     WriteFile(STD_OUTPUT_HANDLE, buffer, size, &bytesWritten, NULL);
 
 
 
     return 0;
}
