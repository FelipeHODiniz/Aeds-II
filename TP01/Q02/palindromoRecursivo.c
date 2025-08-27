#include<stdio.h>
#include<stdbool.h>

bool isFim(char linha[])
{
    return(linha[0] == 'F' && linha[1] == 'I' && linha[2] == 'M'  && linha[3] == '\0');
}

bool isPalindromo(char linha[], int i, int j)
{
    if(i >= j)
    {
        return true;
    }
    if(linha[i] == linha[j])
    {
        return isPalindromo(linha, i+1, j-1);
    }
    else
    {
        return false;
    }
}

int getSize(char linha[])
{
    int cont = 0;
    for(int i = 0; linha[i] != '\0'; i++)
    {
        cont++;
    }
    return cont-1;
}

int main()
{
    char linha[100];

    scanf("%s", linha);

    while(!(isFim(linha)))
    {
        int length = getSize(linha);
        if(isPalindromo(linha, 0, length))
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
        scanf("%s", linha);
    }



    return 0;
}