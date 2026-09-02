import java.util.*;


class Solution
{
    public static int scann(int arr[][],int arr2[][], int N, int k){
        int num = 0;
        int tmp = 0;
        int tmp2 = 0;
        for(int i = 0; i <N; i++){
            tmp = 0;
            for(int j = 0; j <(N-1); j++){
                if(arr[i][j] == 1 && arr[i][j+1] == 1){
                    tmp ++;
                    if (j == N-2 && k == ++tmp){
                        num ++;
                        tmp = 0;
                    }
                }else if (arr[i][j] == 1 && arr[i][j+1] ==0){
                    if(k == ++tmp){
                        num ++;
                        tmp = 0;
                    }else tmp = 0;
                }
                if(arr[i][j] == 0){
                    tmp =0;
                }
            }
        }

        for(int i = 0; i <N; i++){
            tmp2 = 0;
            for(int j = 0; j <(N-1); j++){
                if(arr2[i][j] == 1 && arr2[i][j+1] == 1){
                    tmp2 ++;
                    if (j == N-2 && k == ++tmp2){
                        num ++;
                        tmp2 = 0;
                    }
                }else if (arr2[i][j] == 1 && arr2[i][j+1] ==0){
                    if(k == ++tmp2){
                        num ++;
                        tmp2 = 0;
                    }else tmp2 = 0;
                }
                if(arr2[i][j] == 0){
                    tmp2 =0;
                }
            }
        }

        return num;
    }

    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int tc = 1; tc <= T; tc++)
        {
            int N = sc.nextInt();
            int k = sc.nextInt();
            int[][] arr = new int[N][N];
            int[][] arr2 = new int[N][N]; //회전 배열

            //배열에 값 넣기
            for(int i = 0; i < N; i++){
                for(int j = 0; j<N; j++){
                    arr[i][j] = sc.nextInt();
                    arr2[j][N-1-i] = arr[i][j];
                }
            }

            int result = scann(arr,arr2,N,k);

            System.out.println("#" + tc +" " + result);

        }
    }
}

