import java.util.*;


class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        for(int T = 1; T <= 10; T++)
        {
            int N = sc.nextInt();
            int s = 0;
            int m = 0;
            int l = 0;
            int k = 0;
            int answer = 0;
            String str = sc.next();


            for(int i = 0; i<N; i++){
                switch (str.charAt(i)){
                    case '(':
                        s++;
                        break;
                    case '{':
                        m++;
                        break;
                    case '[':
                        l++;
                        break;
                    case '<':
                        k++;
                        break;
                    case ')':
                        s--;
                        break;
                    case '}':
                        m--;
                        break;
                    case ']':
                        l--;
                        break;
                    case '>':
                        k--;
                        break;
                    default:
                        break;
                }
                if( s< 0 || m <0 || l < 0 || k < 0){
                    answer = 0;
                    break;
                }else if (!(s==0) || !(m==0) || !(l==0) || !(k==0)) {
                    answer = 0;
                }
                else if ( s== 0 && m ==0|| l ==0|| k ==0) {
                    answer = 1;
                }
            }

            System.out.println("#" + T + " " + answer);
        }
    }
}