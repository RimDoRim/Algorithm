import java.util.Scanner;

class Solution   // 여기를 반드시 Solution으로!
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int c = N;
            String[][] mat = new String[N][N];
            int num = 1;
            int count = 1;
            int i = 0, j = 0;
            while (num <= N * N) {
                mat[i][j] = String.valueOf(num);
                if (count < c) {
                    j++;
                } else if (count < 2 * c - 1) {
                    i++;
                } else if (3 * c - 2 > count) {
                    j--;
                } else if (4 * c - 4 > count) {
                    i--;
                }
                if (count == 4 * c - 4) {
                    j = ++j;
                    c = c - 2;
                    count = 0;
                }
                num++;
                count++;
            }

            System.out.println("#" + test_case );
            StringBuilder sb = new StringBuilder();
            for (int r = 0; r < N; r++) {

                for (int col = 0; col < N; col++) {
                    sb.append(mat[r][col]);
                    if (col < N - 1) sb.append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
        }
    }
}