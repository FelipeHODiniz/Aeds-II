#include<stdio.h>
#include<stdlib.h>

int absMath(char s1[], char s2[])
{
    int resp = 0;

    for(int i=0; i < 100; i++)
    {
        if(s1[i] == '\0' || s2[i] == '\0')
        {
            i = 100;
        }
        resp++;
    }

    return resp-1;
}

int getSize(char s1[])
{
    int resp = 0;

    for(int i=0; i < 100; i++)
    {
        if(s1[i] == '\0')
        {
            i = 100;
        }
        resp++;
    }

    return resp-1;
}

int main()
{
    char s1[100];
    char s2[100];

    while(scanf("%s", s1) == 1 && scanf("%s", s2) == 1)
    {
        char s3[200];

        int m = absMath(s1, s2);
        int size1 = getSize(s1);
        int size2 = getSize(s2);

        int i = 0;
        int j = 0;

        for(i = 0, j = 0; j < m; i= i+2, j++)
        {
            s3[i] = s1[j];
            s3[i+1] = s2[j];
        }

        if(size1 > size2)
        {
            while(j < size1)
            {
                //printf("%c", s1[j]);
                s3[i] = s1[j];
                i++;
                j++;
            }
            s3[i] = '\0';
        }

        printf("o s3 e %s\n", s3);
    }
}