
import java.util.*;



class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        for(int tc = 1; tc <= 10; tc++)
        {
            int tcc = sc.nextInt();
            int maxS = 0; //최대값
            //배열 만들기
            int[][] arr = new int[100][100];
            for(int i = 0; i < 100; i++){
                for (int j = 0; j < 100; j++){
                    arr[i][j] = sc.nextInt();
                }
            } //배열 채우기

            //행합구하기 -> sum행 1등이랑 sum열 1등이랑 비교하기
            int rSum = 0;
            for(int i = 0; i < 100; i++){
                for (int j = 0; j < 100; j++){
                    rSum += arr[i][j];
                }
                if(maxS < rSum){
                    maxS = rSum;
                }
                rSum = 0;
            }
            //열합구하기
            int cSum = 0;
            for(int j = 0; j < 100; j++){
                for (int i = 0; i < 100; i++){
                    cSum += arr[i][j];
                }
                if(maxS < cSum){
                    maxS = cSum;
                }
                cSum = 0;
            }

            int iSum = 0;
            for(int i = 0; i < 100; i++){
                iSum += arr[i][i];
                }
            if(maxS < iSum) {
                maxS = iSum;
            }
            System.out.println("#" + tc + " " +maxS);


        }

    }
}

//100*100 크기 고정, int 범위
//#t 100*100개 숫자