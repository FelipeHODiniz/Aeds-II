/*
    Felipe Henrique Oliveira Diniz - 844318 - C.C. - PUC Minas
*/

import java.util.*;

class aqueInterativo 
{
    //Verificar se é o fim da entrada
    public static boolean isFim(String p) 
    {
        return (p.length() == 3 && p.charAt(0) == 'F' && p.charAt(1) == 'I' && p.charAt(2) == 'M');
    }

    //Contar quantas letras maiusculas a linha tem de forma iterativa
    public static int isUpCase(String palavra) 
    {
        int resp = 0;
        for (int i = 0; i < palavra.length(); i++) 
        {
            if (palavra.charAt(i) >= 'A' && palavra.charAt(i) <= 'Z') 
            {
                resp++;
            }
        }
        return resp;
    }

    public static void main(String args[]) 
    {
        Scanner sc = new Scanner(System.in);
        String linha;

        do 
        {
            linha = sc.nextLine();
            if (!isFim(linha)) 
            {
                System.out.println(isUpCase(linha));
            }
        } while (!isFim(linha));

        sc.close();
    }
}
