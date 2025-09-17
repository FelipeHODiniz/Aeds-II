import java.util.*;

class InversaoString
{
    // Verifica se a linha é "FIM"
    public static boolean isFIM(String linha)
    {
         return (linha.length() == 3 && linha.charAt(0) == 'F' && linha.charAt(1) == 'I' && linha.charAt(2) == 'M');
    }

    // Retorna a string invertida
    public static String inversao(String linha)
    {
        String resp = "";
        for(int i = linha.length() - 1; i >= 0; i--)
        {
            resp += linha.charAt(i); // Constrói a string de trás para frente
        }
        return resp;
    }

    public static void main(String args[])
    { 
        Scanner sc = new Scanner(System.in);
        String linha = sc.nextLine();

        while( !(isFIM(linha)) ) // Continua até encontrar "FIM"
        {
            System.out.println(inversao(linha)); // Imprime a string invertida
            linha = sc.nextLine(); // Lê a próxima linha
        }
        sc.close();
    }
}