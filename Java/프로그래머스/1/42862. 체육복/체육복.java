import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] std = new int[n+1];
        Arrays.fill(std, 1);
        
        for(int l : lost)
            std[l] = 0;
        
        Arrays.sort(reserve);
        
        for(int r : reserve){
            boolean isLost = false;
            for (int l : lost){
                if ( l == r){
                    isLost = true;
                    break;
                }
            }
            if (isLost)
                std[r] = 1;
            else if (std[r-1] == 0)
                std[r-1] = 1;
            else if (r+1 <= n && std[r+1] == 0)
                std[r+1] = 1;
        }
        
        for(int i = 1 ; i<=n ; i++)
            if (std[i] == 1)
                answer++;
        
        return answer;
    }
}