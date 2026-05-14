import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        
        int n = maps.length;
        int m = maps[0].length;
        
        int[] dc = {0,-1,1,0};
        int[] dr = {-1,0,0,1};
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 1});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];

            if(r == n-1 && c == m-1) {
                return dist;
            }

            for(int i = 0; i <4; i ++){
                int nc = c + dc[i];
                int nr = r + dr[i];
                if(nr >= 0 && nr < n && nc >= 0 && nc < m
                && maps[nr][nc] == 1) {
                    maps[nr][nc] = 0;
                    q.offer(new int[]{nr, nc, dist+1});
                }
            }
        } 
        return -1;
    }
}

//maps가 매개변수
//최솟값 리턴
//도착불가시 -1
//최소거리 -> bfs