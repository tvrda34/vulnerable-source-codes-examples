#include <stdio.h>
#include <stdlib.h>

int main() {
    char host[100];
    printf("Enter host to ping: ");
    fgets(host, sizeof(host), stdin);

    char cmd[150];
    snprintf(cmd, sizeof(cmd), "ping -c 1 %s", host);
    system(cmd);

    return 0;
}
