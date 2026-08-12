import java.util.HashSet;
import java.util.Scanner;
import java.io.FileInputStream;


//hash set 문제
// i*N 을 set에 담기
// 만약 사이즈가 10이면 종료

class Solution
{
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int i = 1;
            int c = 0;
            System.out.print("#" + test_case + " ");
            HashSet<Integer> set = new HashSet<>();

            while (set.size() < 10){
                String target = String.valueOf((Integer) i*N);
                for (char n : target.toCharArray() ) {
                    set.add(n - '0');
                }
                i++;
                c++;
            }
            System.out.println(c*N);



        }
    }
}