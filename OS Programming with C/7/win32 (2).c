
#include <stdio.h>
#include <windows.h> //for windows system call library DWORD, HANDLE.. etc.
#define MYNAME "Ozan" //defined string constant
#define MYBUF 4096

int main() {
    //many variables and logic are from demo
    STARTUPINFO si;
    PROCESS_INFORMATION pi;
    STARTUPINFO si2;
    PROCESS_INFORMATION pi2;
    HANDLE ReadHandle, WriteHandle;
    HANDLE ReadHandle2, WriteHandle2;
    SECURITY_ATTRIBUTES sa = {sizeof (SECURITY_ATTRIBUTES), NULL, TRUE};
    char* argA[16];
    char* argB[16];
    char mybuf[MYBUF];
    char prompt[] = "MyShell> ";
    int i = 0;
    int caseNumber = 0;
    int pipelocation = 0;
    int varlocation = 0;
    int offset = 0;
    int argCount = 0;
    int bytesConsumed = 0;
    int isAmpercent = 1;
    int bytesRead = 0;
    int exitCode = 1;
    HANDLE pOut, pIn;
    DWORD nIn, nOut;

    pOut = GetStdHandle(STD_OUTPUT_HANDLE);
    pIn = GetStdHandle(STD_INPUT_HANDLE);

    while (exitCode) {
        argCount = 0;
        offset = 0;
        bytesConsumed = 0;
        caseNumber = 0;
        pipelocation = 0;
        varlocation = 0;
        isAmpercent = 0;
        memset(mybuf, 0, MYBUF); //set mybuf to null

        WriteFile(pOut, prompt, 8, &nOut, NULL);
        bytesRead = ReadFile(pIn, mybuf, 256, NULL, NULL);
        if (bytesRead == 0) {
            printf("\nExiting shell.\n");
            exit(0);
        } else if (mybuf[0] == '\n') {//for entering without any command
            continue;
        }
        
        while (sscanf(&mybuf[offset], "%s%n", argA[argCount], &bytesConsumed) == 1) {
            argCount++;
            offset += bytesConsumed; //now I have parsed strings in the array of argA;
        }

        exitCode = 0;
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

        switch (caseNumber) {
            case 1:
                //can not implement due to imperfect parsing technique
                break;
            case 2:
                //can not implement due to imperfect parsing technique
                break;
            case 3:
                //can not implement due to imperfect parsing technique
                break;
            case 4:
                //can not implement due to imperfect parsing technique
                break;

            default: // for case = 0 nothing changes
                //following code from democode provided by 415 website
                ZeroMemory(&si, sizeof (si));
                si.cb = sizeof (si);
                ZeroMemory(&pi, sizeof (pi));
                ZeroMemory(&si2, sizeof (si2));
                si2.cb = sizeof (si2);
                ZeroMemory(&pi2, sizeof (pi2));

                if (!CreatePipe(&ReadHandle, &WriteHandle, &sa, 0)) {
                    fprintf(stderr, "Create Pipe Failed\n");
                    return 1;
                }
                if (!CreatePipe(&ReadHandle2, &WriteHandle2, &sa, 0)) {
                    fprintf(stderr, "Create Pipe 2 Failed\n");
                    return 1;
                }

                GetStartupInfo(&si);
                GetStartupInfo(&si2);


                si.hStdOutput = WriteHandle2;
                si.hStdError = GetStdHandle(STD_ERROR_HANDLE);
                si.hStdInput = ReadHandle;
                si.dwFlags = STARTF_USESTDHANDLES;


if (!CreateProcess(NULL, mybuf, NULL, NULL, TRUE, 0, NULL, NULL, &si, &pi)) {
                    fprintf(stderr, "Create Process 2 Failed\n");
                    return -1;
                }

                si2.hStdInput = ReadHandle2;
                si2.hStdError = GetStdHandle(STD_ERROR_HANDLE);
                si2.hStdOutput = WriteHandle;
                si2.dwFlags = STARTF_USESTDHANDLES;


if (!CreateProcess(NULL, mybuf, NULL, NULL, TRUE, 0, NULL, NULL, &si2, &pi2)) {
                    fprintf(stderr, "Create Process 1 Failed\n");
                    return -1;
                }

                WaitForSingleObject(pi2.hProcess, INFINITE);
                printf("parent:Child one Completed\n");

                WaitForSingleObject(pi.hProcess, INFINITE);
                printf("parent:Child two Completed\n");


                CloseHandle(pi.hProcess);
                CloseHandle(pi.hThread);
                CloseHandle(pi2.hProcess);
                CloseHandle(pi2.hThread);

                break;
        }
    }

    return 0;
}




