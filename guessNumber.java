import java.util.Scanner;

public class guessNumber {
    static int genRandNum() {
        return (int)(Math.random()*10);
    }
    static void printNumber(final int[] number) {
        for(int i=0; i < number.length; ++i)
            System.out.print(number[i]);
        System.out.println();
    }

    static boolean isValid(final int[] guess) {
        int appear[] = new int[10];
        for(int i=0; i < guess.length; ++i) {
            if(guess[i] < 0 || guess[i] > 9) return false;
            if(appear[guess[i]] > 0) return false;
            else appear[guess[i]]++;
        }
        return true;
    }

    
    static void getAB(final int[] secret, final int[] guess, int[] AB) {
        AB[0] = 0;
        AB[1] = 0;
        // record appeard numbers without the number in the right position
        int appear[] = new int[10];
        for(int i=0; i < secret.length; ++i) {
            if(secret[i] == guess[i]) AB[0]++;
            else {
                if(appear[guess[i]] > 0) AB[1]++;
                else appear[guess[i]]++;

                if(appear[secret[i]] > 0) AB[1]++;
                else appear[secret[i]]++;
            }
        }
    }

    public static void main(String[] args) {
        int secret[] = new int[4];
        int guess[] = new int[4];

        // AB[0] for a correct number in the correct position
        // AB[1] for a correct number in a wrong position
        int AB[] = new int[2];

        // generate secret numbers

        // generate number from 1-9
        secret[0] = genRandNum();
        secret[1] = genRandNum();
        secret[2] = genRandNum();
        secret[3] = genRandNum();
        // avoid a duplcate number
        while(secret[1]==secret[0]) 
            secret[1] = genRandNum();
        while(secret[2]==secret[1] || secret[2]==secret[0])
            secret[2] = genRandNum();
        while(secret[3]==secret[2] || secret[3]==secret[1] || secret[3]==secret[0])
            secret[3] = genRandNum();

        printNumber(secret);

        Scanner input = new Scanner(System.in);
        // start guessing
        while(true) {
            // initialize guess
            for(int i=0; i < guess.length; ++i) guess[i] = 0;

            // input until it is valid            
            while(true) {
                System.out.print("Guess a number: ");
                int number = input.nextInt();
                
                // split a four-digit number into four numbers
                guess[0] = number/1000;
                number%=1000;
                guess[1] = number/100;
                number%=100;
                guess[2] = number/10;
                number%=10;
                guess[3] = number;

                if(isValid(guess))
                    break;
                else System.out.println("Invalid Input");
            }
            
            getAB(guess, secret, AB);
            if(AB[0]>0) System.out.print(AB[0]+"A");
            if(AB[1]>0) System.out.println(AB[1]+"B");
            else System.out.println();

            if(AB[0] == 4) break;
        }
        
        
    }
}