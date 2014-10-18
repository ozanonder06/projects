//Ozan Gazi Onder
linux version of homework2
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#define MYNAME "Ozan Onder" //defined string constant
#define BUFFERSIZE 512 //defined buffer constant

int main(int argc, char *argv[]) {
	int i; //for sprintf usage of bytes
	int fileD, copyD; //open file, copy file descriptor
	int nbr,nbw; //number of bytes read/written
	int writeD; //write file descriptor
	int totalbytes = 0;
	char buffer[BUFFERSIZE]; //buffer for read/write files

	//i = sprintf(buffer, "Welcome to the File Copy Program by %s!\n", MYNAME);

	//printf("Enter the name of the file to copy from:\n");
	//scanf("%s", &fnb[0]);
	//printf("Enter the name of the file to copy to:\n");
	//scanf("%s", &ftw[0]);

	fileD = open(argv[1], O_RDONLY);
	if(fileD < 0) {
		perror("file could not be opened!\n");
		exit(-1);
	}
	//copyD = open(ftw, O_CREAT|O_EXCL, S_IRWXU);
	copyD = creat(argv[2], S_IRWXU);
	//user, file owner has read, write and execute permission
	//
	if(copyD < 0) {
		perror("file could not be created!\n");
		exit(-1);
	}
	do { //logic similar to demo code for hw2
		nbr = read(fileD, argv[1], BUFFERSIZE);
		if(nbr < 0) {
			perror("file could not be read!\n");
			exit(-1);
		}
		nbw = write(copyD, argv[1] , nbr);
		if(nbw < 0) {
			perror("file coult not be written correctly!\n");
			exit(-1);
		}
		totalbytes += nbw; //keeping track of bytes copied
		//write(1, fnb, nbr); // for debug purposes
		//printf("\n");
	} while (nbr > 0);
	printf("File Copy Successful, %d bytes copied\n", totalbytes);
return 0;
}