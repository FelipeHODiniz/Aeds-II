#include<stdio.h>
#include<stdbool.h>

bool isVogal(char c) 
{
    return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
            c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
}

// Retorna true se todos os caracteres da string forem vogais
bool isVogalRec(char linha[], int i, int size) 
{
    if (i == size) // caso base: chegou ao fim
    {
        return true;
    }
    if (!isVogal(linha[i])) // se não for vogal, retorna falso
    {
        return false;
    }
    return isVogalRec(linha, i + 1, size); // chama para o próximo caractere
}

bool isLetra(char c) 
{
    return ( (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') );
}

// versão recursiva que verifica se todos os caracteres são consoantes
bool isConsoanteRec(char linha[], int i, int size) 
{
    if (i == size) // fim da string
    {
        return true;
    }
    if (!isLetra(linha[i]) || isVogal(linha[i])) // se não for letra OU for vogal → falso
    {
        return false;
    }
    return isConsoanteRec(linha, i + 1, size); // avança
}


bool isIntRec(char linha[], int i, int size) 
{
    if (i == size) // caso base: percorreu tudo
    {
        return true;
    }
    if (!(linha[i] >= '0' && linha[i] <= '9')) // se não for dígito
    {
        return false;
    }
    return isIntRec(linha, i + 1, size); // chama para o próximo
}

// Função recursiva auxiliar
bool isRealRec(char linha[], int i, int size, bool ponto) {
    if (i == size) {
        return ponto; // só é real se encontrou ponto/vírgula
    }

    if (linha[i] >= '0' && linha[i] <= '9') {
        return isRealRec(linha, i + 1, size, ponto);
    } 
    else if ((linha[i] == '.' || linha[i] == ',') && !ponto) {
        return isRealRec(linha, i + 1, size, true);
    } 
    else {
        return false; // caractere inválido
    }
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
        if(isVogalRec(linha, 0, length)) printf("SIM "); else printf("NAO ");
        if(isConsoanteRec(linha, 0, length)) printf("SIM "); else printf("NAO ");
        if(isIntRec(linha, 0, length)) printf("SIM "); else printf("NAO ");
        if(isRealRec(linha, 0, length, false)) printf("SIM "); else printf("NAO ");

        printf("\n");

        fgets(linha, sizeof(linha), stdin);
        removeEnter(linha);
    }
    
    return 0;
}