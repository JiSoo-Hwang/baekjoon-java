public class CompareSumMethods {
    public static void main (String[]args){
        String numStr = "763246918234";
        int repeat = 100000;
        long start1 = System.nanoTime();
        for(int r=0;r<repeat;r++){
            int sum1 = 0;
            for(int i=0;i<numStr.length();i++){
                sum1+= numStr.charAt(i)-'0';
            }
        }
        long end1 = System.nanoTime();
        System.out.println("charAt 방식 소요 시간 : "+(end1-start1));
        long start2= System.nanoTime();
        for(int r=0;r<repeat;r++){
            int sum2 =0;
            String[]digits = numStr.split("");
            for(int i=0;i<numStr.length();i++){
                sum2+=Integer.parseInt(digits[i]);
            }
        }
        long end2 = System.nanoTime();
        System.out.println("split 방식 소요 시간 : "+(end2-start2));
    }
}
