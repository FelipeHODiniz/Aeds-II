#include<stdio.h>
#include<stdbool.h>

bool isEnd(char line[])
{
    return(line[0] == 'F' && line[1] == 'I' && line[2] == 'M');
}

bool isUpCase(char c)
{
    bool resp = false;
    if(c >= 'A' && c <= 'Z')
    {
        resp = true;
    }
    return resp;
}

int countUpper (char palavra[])
{
    int resp = 0;

    for(int i = 0; palavra[i] != '\0'; i++)
    {
        if(isUpCase(palavra[i]))
        {
            resp++;
        }
    }   

    return resp;
}

int main()
{
    char line[256];
    do
    {
        fgets(line, sizeof(line), stdin);
        if(!isEnd(line))
        {
            printf("%d\n", countUpper(line));
        }
    }while(isEnd(line) == false);


    return 0;
}