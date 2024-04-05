public class guessingNumber {
    static int genRandNum() {
        return (int)(Math.random()*10);
    }
    public static void main(String[] args) {
        int secret[] = new int[4];

        // generate number from 1-9
        System.out.println(genRandNum());
    }
}