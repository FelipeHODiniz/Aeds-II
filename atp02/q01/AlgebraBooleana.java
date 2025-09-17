import java.util.*;

class AlgebraBooleana
{
    // Substitui as variáveis A, B e C pelos valores binários fornecidos no array entradas
    public static String substituirVariaveis(int[] entradas, String expressao) 
    {
        String expressaoSubs = "";
        int length = expressao.length();

        for (int i = 0 ; i < length ; i++) 
        {
            if (expressao.charAt(i) != ' ') 
            {
                if (expressao.charAt(i) == 'A') 
                {
                    expressaoSubs += entradas[0]; // Substitui A pelo primeiro valor
                } 
                else if (expressao.charAt(i) == 'B') 
                {
                    expressaoSubs += entradas[1]; // Substitui B pelo segundo valor
                } 
                else if (expressao.charAt(i) == 'C')
                {
                    expressaoSubs += entradas[2]; // Substitui C pelo terceiro valor
                } 
                else 
                {
                    expressaoSubs += expressao.charAt(i); // Mantém outros caracteres
                }
            }
        }

        return expressaoSubs;
    }

    // Realiza a operação lógica AND entre os valores dentro da expressão
    private static String and(String expressao) 
    {
        String expAnd = ""; 
        int length = expressao.length();

        for (int i = 0 ; i < length ; i++) 
        {
            if ( expressao.charAt(i) == 'a' && 
                (expressao.charAt(i+4) == '0' || expressao.charAt(i+4) == '1') && 
                (expressao.charAt(i+6) == '0' || expressao.charAt(i+6) == '1') ) 
            {
                if (expressao.charAt(i+7) == ')') 
                {
                    // AND de dois operandos
                    expAnd += (expressao.charAt(i+4) == '1' && expressao.charAt(i+6) == '1') ? 1 : 0;
                    i+= 7;
                } 
                else if (expressao.charAt(i+9) == ')') 
                {
                    // AND de três operandos
                    expAnd += (expressao.charAt(i+4) == '1' && expressao.charAt(i+6) == '1' && expressao.charAt(i+8) == '1') ? 1 : 0;
                    i += 9;
                }
                else 
                {
                    expAnd += expressao.charAt(i);
                }
            } 
            else 
            {
                expAnd += expressao.charAt(i);
            }
        }
        
        return expAnd;
    }

    // Realiza a operação lógica NOT em um único valor
    private static String not(String expressao) 
    {
        String expNot = "";
        int length = expressao.length();

        for (int i = 0 ; i < length ; i++) 
        {
            if ( expressao.charAt(i) == 'n' && 
                (expressao.charAt(i+4) == '0' || expressao.charAt(i+4) == '1') ) 
            {
                // Inverte o valor lógico
                expNot += expressao.charAt(i+4) == '1' ? 0 : 1;
                i += 5;
            } 
            else 
            {
                expNot += expressao.charAt(i);
            }
        }

        return expNot;
    }

    // Realiza a operação lógica OR entre os valores dentro da expressão
    private static String or(String expressao) 
    {
        String expOr = "";
        int length = expressao.length();

        for (int i = 0 ; i < length ; i++) 
        {
            if ( expressao.charAt(i) == 'o' && 
                (expressao.charAt(i+3) == '1' || expressao.charAt(i+3) == '0') &&  
                (expressao.charAt(i+5) == '1' || expressao.charAt(i+5) == '0') ) 
                {
                if (expressao.charAt(i+6) == ')') 
                {
                    // OR de dois operandos
                    expOr += (expressao.charAt(i + 3) == '1' || expressao.charAt(i + 5) == '1') ? 1 : 0; 
                    i += 6; 
                } 
                else if (expressao.charAt(i+8) == ')') 
                {
                    // OR de três operandos
                    expOr += (expressao.charAt(i+3) == '1' || expressao.charAt(i+5) == '1' || expressao.charAt(i + 7) == '1') ? 1 : 0; 
                    i+= 8; 
                }
                else if (expressao.charAt(i+10) == ')') 
                {
                    // OR de quatro operandos
                    expOr += ( expressao.charAt(i+3) == '1' || expressao.charAt(i+5) == '1' || 
                            expressao.charAt(i+7) == '1' || expressao.charAt(i+9) == '1' ) ? 1 : 0;
                    i += 10; 
                }
            } 
            else 
            {
                expOr += expressao.charAt(i);
            }
        }
        
        return expOr;
    }

    // Avalia a expressão booleana aplicando as operações na ordem correta
    public static String algebra(String expressao) 
    {
        int qntOperacao = 0;
        int expressaoLength = expressao.length();

        // Conta a quantidade de operações (quantidade de parênteses)
        for(int i = 0 ; i < expressaoLength; i++) 
        {
            if (expressao.charAt(i) == '(') 
            {
                qntOperacao++;
            }
        }

        // Armazena a ordem das operações: AND, NOT, OR
        char[] funcao = new char[qntOperacao];
        int funcaoLength = funcao.length;

        for (int i = 0, j = qntOperacao-1 ; i < expressaoLength-2; i++) 
        {
            if (expressao.charAt(i) == 'a' && expressao.charAt(i+1) == 'n' && expressao.charAt(i+2) == 'd') 
            { 
                funcao[j] = 'a';
                j--;
            } 
            else if (expressao.charAt(i) == 'n' && expressao.charAt(i+1) == 'o' && expressao.charAt(i+2) == 't')
            { 
                funcao[j] = 'n';
                j--;
            } 
            else if (expressao.charAt(i) == 'o' && expressao.charAt(i+1) == 'r')
            {
                funcao[j] = 'o';
                j--;
            } 
        }

        // Executa cada operação na ordem inversa (da mais interna para a mais externa)
        for (int i = 0 ; i < funcaoLength ; i++) 
        {
            if (funcao[i] == 'a') 
            {
                expressao = and(expressao);
            } 
            else if (funcao[i] == 'n')
            {
                expressao = not(expressao);
            }
            else 
            {
                expressao = or(expressao);
            }
        }

        return expressao;
    }


    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        int qntExp = sc.nextInt(); // Quantidade de entradas a serem lidas
        int entradas[] = new int[3];
        String expressao;

        while(qntExp > 0)
        {
            for(int i = 0; i < qntExp; i++)
            {
                entradas[i] = sc.nextInt(); // Lê os valores das variáveis A, B e C
            }
            expressao = sc.nextLine(); // Lê a expressão booleana
            expressao = substituirVariaveis(entradas, expressao); // Substitui variáveis pelos valores
            expressao = algebra(expressao); // Avalia a expressão

            System.out.println(expressao); // Imprime o resultado final

            qntExp = sc.nextInt(); // Lê a próxima quantidade de entradas
        }
        sc.close();
    }
}