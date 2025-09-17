import java.util.*;

public class alteracao
{
    public static boolean isFim(String linha)
    {
        return(linha.length() == 3 && linha.charAt(0) == 'F' && linha.charAt(1) == 'I' && linha.charAt(2) == 'M');
    }

    public static String alterarLetras(String linha, char c, char k)
    {
        String alt = "";
        for (int i = 0; i < linha.length(); i++) 
        {
            if (linha.charAt(i) != c) 
            {
                alt = alt + linha.charAt(i);
            }
            else
            {
                alt = alt + k;
            }
        }
        return alt;
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String linha = sc.nextLine();

        char c = ' ';
        char k = ' ';
        Random gerador = new Random();
        gerador.setSeed(4);
        
        
        while( !(isFim(linha)) )
        {
            c  =  ( char ) ( 'a' + ( Math.abs( gerador.nextInt( ) ) % 26 ) );
            k = ( char ) ( 'a' + ( Math.abs( gerador.nextInt( ) ) % 26 ) );

            System.out.println(alterarLetras(linha, c, k));

            linha = sc.nextLine();
        }
        sc.close();
    }
}