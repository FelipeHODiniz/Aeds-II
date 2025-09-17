import java.util.*;

class SomaDigitosRec
{
    // Verifica se a linha é "FIM"
    public static boolean isFIM(String linha)
    {
         return (linha.length() == 3 && linha.charAt(0) == 'F' && linha.charAt(1) == 'I' && linha.charAt(2) == 'M');
    }

    public static int somaDigitos(String linha)
    {
        return somaDigitos(linha, 0);
    }
    // Calcula a soma dos dígitos numéricos presentes na string
    public static int somaDigitos(String linha, int i)
    {
        if(i >= linha.length())
        {
            return 0;
        }
        if(linha.charAt(i) >= '0' && linha.charAt(i) <= '9')
        {
            return (linha.charAt(i) - '0') + somaDigitos(linha, i+1); // Converte caractere para número e soma
        }

        return somaDigitos(linha, i+1);
    }

    public static void main(String args[])
    { 
        Scanner sc = new Scanner(System.in);
        String linha = sc.nextLine();

        while( !(isFIM(linha)) ) // Continua até encontrar "FIM"
        {
            System.out.println(somaDigitos(linha)); // Imprime a soma dos digitos
            linha = sc.nextLine(); // Lê a próxima linha
        }
        sc.close();
    }
}
