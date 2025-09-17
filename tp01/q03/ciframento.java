import java.util.*;
import java.io.*;
import java.nio.charset.*;

public class ciframento
{
    private static String charset = "ISO-8859-1";
    
    public static void println(String x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static boolean isFim(String linha)
    {
        return(linha.length() == 3 && linha.charAt(0) == 'F' && linha.charAt(1) == 'I' && linha.charAt(2) == 'M');
    }

     public static String ciframento(String linha) 
     {
        String cif = "";
        for (int i = 0; i < linha.length(); i++) 
        {
            char caractere = linha.charAt(i); 
            int asc = (int) caractere; 

            if (asc >= 32 && asc <= 126) 
            { 
                asc = (asc - 32 + 3) % 95 + 32;
                cif += (char) asc;
            } 
            else 
            {
                cif += caractere;
            }
        }
        return cif;
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String linha = sc.nextLine();
        
        while( !(isFim(linha)) )
        {
            System.out.println(ciframento(linha));

            linha = sc.nextLine();
        }
        sc.close();
    }
}