#include<stdio.h>
#include<stdbool.h>

// Retorna true se todos os caracteres da string forem vogais
bool isVogal(char linha[], int size)
{
    bool resp = true;

    for(int i = 0; i < size; i++)
    {
        if( ! ( linha[i] == 'a' || linha[i] == 'e' || linha[i] == 'i' || linha[i] == 'o' || linha[i] == 'u' ||
                linha[i] == 'A' || linha[i] == 'E' || linha[i] == 'I' || linha[i] == 'O' || linha[i] == 'U') )
        {
            resp = false; // Encontrou caractere que não é vogal
            i = size;
        }
    }

    return resp;
}

// Retorna true se todos os caracteres da string forem consoantes
bool isConsoante(char linha[], int size)
{
    bool resp = true;

    for(int i = 0; i < size; i++)
    {
        // Verifica se é letra e não é vogal
        if( !( linha[i] >= 'a' && linha[i] <= 'z' || linha[i] >= 'A' && linha[i] <= 'Z' ) ) 
        {
            resp = false;
            i = size;
        }
        if( linha[i] == 'a' || linha[i] == 'e' || linha[i] == 'i' || linha[i] == 'o' || linha[i] == 'u' ||
            linha[i] == 'A' || linha[i] == 'E' || linha[i] == 'I' || linha[i] == 'O' || linha[i] == 'U' ) 
        {
            resp = false;
            i = size;
        }
    }

    return resp;
}

// Retorna true se todos os caracteres forem dígitos (número inteiro)
bool isInt(char linha[], int size)
{
    bool resp = true;

    for(int i = 0; i < size; i++)
    {
        if( !(linha[i] >= '0' && linha[i] <= '9') )
        {
            resp = false; // Encontrou caractere que não é dígito
            i = size;
        }
    }

    return resp;
}

// Retorna true se a string representar um número real (possui um ponto ou vírgula)
bool isReal(char linha[], int size)
{
    bool resp = true;
    bool ponto = false;

    for(int i = 0; i < size; i++)   
    {
        if( !(linha[i] >= '0' && linha[i] <= '9') ) 
        {
            if ((linha[i] == '.' || linha[i] == ',') && !ponto) 
            {
                ponto = true; // Marca que encontrou ponto ou vírgula
            } 
            else 
            {
                resp = false;
                i = size;
            }
        }
    }

    if(!ponto) resp = false; // Se não houver ponto ou vírgula, não é real

    return resp;
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
    return cont-1; // Desconsidera '\n' lido pelo fgets
}

// Substitui '\n' pelo '\0' para finalizar corretamente a string
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

    while(!(isFim(linha)))
    {
        int length = getSize(linha);

        // Testes principais: vogal, consoante, inteiro, real
        if(isVogal(linha, length)) printf("SIM "); else printf("NAO ");
        if(isConsoante(linha, length)) printf("SIM "); else printf("NAO ");
        if(isInt(linha, length)) printf("SIM "); else printf("NAO ");
        if(isReal(linha, length)) printf("SIM "); else printf("NAO ");

        printf("\n");

        fgets(linha, sizeof(linha), stdin);
        removeEnter(linha);
    }
    
    return 0;
}