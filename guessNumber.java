import java.util.Scanner;

public class guessNumber {
    static int genRandNum() {
        return (int)(Math.random()*10);
    }
    static void printNumber(int[] number) {
        for(int i=0; i < number.length; ++i)
            System.out.print(number[i]);
        System.out.println();
    }

    static boolean isDuplicate(int[] guess)
    {
        int number[] = new int[10];
        for(int i=0; i < guess.length; ++i)
            if(number[guess[i]] > 0) return true;
            else number[guess[i]]++;
        return false;
    }
    
    static void guessNumber() {
        Scanner input = new Scanner(System.in);
        
        
        int guess[] = new int[4];

        while(isDuplicate(guess))
        {
            System.out.print("Guess a number: ");
            int number = input.nextInt();
            
            // check if the number is valid
            if(number < 0 || number > 9999)
                continue;
            // split a four-digit number into four numbers
            guess[0] = number/1000;
            number%=1000;
            guess[1] = number/100;
            number%=100;
            guess[2] = number/10;
            number%=10;
            guess[3] = number;
        }
        
        // close the scanner object
        input.close();
    }
    
    public static void main(String[] args) {
        int secret[] = new int[4];

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
        guessNumber();
    }
}