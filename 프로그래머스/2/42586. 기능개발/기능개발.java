import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        int[]pc = new int[progresses.length];
        Queue<Integer> q = new LinkedList<>();
        List<Integer> counts = new ArrayList<>();

        for(int i = 0; i < progresses.length; i++){
            if((100 - progresses[i])%speeds[i] > 0 ){
                pc[i] = 1 + (100 - progresses[i])/speeds[i];
            }else{
                pc[i] = (100 - progresses[i])/speeds[i];
            }
            q.add(pc[i]);
        }

        while (!q.isEmpty()) {//q가 안빌때까지
            int current = q.remove();
            int count = 1;
            while (!q.isEmpty() && q.peek() <= current) {
                q.remove();//작거나 같으면 제거
                count++; // 카운트 추가
            }
            counts.add(count);//끝나면 그 숫자리스트에 넣기
        }
        
        int[] answer = new int[counts.size()]; // int 배열 길이를 만든다
        for (int i = 0; i < counts.size(); i++) {
            answer[i] = counts.get(i);
        }//int 배열에 넣는다
        return answer;
    }
}