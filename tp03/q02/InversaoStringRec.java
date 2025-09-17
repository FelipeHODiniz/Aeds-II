import java.util.*;

class InversaoStringRec
{
    // Verifica se a linha é "FIM"
    public static boolean isFIM(String linha)
    {
         return (linha.length() == 3 && linha.charAt(0) == 'F' && linha.charAt(1) == 'I' && linha.charAt(2) == 'M');
    }

    public static String inversao(String linha)
    {
        String resp = "";
        return inversao(linha, linha.length()-1);
    }
    // Retorna a string invertida
    public static String inversao(String linha, int i)
    {
        if(i < 0)
        {
            return "";
        }

        return linha.charAt(i) + inversao(linha, i-1); // Constrói a string de trás para frente
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