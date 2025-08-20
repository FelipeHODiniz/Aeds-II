import java.util.*;

class espelho
{
    public static void main(String args[])
    {
        int a = 0;
        int b = 0;
        int i = 0;
        int x = 0;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            a = sc.nextInt();
            b = sc.nextInt();
            i = b - a;
            while(a <= b)
            {
                System.out.print(a);
                a++;
            }
            while(i >= 0)
            {
                a = b;
                while(a > 0)
                {
                    x = a % 10;
                    System.out.print(x);
                    a = a / 10;
                }
                i--;
                b--;
            }
            System.out.println(" ");
        }
        sc.close();
    }
}