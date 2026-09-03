import java.util.*;


class Solution
{
    static int[] dx1 = {0, 0, -1, 1}; //십자모양용
    static int[] dy1 = {1, -1, 0, 0};
    static int[] dx2 = {-1, 1, 1, -1}; //x자 모양용
    static int[] dy2 = {1, 1, -1, -1};


    public static int spray(int[][] arr, int M, int N){
        int count = 0;
        for(int i = 0; i <N; i++){
            for(int j = 0; j< N; j++){
                int tmp = 0;
                tmp += arr[i][j];
                for (int dir = 0; dir < 4; dir++) {

                    int k = 1;
                    while (k < M) {
                        int nx = i, ny = j;
                        nx += k*dx1[dir];
                        ny += k*dy1[dir];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) break; // 범위 벗어남

                        tmp += arr[nx][ny];
                        k++;

                    }
                }
                int tmp2 = 0;
                tmp2 += arr[i][j];
                for (int dir = 0; dir < 4; dir++) {

                    int p = 1;
                    while (p < M) {
                        int nx2 = i, ny2 = j;
                        nx2 += p*dx2[dir];
                        ny2 += p*dy2[dir];

                        if (nx2 < 0 || ny2 < 0 || nx2 >= N || ny2 >= N) break; // 범위 벗어남

                        tmp2 += arr[nx2][ny2];
                        p++;

                    }
                }
                count = Math.max(count, Math.max(tmp,tmp2));

            }
        }
        return count;

    }

    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int tc = 1; tc <= T; tc++)
        {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] arr = new int[N][N];

            for(int i = 0; i<N; i++){
                for(int j = 0; j <N ; j++){
                    arr[i][j] = sc.nextInt();
                }
            } // 배열 생성

            int result = spray(arr,M,N);

            System.out.println("#" + tc + " " + result);
        }
    }
}

//배열의 크기는 N
//분사되는 길이(중심포함) M
//분사하는 함수 작성 + x 방향
//분사되는 길이 직전까지
// ++ 시키기