import java.util.*;


class Solution
{
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int tc = 1; tc <= T; tc++){
            int N = sc.nextInt();
            int M = sc.nextInt();

            int tmp = N + M + 2;


            int[] arr = new int[N+M +1];
            for(int i = 1; i<=N; i++){
                for (int j = 1; j <=M; j++){
                    arr[i+j]++;
                }
            }
            System.out.print("#" + tc);
            int i = 0;
            int max = 0;
            while (true){
                max = Math.max(max, arr[i]);
                if(i == N+M){
                    break;
                }else if(arr[i] >= arr[i+1] && arr[i] == max && arr[i] != 0){
                    System.out.print(" " + i);
                }
                i++;

            }
            System.out.println();
        }
    }
}