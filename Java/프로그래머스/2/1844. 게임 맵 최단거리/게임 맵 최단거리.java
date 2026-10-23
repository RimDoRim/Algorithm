import java.util.*;

class Solution {
    //방향 설정
    int[] dr = {0,0,-1,1};
    int[] dc = {1,-1,0,0};
    
    public int solution(int[][] maps) {
        
        int n = maps.length;
        int m = maps[0].length;
        
        //q만들기
        Queue<int[]> q = new LinkedList<>();
        
        //첫 지점 넣기
        q.add(new int[] {0,0});
        
        //배열 만들기
        boolean[][] v = new boolean[n][m];
        int[][] dist = new int[n][m];
        dist[0][0] = 1; //시작점
        
        
        while(!q.isEmpty()){
            //꺼내기
            int[] now = q.poll();
            
            for(int d = 0; d < 4 ; d ++){
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                
                //배열 밖이거나 이미 간곳이거나 못가는곳이면 컨티뉴
                if(nr < 0 || nc <0 || nr >= n || nc>= m) continue;
                if(v[nr][nc]) continue;
                if(maps[nr][nc] != 1) continue;
                
                //갈수있으면 탐색
                
                q.add(new int[] {nr,nc});
                v[nr][nc] = true;
                dist[nr][nc] = dist[now[0]][now[1]] + 1;
                
            }//for
            
        }
        if(v[n-1][m-1]) return dist[n-1][m-1];
        return -1;
    }
}