import java.util.*;

public class Lab05 {

    public static class Array {
        protected int[] array;
        protected int n;
        public static final int SIZE_100 = 100;
        public static final int SIZE_1000 = 1000;
        public static final int SIZE_10000 = 10000;

        public Array(int tamanho) {
            array = new int[tamanho];
            n = tamanho;
        }

        public void crescente() {
            for (int i = 0; i < n; i++) {
                array[i] = i;
            }
        }

        public void quaseOrdenado() {
            crescente();
            Random r = new Random();
            for (int i = 0; i < n / 20; i++) {
                int a = r.nextInt(n);
                int b = r.nextInt(n);
                int tmp = array[a];
                array[a] = array[b];
                array[b] = tmp;
            }
        }

        public void aleatorio() {
            Random r = new Random();
            for (int i = 0; i < n; i++) {
                array[i] = r.nextInt(n * 10);
            }
        }

        public int[] getArray() {
            return array.clone();
        }
    }

    // Quicksort 
    public static class Quicksort extends Array {
        Random rand = new Random();

        public Quicksort(int tamanho) {
            super(tamanho);
        }

        // Primeiro pivô
        public void quicksortFirstPivot(int esq, int dir) {
            if (esq >= dir) return;
            int i = esq, j = dir;
            int pivo = array[esq];
            while (i <= j) {
                while (array[i] < pivo) i++;
                while (array[j] > pivo) j--;
                if (i <= j) {
                    swap(i, j);
                    i++;
                    j--;
                }
            }
            if (esq < j) quicksortFirstPivot(esq, j);
            if (i < dir) quicksortFirstPivot(i, dir);
        }

        // Último pivô
        public void quicksortLastPivot(int esq, int dir) {
            if (esq >= dir) return;
            int i = esq, j = dir;
            int pivo = array[dir];
            while (i <= j) {
                while (array[i] < pivo) i++;
                while (array[j] > pivo) j--;
                if (i <= j) {
                    swap(i, j);
                    i++;
                    j--;
                }
            }
            if (esq < j) quicksortLastPivot(esq, j);
            if (i < dir) quicksortLastPivot(i, dir);
        }

        // Pivô aleatório
        public void quicksortRandomPivot(int esq, int dir) {
            if (esq >= dir) return;
            int i = esq, j = dir;
            int pivo = array[esq + rand.nextInt(dir - esq + 1)];
            while (i <= j) {
                while (array[i] < pivo) i++;
                while (array[j] > pivo) j--;
                if (i <= j) {
                    swap(i, j);
                    i++;
                    j--;
                }
            }
            if (esq < j) quicksortRandomPivot(esq, j);
            if (i < dir) quicksortRandomPivot(i, dir);
        }

        // Mediana de três
        public void quicksortMedianOfThree(int esq, int dir) {
            if (esq >= dir) return;
            int i = esq, j = dir;
            int mid = (esq + dir) / 2;
            int pivo = median(array[esq], array[mid], array[dir]);

            while (i <= j) {
                while (array[i] < pivo) i++;
                while (array[j] > pivo) j--;
                if (i <= j) {
                    swap(i, j);
                    i++;
                    j--;
                }
            }
            if (esq < j) quicksortMedianOfThree(esq, j);
            if (i < dir) quicksortMedianOfThree(i, dir);
        }

        private int median(int a, int b, int c) {
            if ((a > b) != (a > c)) return a;
            else if ((b > a) != (b > c)) return b;
            else return c;
        }

        private void swap(int i, int j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    // Testes 
    public static void testar(String tipo, int[] base, int tamanho) {
        Quicksort qs = new Quicksort(tamanho);

        long ini, fim;

        qs.array = base.clone();
        ini = System.nanoTime();
        qs.quicksortFirstPivot(0, tamanho - 1);
        fim = System.nanoTime();
        System.out.println(tipo + " - FirstPivot (" + tamanho + "): " + (fim - ini) / 1e6 + " ms");

        qs.array = base.clone();
        ini = System.nanoTime();
        qs.quicksortLastPivot(0, tamanho - 1);
        fim = System.nanoTime();
        System.out.println(tipo + " - LastPivot  (" + tamanho + "): " + (fim - ini) / 1e6 + " ms");

        qs.array = base.clone();
        ini = System.nanoTime();
        qs.quicksortRandomPivot(0, tamanho - 1);
        fim = System.nanoTime();
        System.out.println(tipo + " - RandomPivot(" + tamanho + "): " + (fim - ini) / 1e6 + " ms");

        qs.array = base.clone();
        ini = System.nanoTime();
        qs.quicksortMedianOfThree(0, tamanho - 1);
        fim = System.nanoTime();
        System.out.println(tipo + " - Median3    (" + tamanho + "): " + (fim - ini) / 1e6 + " ms");
    }

    public static void main(String[] args) {
        int[] tamanhos = {Array.SIZE_100, Array.SIZE_1000, Array.SIZE_10000};

        for (int t : tamanhos) {
            Quicksort qs = new Quicksort(t);

            qs.crescente();
            testar("AOrdenado", qs.getArray(), t);

            qs.quaseOrdenado();
            testar("QuaseOrde", qs.getArray(), t);

            qs.aleatorio();
            testar("Aleatorio", qs.getArray(), t);

            System.out.println("---------------------------");
        }
    }
}