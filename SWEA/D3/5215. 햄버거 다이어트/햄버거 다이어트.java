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
            int L = sc.nextInt(); //limit
            int[] score = new int[N];
            int[] kcal = new int[N];
            int result = 0;

            for (int i = 0 ; i < N; i++){
                score[i] = sc.nextInt();
                kcal[i] = sc.nextInt();
            } //score랑 kcal 배열 만들었음

            for(int i = 0; i<(1<<N); i++) {
                int nowKcal = 0;
                int nowScore = 0;
                for(int j = 0; j<N; j++) {
                    if((i & (1<<j)) != 0) {
                        nowKcal += kcal[j];
                        nowScore += score[j];
                    }
                }
                if (nowKcal <= L && result <nowScore){
                    result = Math.max(result, nowScore);
                }
            }

            System.out.println("#" + tc + " " + result);
        }


    }
}

// 재료 수 제한 칼로리
// 같은 재료는 여러번사용할 수 없음
// a + b + c + d .. <= target
// 2^n 개를 선택하는 방법으로

