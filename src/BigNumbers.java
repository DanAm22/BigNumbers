import java.util.Arrays;

public class BigNumbers {

    public int[] sum(int[] op1, int[] op2) {
        if(op1.length == 1 && op1[0] == 0){
            return op2;
        }
        if(op2.length == 1 && op2[0] == 0){
            return op1;
        }
        op1 = reverseArray(op1);
        op2 = reverseArray(op2);
        int[] sum = new int[op1.length + op2.length];
        int k = 0;
        int overTen = 0;
        for(int i = 0; i < Math.min(op1.length, op2.length); i++) {
            int sumNumbers = op1[i] + op2[i] + overTen;
            if(sumNumbers > 10){
                sum[k++] = sumNumbers % 10;
                overTen = 1;
            }
            else{
                sum[k++] = sumNumbers;
                overTen = 0;
            }
        }

        if(op1.length > op2.length){
            sum[k++] = op1[op2.length] + overTen;
            overTen = 0;
            for(int i = op2.length + 1; i < op1.length ; i++){
                sum[k++] = op1[i];
            }
        }
        else if(op2.length > op1.length){
            sum[k++] = op2[op1.length] + overTen;
            overTen = 0;
            for(int i = op1.length + 1; i < op2.length ; i++){
                sum[k++] = op2[i];
            }
        }
        else if(op1.length == op2.length && overTen == 1){
            sum[k++] = overTen;
        }

        sum = Arrays.copyOf(sum,k);
        sum = reverseArray(sum);
        return sum;
    }

    public static int[] reverseArray(int[] array){
        int[] reverse = new int[array.length];
        for(int i = 0, j = array.length - 1; i < array.length && j >= 0; i++,j--){
            reverse[i] = array[j];
        }
        return reverse;
    }

    private static String noToString(int[] no) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < no.length; i++) {
            sb.append(no[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int noTests = 6;

        int[][] op1 = {{0}, {9, 9}, {9}, {2, 1, 7, 8},
                {5, 0, 5, 0, 5}, {1, 0, 8, 6, 7, 8, 9, 4, 2, 3, 4}};
        int[][] op2 = {{1, 0, 1}, {1}, {9, 9, 9, 9}, {5, 9, 6},
                {5, 5, 0, 5, 0}, {3, 4, 2, 8, 9, 7, 9, 2, 3, 4, 9}};
        int[][] correct = {{1, 0, 1}, {1, 0, 0}, {1, 0, 0, 0, 8}, {2, 7, 7, 4},
                {1, 0, 5, 5, 5, 5}, {4, 5, 1, 5, 7, 6, 8, 6, 5, 8, 3}};

        int total = 0;
        for (int i = 0; i < noTests; i++) {
            System.out.println("Test " + (i+1) + ":");
            String op1S = noToString(op1[i]);
            String op2S = noToString(op2[i]);

            BigNumbers bn = new BigNumbers();
            int[] rez = bn.sum(op1[i], op2[i]);

            String rezS = noToString(rez);
            String correctS = noToString(correct[i]);
            System.out.println(op1S + " + " + op2S + " = " + rezS + " C: " + correctS +
                    "......" + (rezS.equals(correctS) ? "OK" : "WRONG"));
            System.out.println();

            total += rezS.equals(correctS) ? 1 : 0;
        }

        System.out.println("Your total score is: " + total + " / " + noTests);
        System.out.println(total > 3 ? "You passed! Hurray! :)" : "Sorry.. :(");
    }

}
