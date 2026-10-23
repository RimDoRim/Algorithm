import java.util.*;

class Solution {
    
    //상하좌우
    int[] dr = {0,0,-1,1};
    int[] dc = {1,-1,0,0};
    
    public int solution(int[][] maps) {
        int ans = 0;
        
        //st = maps[0][0]
        
        int n = maps.length; 
        int m = maps[0].length;
        
        boolean[][] visited = new boolean[n][m];
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0}); //대기열 넣기
        visited[0][0] = true;
        
        int[][] dist = new int[n][m];
        
        dist[0][0] = 1;
        
        while(!q.isEmpty()){
            int[] cur = q.poll(); //꺼내서 담기, 처음엔 0,0
            for(int d = 0; d < 4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                
                //밖이거나 방문했거나 못가는 곳이면 컨티뉴
                if(nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                if(visited[nr][nc]) continue;
                if(maps[nr][nc] == 0) continue;
                //갈 수 있으면 체크
                visited[nr][nc] = true;
                q.add(new int[]{nr,nc});
                dist[nr][nc] = dist[cur[0]][cur[1]] + 1;
            }
        }
        
        if (visited[n-1][m-1]) return dist[n-1][m-1];
        return -1;
        
        
    }
}