
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#define MYNAME "Ozan"
#define MYBUF 4096

int main() {
    char* argA[16];
    char* argB[16];
    char mybuf[MYBUF];
    char prompt[] = "prompt> ";
    int argCount = 0;
    int offset = 0;
    int bytesConsumed = 0; //for bytes consumed by sscanf
    int bytesRead = 0; //for read systemcall return value
    int i = 0;
    int caseNumber = 0; // for switch structure
    int pid1, pid2;
    int pipeID[2];
    int pipelocation = 0; // index location of the pipe command
    int varlocation = 0; // index locatino for other commands
    int status;
    int fileD;
    int isAmpercent = 0; //if there is ampercent

    while (1) {
        argCount = 0;
        offset = 0;
        bytesConsumed = 0;
        caseNumber = 0;
        pipelocation = 0;
        varlocation = 0;
        isAmpercent = 0;
        for (i = 0; i < 16; i++) { //allocating memory space for each array pointer
            argA[i] = calloc(25, sizeof (char));
            argB[i] = calloc(25, sizeof (char));
        }
        memset(mybuf, 0, MYBUF); //set mybuf to null
        write(1, prompt, sizeof (prompt));
        bytesRead = read(0, mybuf, MYBUF);
        if (bytesRead == 0) {
            printf("\nExiting shell.\n");
            exit(0);
        } else if (mybuf[0] == '\n') {//for entering without any command
            continue;
        }
        //printf("%s",&mybuf[0]); part 1
        //http://stackoverflow.com/questions/3975236/how-to-use-sscanf-in-loops
        while (sscanf(&mybuf[offset], "%s%n", argA[argCount], &bytesConsumed) == 1) {
            argCount++;
            offset += bytesConsumed; //now I have parsed strings in the array of argA;
        }
        for (i = 0; i < argCount; i++) { //checking for cases |,>,<,>>,&
            if (strcmp("|", argA[i]) == 0) {
                caseNumber = 1;
                //printf("haspipe\n");
                pipelocation = i;
                //argA[i] = NULL;
            }
            if (strcmp(">", argA[i]) == 0) {
                caseNumber = 2;
                varlocation = i; // record location of >
            }
            if (strcmp("<", argA[i]) == 0) {
                caseNumber = 3;
                varlocation = i;
            }
            if (strcmp(">>", argA[i]) == 0) {
                caseNumber = 4;
                varlocation = i;
            }
            if (strcmp("&", argA[i]) == 0) {
                isAmpercent = 1;
                argA[i] = NULL;
            }
        }
        /*for(i=0;i<argCount;i++){
                printf("%s\n", argA[i]);
        }*/
        argA[argCount] = NULL; //null terminating argA array
        if (strcmp("exit", argA[0]) == 0) { //allowing typing exit to exit
            printf("shell exiting.\n");
            exit(0);
        }

        switch (caseNumber) {
            case 1:
                argA[pipelocation] = NULL;
                if ((status = pipe(pipeID)) == -1) { /* error exit - bad pipe */
                    perror("Bad pipe");
                    exit(-1);
                }
                if ((pid1 = fork()) == -1) { /* error exit - bad fork */
                    perror("Bad fork");
                    exit(-1);
                }
                if (pid1 == 0) { /* The first child process */
                    /* make sure you start the last process in the pipeline first! */

printf("The first child is starting: %s %s\n", argA[pipelocation + 1], argA[pipelocation + 2]);
                    close(0); //closing standard in
dup(pipeID[0]); //dubs the read end of the pipe, puts that in the pipe stdin place
                    close(pipeID[0]); //closes read end of the pipe
                    close(pipeID[1]); //closes write end

execvp(argA[pipelocation + 1], &argA[pipelocation + 1]); //execute the commend
                    /* error exit - exec returned */
                    perror("Execl returned");
                    exit(-1);
                }
                /* this is the parent again */
                if ((pid2 = fork()) == -1) { /* error exit - bad fork */
                    perror("Bad fork");
                    exit(-1);
                }
                if (pid2 == 0) { /* the second child process */
                    printf("The second child is starting: %s %s\n", argA[0], argA[1]);
                    close(1); // closed stdout
                    dup(pipeID[1]);
                    close(pipeID[0]);
                    close(pipeID[1]);
                    execvp(argA[0], &argA[0]);
                    /* error exit - exec returned */
                    perror("Execl returned");
                    exit(-1);
                }
                /* back to the parent again */
                close(pipeID[0]);
                close(pipeID[1]);

                wait(pid1, 0, 0);
                wait(pid2, 0, 0);

                printf("The parent is exiting\n");
                break;
            case 2:
                argA[varlocation] = NULL;
                //this is from the demo code, same logic, differnt argument variable
                if ((pid1 = fork()) == -1) { /* error exit - fork failed */
                    perror("Fork failed");
                    exit(-1);
                }
                if (pid1 == 0) { /* this is the child */
                    printf("This is the child ready to execute: %s\n", argA[0]);
                    close(1);
                    fileD = open(argA[varlocation + 1], O_WRONLY);
                    dup(fileD);
                    //dup2(fileD, 1);
                    execvp(argA[0], &argA[0]);
                    /* error exit - exec returned */
                    perror("Exec returned");
                    exit(-1);
                } else { /* this is the parent -- wait for child to terminate */

                    wait(pid1, 0, 0);
                    printf("The parent is exiting now\n");

                }

                break;
            case 3:
                argA[varlocation] = NULL;
                //this is from the demo code, same logic, differnt argument variable
                if ((pid1 = fork()) == -1) { /* error exit - fork failed */
                    perror("Fork failed");
                    exit(-1);
                }
                if (pid1 == 0) { /* this is the child */
                    printf("This is the child ready to execute: %s\n", argA[0]);
                    close(0);
                    fileD = open(argA[varlocation + 1], O_RDONLY);
                    dup(fileD);
                    //dup2(fileD, 1);
                    execvp(argA[0], &argA[0]);
                    /* error exit - exec returned */
                    perror("Exec returned");
                    exit(-1);
                } else { /* this is the parent -- wait for child to terminate */
                    wait(pid1, 0, 0);
                    printf("The parent is exiting now\n");
                }
                break;
            case 4:
                argA[varlocation] = NULL;
                //this is from the demo code, same logic, differnt argument variable
                if ((pid1 = fork()) == -1) { /* error exit - fork failed */
                    perror("Fork failed");
                    exit(-1);
                }
                if (pid1 == 0) { /* this is the child */
                    printf("This is the child ready to execute: %s\n", argA[0]);
                    close(1);
                    fileD = open(argA[varlocation + 1], O_WRONLY | O_APPEND);
                    dup(fileD);
                    //dup2(fileD, 1);
                    execvp(argA[0], &argA[0]);
                    /* error exit - exec returned */
                    perror("Exec returned");
                    exit(-1);
                } else { /* this is the parent -- wait for child to terminate */
                    wait(pid1, 0, 0);
                    printf("The parent is exiting now\n");

                }
                break;

            default: // for case = 0 nothing changes
                //this is from the demo code, same logic, differnt argument variable
                if ((pid1 = fork()) == -1) { /* error exit - fork failed */
                    perror("Fork failed");
                    exit(-1);
                }
                if (pid1 == 0) { /* this is the child */
                    printf("This is the child ready to execute: %s\n", argA[0]);
                    execvp(argA[0], &argA[0]);
                    /* error exit - exec returned */
                    perror("Exec returned");
                    exit(-1);
                } else { /* this is the parent -- wait for child to terminate */
printf("ampercenet value is currently %d\n", isAmpercent);
					if( isAmpercent == 1){
printf("The parent is exiting now without waiting for child\n");
						continue;
					} else {
                    wait(pid1, 0, 0);
                    printf("The parent is exiting now\n");
					}
                }
                break;
        }
    }
    return 0;
}

