import java.util.*;

public class QuadroMedalhas
{
    public static class Pais
    {
        String nome;
        int ouro;
        int prata;
        int bronze;

        Pais()
        {
            this("", 0, 0, 0);
        }

        Pais(String nome, int ouro, int prata, int bronze)
        {
            this.nome = nome;
            this.ouro = ouro;
            this.prata = prata;
            this.bronze = bronze;
        }
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int qnt = sc.nextInt();
        sc.nextLine(); //\n para pegar a quebra de linha do java
        int n = qnt;

        Pais[] medalhas = new Pais[n];

        for(int i = 0; i < n; i++)
        {
            String nome = sc.next();
            int ouro = sc.nextInt();
            int prata = sc.nextInt();
            int bronze = sc.nextInt();
            sc.nextLine();

            medalhas[i] = new Pais(nome, ouro, prata, bronze);
        }

        //para ordenar vou usar a chave sendo int ouro, se for igual, prata, se for igual bronze e se for igual ordenar por ordem lexicografica

        for(int i = 0; i < n-1; i++)
        {
            for(int j = i+1; j < n; j++)
            {
                if(medalhas[i].ouro < medalhas[j].ouro)
                {
                    Pais temp = medalhas[i];
                    medalhas[i] = medalhas[j];
                    medalhas[j] = temp;
                }
                if(medalhas[i].ouro == medalhas[j].ouro)
                {
                    if(medalhas[i].prata < medalhas[j].prata)
                    {
                        //swap
                        Pais temp = medalhas[i];
                        medalhas[i] = medalhas[j];
                        medalhas[j] = temp;
                    }
                    if(medalhas[i].prata == medalhas[j].prata)
                    {
                        if(medalhas[i].bronze < medalhas[j].bronze)
                        {
                            //swap
                            Pais temp = medalhas[i];
                            medalhas[i] = medalhas[j];
                            medalhas[j] = temp;
                        }
                        if(medalhas[i].bronze == medalhas[j].bronze)
                        {
                            //ordenar lexicograficamente
                        }
                    }
                }
            }
        }

        for(int i = 0; i < n; i++)
        {
                System.out.print(medalhas[i].nome + " ");
                System.out.print(medalhas[i].ouro  + " ");
                System.out.print(medalhas[i].prata  + " ");
                System.out.print(medalhas[i].bronze);
                System.out.println("");
        }

        sc.close();
    }
}