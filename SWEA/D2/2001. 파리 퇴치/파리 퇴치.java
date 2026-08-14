
import java.util.*;
import java.lang.*;

//파리지옥

class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        int max = 0;

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[][] mat = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    mat[i][j] = sc.nextInt();
                }
            }

            int[][] sum = new int[N+1][N+1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    sum[i][j] = mat[i-1][j-1] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
                }
            }

            for (int k = 0; k <= N-M; k++) {
                for (int l = 0; l <= N-M; l++) {
                    int total = sum[k+M][l+M] - sum[k][l+M] - sum[k+M][l] + sum[k][l];
                    max = Math.max(max, total);
                }
            }
            System.out.println("#" + test_case + " " + max);
            max = 0;

        }

    }
}