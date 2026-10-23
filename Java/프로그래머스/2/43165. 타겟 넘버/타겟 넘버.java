import java.util.*;

class Solution {
    int[] numbers;
    int target;
    int ans = 0;
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        dfs(0,0);
        return ans;
    }
    
    
    
    public void dfs(int index, int sum){
        if(index == numbers.length){
            if(sum == target) ans++;
            return;
        }//만약 끝까지 도달했으면 인덱스가 멈출지말지 체크. 
        dfs(index + 1, sum + numbers[index]);
        dfs(index + 1, sum - numbers[index]);
    }
}


//노드로 생각해보자
//재귀함수 형태임, c에서 포인터 느낌
//넘버 수만큼 깊이를 내려갈거임
//프론트 백이 있는데 프론트면 더하기 백이면 빼기를 연산
