import java.util.*;


class Solution
{
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int count = 0;
            for(int i = 0; i < N ; i++){
                if(M%2 == 0){
                    System.out.println("#" + tc + " " + "OFF");
                    break;
                }
                if((M & (1 << i)) != 0 ){
                    count ++;
                }
                if(count == N){
                    System.out.println("#" + tc + " " + "ON");
                    break;
                }
                if(count != N && i == N-1){
                    System.out.println("#" + tc + " " + "OFF");
                }
            }



        }
    }
}

//정수 n,m
//((num & (1 << N)) != 0) 이면 ON 아니면 off
