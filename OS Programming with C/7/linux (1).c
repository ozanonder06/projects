//Extended shell program
//CSC 415 homework 7
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>

#define INPUT_BUF 200 //INPUT SIZE


//FUNCTION PROTOTYPES

//depending on the case, the program runs thje necessary operation
//cases 1> nomal
//1 -> st output direction 2-> st input direction
//3 -> simgle shell pipe execution
// 4 -> runs the process in the background with &
void execute_command(char **, int, char **);


//parses arguments from the user command prompt
//by scanning the input array
int parse_arguments(char *, char **, char **, int *);

//skips the empty line
//tab and new line characters
void chop(char *);
typedef void (*sighandler_t)(int);



int main(int argc, char *argv[])
{
	int i, command = 0, arguments;
	size_t len = INPUT_BUF;
	char *cpt, *inputString, *cmdArgv[INPUT_BUF], *supp = NULL;
	inputString = (char*)malloc(sizeof(char)*INPUT_BUF);
	
	char curDir[100];
	
	while(1)
	{
		command = 0;
		getcwd(curDir, 100);
		printf("MyShell>");
		getline( &inputString, &len, stdin);
		if(strcmp(inputString, "exit\n") == 0)
			exit(0);
		arguments = parse_arguments(inputString, cmdArgv, &supp, &command);
		if(strcmp(*cmdArgv, "cd") == 0)
		{
			chdir(cmdArgv[1]);
		}
		else 
			execute_command(cmdArgv, command, &supp);
	}
	return 0;
}



int parse_arguments(char *inputString, char *cmdArgv[], char **sPtr, int *commandPtr)
{
	int arguments = 0, terminate = 0;
	char *srcPtr = inputString;
	//PARSE ARGUMENTS
	while(*srcPtr != '\0' && terminate == 0)
	{
		arguments++;
		*cmdArgv = srcPtr;
		while(*srcPtr != ' ' && *srcPtr != '\t' && *srcPtr != '\0' && *srcPtr != '\n' && terminate == 0)
		{
			switch(*srcPtr)
			{
                //RUNNING PROCESS IN THE BACKGROUND
				case '&':
					*commandPtr = 4;
					break;
					//STANDADR OUTPUT IMPLEMENTATION
				case '>':
					*commandPtr = 1; 
					*cmdArgv = '\0';
					srcPtr++;
					if(*srcPtr == '>')
					{
						*commandPtr = 5;
						srcPtr++;
					}
					while(*srcPtr == ' ' || *srcPtr == '\t')
						srcPtr++;
					*sPtr = srcPtr;
					chop(*sPtr);
					terminate = 1;
					break;
				//STANDADR input IMPLEMENTATION	
				case '<':
					*commandPtr = 2;
					*cmdArgv = '\0';
					srcPtr++;
					while(*srcPtr == ' ' || *srcPtr == '\t')
						srcPtr++;
					*sPtr = srcPtr;
					chop(*sPtr);
					terminate = 1;
					break;
					//PIPE EXECUTION
				case '|':
					*commandPtr = 3;
					*cmdArgv = '\0';
					srcPtr++;
					while(*srcPtr == ' ' || *srcPtr == '\t')
						srcPtr++;
					*sPtr = srcPtr;
					terminate = 1;
					break;
			}
			srcPtr++;
		}
		while((*srcPtr == ' ' || *srcPtr == '\t' || *srcPtr == '\n') && terminate == 0)
		{
			*srcPtr = '\0';
			srcPtr++;
		}
		cmdArgv++;
	}

	*cmdArgv = '\0';
	return arguments;
}


void chop(char *srcPtr)
{
	while(*srcPtr != ' ' && *srcPtr != '\t' && *srcPtr != '\n')
	{
		srcPtr++;
	}
	*srcPtr = '\0';
}

void execute_command (char **cmdArgv, int command, char **sPtr)
{
    int  arguments, modeA, modeB;
	pid_t pid, pid2;
	FILE *file;
	int command2 = 0;
	char *cmdArgv2[INPUT_BUF], *supp2 = NULL;
	int myPipe[2];
	if(command == 3)
	{
		if(pipe(myPipe))					//create pipe
		{
			fprintf(stderr, "Pipe failed");
			exit(-1);
		}
		parse_arguments(*sPtr, cmdArgv2, &supp2, &command2);
	}
	pid = fork();
	if( pid < 0)
	{
		printf("Error");
		exit(-1);
	}
	else if(pid == 0)
	{
		switch(command)
		{
			case 1:
				file = fopen(*sPtr, "w+");
				dup2(fileno(file), 1);
				break;
			case 5:
				file = fopen(*sPtr, "a");
				dup2(fileno(file), 1);
				break;
				//STANDARD INPUT REDIRECTION
			case 2:
				file = fopen(*sPtr, "r");
				dup2(fileno(file), 0);
				break;
				//PIPE IMPLMENTATION
			case 3:
				close(myPipe[0]);		//close input of pipe
				dup2(myPipe[1], fileno(stdout));
				close(myPipe[1]);
				break;
		}
		execvp(*cmdArgv, cmdArgv);
	}
	else
	{
		if(command == 4)
		;//DO NOTHING	
		else if(command == 3)
		{
             //wait the first process
			waitpid(pid, &modeA, 0);		
			pid2 = fork();
			if(pid2 < 0)
			{
				printf("error in forking");
				exit(-1);
			}
			else if(pid2 == 0)
			{
                 //pipe output termination
				close(myPipe[1]);		
				dup2(myPipe[0], fileno(stdin));
				close(myPipe[0]);
				execvp(*cmdArgv2, cmdArgv2);
			}
			else
			{
				;
				//WAIT FOR THE THREADS TO
				//FINISH THEIR TASKS
				close(myPipe[0]);
				close(myPipe[1]);
			}
		}
		else
			waitpid(pid, &modeA, 0);
			
	}
}
