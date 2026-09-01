import java.util.Scanner;


class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        char[][] arr = new char[100][100];
        int T = 10;
        int answer = 0;

        for (int tt = 1; tt <= T; tt++) {
            int tmp = sc.nextInt();
            for (int i = 0; i < 100; i++) {
                arr[i] = sc.next().toCharArray();
            }


            int N = arr.length;
            int M = arr[0].length;
            int[][] arr2 = new int[M][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    arr2[j][N - 1 - i] = arr[i][j];
                }
            } //세로 배열 계산용


            //회문 체크

            int now = 0;
            while (now < 100) {
                for (int i = now + 1; i < 100; i++) { // i는 끝점 포인터
                    for (int k = 0; k < 100; k++) {
                        // 가로
                        String sliced = new String(arr[k], now, i - now+1);
                        String rSliced = new StringBuilder(sliced).reverse().toString();
                        if (sliced.equals(rSliced)) {
                            answer = Math.max(answer, sliced.length());
                        }

                        // 세로
                        String sliced2 = new String(arr2[k], now, i - now +1);
                        String rSliced2 = new StringBuilder(sliced2).reverse().toString();
                        if (sliced2.equals(rSliced2)) {
                            answer = Math.max(answer, sliced2.length());
                        }
                    }
                }
                now++;
            }
            System.out.println("#" + tmp + " " + answer);
            answer = 0;
        }

    }


}
