import java.util.*;

public class palindromo
{
    public static boolean isFIM(String linha)
    {
         return (linha.length() == 3 && linha.charAt(0) == 'F' && linha.charAt(1) == 'I' && linha.charAt(2) == 'M');
    }

    public static boolean isPalindromo(String linha)
    {
        boolean resp = true;
        int i, j;
        for(i = 0, j = linha.length() - 1; i < j; i++, j--)
        {
            if(linha.charAt(i) != linha.charAt(j))
            {
                resp = false;
                i = j;
            }
        }

        return resp;
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String linha = sc.nextLine();
        while( !(isFIM(linha)) )
        {
            if(isPalindromo(linha))
            {
                System.out.println("SIM");
            }
            else
            {
                System.out.println("NAO");
            }


            linha = sc.nextLine(); // lê a próxima
        }
        sc.close();
    }
}
