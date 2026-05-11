class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        for( int i = 0; i < prices.length; i++){
            for(int j = i + 1; j < prices.length; j++){
                if(prices[i] - prices[j] <= 0){
                    answer[i] += 1;
                }else {
                    if(i != prices.length){
                        answer[i] +=1;
                    }
                    break;
                }
            }
        }
        
        
        return answer;
    }
}

//주식가격 배열 prices

//가격 유지 or 오른 기간 몇초인가?
//i번째 인덱스와 그 이후 모두 비교
//이중선택해서 카운트로 하면될듯?
