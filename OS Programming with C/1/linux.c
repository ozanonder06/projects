
 
#include <unistd.h>
#include <stdio.h>
#define name "ozan onder"
int main()
{
	char text[60];
	sprintf(text, "Hello 415, it's me %s", name);

	write(1, text, 60);

	return 0;
}
