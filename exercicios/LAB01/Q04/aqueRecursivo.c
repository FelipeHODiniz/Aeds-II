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

int countUpper (char palavra[], int pos)
{
    if(palavra[pos] == '\0')
    {
        return 0;
    }

    if(palavra[pos] != '\0')
    {
        if(isUpCase(palavra[pos]))
        {
            return 1 + countUpper(palavra, pos+1);
        }
    }
    else
    {
        return countUpper(palavra, pos+1);

    }
}

int main()
{
    char line[256];
    do
    {
        fgets(line, sizeof(line), stdin);
        if(!isEnd(line))
        {
            printf("%d\n", countUpper(line, 0));
        }
    }while(isEnd(line) == false);


    return 0;
}