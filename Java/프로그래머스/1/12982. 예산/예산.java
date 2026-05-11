import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        int remain = budget; // 남은금액
        int sum = 0; //배열내부 합
        
        //배열 내부 합 구하기 
        for (int i = 0; i < d.length; i ++){
            sum += d[i];
        }
        
        //sum of d < budget 이면 len of d 리턴
        
        if (sum <= budget){
            return d.length;
        }
        
        //아니면 정렬 후 작은금액부터 빼기 (많은 부서에 지원목적)
        // 남은 금액이 (선언) 0보다 작기 전에 종료
        
        Arrays.sort(d);
        
        for (int i = 0; i < d.length ; i++){
            if (remain >= 0){
                remain -= d[i];
                if(remain < 0){
                    return answer;
                }else{
                  answer += 1;        
                }
            } else {
                return answer;
            }
        }
        return answer;
    }
}

//최대한 많은 부서 물품 구매
//금액 배열 d 예산 budget
// 최대 부서 개수 answer