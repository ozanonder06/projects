//Ozan Gazi onder
//windows version of homework 2
#include <stdio.h>
#include <windows.h>

int main(int argc,char *argv[])
{
HANDLE handleIn1,handleIn2;
HANDLE handleOut = GetStdHandle (STD_OUTPUT_HANDLE);
DWORD nIn=0;
DWORD nOut=0;
DWORD countbyte = 0;
char* buf;
int size = 512;

buf = (char*) HeapAlloc (GetProcessHeap(),0,size);
if(buf == NULL)
{
printf ("Error!");
return 1;
}

printf ("\nWelcome to File Copy by Ozan!\n");
printf ("Enter the name of the source file:\n");
handleIn1 = CreateFile (argv[1], GENERIC_READ,
		FILE_SHARE_READ, NULL, OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, NULL);
handleIn2 = CreateFile (argv[2], GENERIC_WRITE,
		FILE_SHARE_WRITE, NULL, CREATE_NEW, FILE_ATTRIBUTE_NORMAL, NULL);
if ((handleIn1 == INVALID_HANDLE_VALUE) || (handleIn2 == INVALID_HANDLE_VALUE))
{
printf("I can't open the file!");
return 2;
}
while (ReadFile (handleIn1, buf, size, &nIn, NULL) && nIn > 0)
{
	WriteFile(handleIn2, buf, nIn, &nOut, NULL);
	if(nIn != nOut)
	{
		printf("contents is not copied, Error!");
		return 4;
	}
	countbyte += nOut;
}
printf("File Copy was successful, with %d bytes copied.\n", countbyte);
CloseHandle(handleIn1);
CloseHandle(handleOut);
}
