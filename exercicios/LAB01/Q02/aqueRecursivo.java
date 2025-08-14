/*
    Felipe Henrique Oliveira Diniz - 844318 - C.C. - PUC Minas
*/

import java.util.*;

class aqueRecursivo
{
    //Verificar se é o fim da entrada
    public static boolean isFim(String p) 
    {
        return (p.length() == 3 && p.charAt(0) == 'F' && p.charAt(1) == 'I' && p.charAt(2) == 'M');
    }

    //Contar quantas letras maiusculas a linha tem de forma recursiva
    public static int isUpCase(String linha)
    {
        return isUpCase(linha, 0);
    }

    private static int isUpCase(String linha, int p)
    {
        int resp = 0;
        if(p < linha.length())
        {
            if (linha.charAt(p) >= 'A' && linha.charAt(p) <= 'Z') 
            {
                resp = 1 + isUpCase(linha, p+1);
            }
            else
            {
                resp = isUpCase(linha, p+1);
            }
        }

        return resp;
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String linha;

        do{
            linha = sc.nextLine();
            if(!isFim(linha))
            {
                System.out.println(isUpCase(linha));
            }
        }while(!isFim(linha));

    }
}