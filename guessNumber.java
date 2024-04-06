import java.util.Scanner;

public class guessNumber {
    static final int DIGIT=4;
    static final int VALID_NUM=10;

    static abstract class Number {
        protected int numbers[];
        Number() { 
            numbers = new int[DIGIT]; 
        }

        abstract void genNumbers();
        
        final int[] getNumbers() {
            return numbers;
        }

        void printNumber() {
            for(int i=0; i < DIGIT; ++i)
                System.out.print(numbers[i]);
            System.out.println();
        }

        void getAB(Number num, int[] AB) {
            // initialize AB
            AB[0]=AB[1]=0;
            int nums[] = num.getNumbers();
            
            // record appeard numbers without the number in the right position
            int appear[] = new int[VALID_NUM];
            for(int i=0; i < DIGIT; ++i) {
                if(numbers[i] == nums[i]) AB[0]++;
                else {
                    if(appear[numbers[i]] > 0) AB[1]++;
                    else appear[numbers[i]]++;

                    if(appear[nums[i]] > 0) AB[1]++;
                    else appear[nums[i]]++;
                }
            }
        }
    }

    static class Secret extends Number {
        Secret() {
            genNumbers();
            printNumber();
        }
        void genNumbers() {
            // generate number from 1-9
            numbers[0] = genRandNum();
            numbers[1] = genRandNum();
            numbers[2] = genRandNum();
            numbers[3] = genRandNum();

            // avoid a duplcate number
            while(numbers[1]==numbers[0]) 
                numbers[1] = genRandNum();
            while(numbers[2]==numbers[1] || numbers[2]==numbers[0])
                numbers[2] = genRandNum();
            while(numbers[3]==numbers[2] || numbers[3]==numbers[1] || numbers[3]==numbers[0])
                numbers[3] = genRandNum();
        }
        
        private int genRandNum() {
            return (int)(Math.random()*VALID_NUM);
        }   
    }

    static class Guess extends Number {
        Scanner input = new Scanner(System.in);

        void genNumbers() {    
            while(true) {
                System.out.print("Guess a number: ");
                int number = input.nextInt();
                    
                // split a four-digit number into four numbers
                numbers[0] = number/1000;
                number%=1000;
                numbers[1] = number/100;
                number%=100;
                numbers[2] = number/10;
                number%=10;
                numbers[3] = number;

                if(isValid()) return;
                else 
                    System.out.println("Invalid input!");
            }
        }
        private boolean isValid() {
            int appear[] = new int[VALID_NUM];
            for(int i=0; i < DIGIT; ++i) {
                if(numbers[i] < 0 || numbers[i] > 9) return false;
                if(appear[numbers[i]] > 0) return false;
                else appear[numbers[i]]++;
            }
            return true;
        }
    }


    public static void main(String[] args) {
        Number secret = new Secret();
        Number guess = new Guess();
        
        int AB[] = new int[2];

        while(true) {
            guess.genNumbers();
            guess.getAB(secret, AB);

            if(AB[0]>0) System.out.print(AB[0]+"A");
            if(AB[1]>0) System.out.println(AB[1]+"B");
            else System.out.println();

            if(AB[0] == DIGIT)
                break;

        }
    }
}