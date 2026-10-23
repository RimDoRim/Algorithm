import java.util.*;

class Solution {
    
    int[] dr = {0,0,1,-1};
    int[] dc = {1,-1,0,0};
    
    public int solution(int[][] maps) {
        
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m]; //방문여부
        int[][] dist = new int[n][m]; // 거리
        
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[]{0,0}); //처음 위치 넣기
        visited[0][0] = true;
        dist[0][0] = 1; //시작점 세팅
        
        while(!q.isEmpty()){
            //cur를 만듦 -> poll 하고 현위치 기억하기 위해
            int[] cur = q.poll(); //cur[0] cur[1]
            for(int d = 0 ; d < 4 ; d ++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                
                //인덱스 벗어났거나 방문했거나 못가는곳이면 넘기기
                if(nr < 0 || nc < 0 || nr >= n || nc >= m || visited[nr][nc] || maps[nr][nc] == 0) continue;
                
                //아니면 방문 때리기
                q.add(new int[]{nr,nc});
                visited[nr][nc] = true;
                dist[nr][nc] = dist[cur[0]][cur[1]] + 1;
            }//for
        }
        
        if(visited[n-1][m-1]) return dist[n-1][m-1]; //만약 목표칸이 비지티드면 
        return -1;
        

    }//sol
}