import java.util.*;

public class Grid
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] largada = new int[n];
        int[] chegada = new int[n];
        int posLargada = 0;
        int posChegada = 0;

        while(sc.hasNext())
        {
            int cont = 0;
            
            for(int i = 0; i < n; i++)
            {
                largada[i] = sc.nextInt();
            }

            for(int i = 0; i < n; i++)
            {
                chegada[i] = sc.nextInt();
            }

            for(int i = i; i < n; i++)
            {
                for(int j = 0; j < n; j++)
                {
                    posLargada = largada[i];
                    if(chegada[j] == posLargada)
                    {
                        if(i < j || ((i - j) > 1))
                    }
                }
            }

            for(int i = 0; i < n; i++)
            {
                posLargada = largada[i];
                for(int j = 0; j < n; j++)
                {
                    posChegada = chegada[j];
                    if(posLargada == posChegada && i > j)
                    {
                        cont += (i - j);
                        for(int k = i; k < j - 1; k++)
                        {
                            int tmp = largada[k];
                            largada[k] = largada[k+1];
                            largada[k+1] = tmp; 
                        }
                    }
                }
            }
                System.out.println(cont);
        }
    }
}