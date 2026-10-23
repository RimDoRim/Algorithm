import java.util.*;


class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int ans = 0;

        Map<String, Integer> w = new HashMap<>();
        
        
        for(int i = 0; i<want.length; i++){
            w.put(want[i], number[i]);
        }
        
        for(int day = 0; day <= discount.length - 10; day ++){
            Map<String, Integer> d = new HashMap<>();
            for(int j = day; j <day +10 ; j++){
                d.put(discount[j], d.getOrDefault(discount[j],0) + 1);
            }
            
            boolean flag = true;
            
            for(int k = 0; k < want.length; k++){
                if(!w.get(want[k]).equals(d.get(want[k]))){
                    flag = false;
                    break;
                }
            }
            if (flag) ans ++;
        }

        
        
        return ans;
    }
}

//10일 동안 회원
//한가지 할인 품목 하루에 하나씩 살 수 있음
// 할인 가능한 날짜 int 
// want, number를 담을 수 있는 hashmap
// discount 에서 하나씩 꺼내서 있으면
// discount 에서 Day ~ day + 9 까지의 hashmap을 만듦
// 해시맵1에서 품목꺼냈을때 해시맵2과 수량 동일하면 다음품목 검사
// 해시맵1에 대해 모두 끝났으면 해당 ans ++ 
// ans return