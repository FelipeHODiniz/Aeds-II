#include<stdio.h>
#include<stdbool.h>

// Calcula a soma dos dígitos numéricos presentes na string
int somaDigitos(char linha[], int length)
{
    int soma = 0;
    for(int i = 0; i < length; i++)
    {
        if(linha[i] >= '0' && linha[i] <= '9')
        {
            soma += (linha[i] - '0'); // Converte caractere para número e soma
        }
    }
    return soma;
}

// Verifica se a string é "FIM"
bool isFim(char linha[])
{
    return(linha[0] == 'F' && linha[1] == 'I' && linha[2] == 'M'  && linha[3] == '\0');
}

// Retorna o tamanho da string sem contar o '\0'
int getSize(char linha[])
{
    int cont = 0;
    for(int i = 0; linha[i] != '\0' ; i++)
    {
        cont++;
    }
    return cont;
}

// Remove o '\n' lido pelo fgets e substitui por '\0'
void removeEnter(char linha[]) {
    int i = 0;
    while (linha[i] != '\0') {
        if (linha[i] == '\n') {
            linha[i] = '\0';
            i--;
        }
        i++;
    }
}

int main()
{
    char linha[100];
    fgets(linha, sizeof(linha), stdin);
    removeEnter(linha);

    while(!(isFim(linha))) // Continua até encontrar "FIM"
    {
        int length = getSize(linha);
        printf("%d\n", somaDigitos(linha, length)); // Imprime a soma dos dígitos

        fgets(linha, sizeof(linha), stdin);
        removeEnter(linha);
    }
    
    return 0;
}