public class guessingNumber {
    static int genRandNum() {
        return (int)(Math.random()*10);
    }
    static void printSecret(int[] secret) {
        for(int i=0; i < secret.length; ++i)
            System.out.print(secret[i]);
        System.out.println();
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

        printSecret(secret);
    }
}