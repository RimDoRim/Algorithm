import java.util.*;

class Solution {

    // 4방향 이동량 (bfs에서 사용)
    int[] dr = {0, 0, -1, 1};
    int[] dc = {-1, 1, 0, 0};

    public int solution(String[] maps) {

        // 1. S, L, E 좌표 찾기 → 각각 {행, 열} 배열로 돌려받음
        int[] s = find(maps, 'S');
        int[] l = find(maps, 'L');
        int[] e = find(maps, 'E');

        // 2. 시작 → 레버 최단거리
        int toLever = bfs(maps, s[0], s[1], l[0], l[1]);
        if (toLever == -1) return -1;   // 레버에 못 가면 탈출 불가

        // 3. 레버 → 출구 최단거리
        int toExit = bfs(maps, l[0], l[1], e[0], e[1]);
        if (toExit == -1) return -1;    // 출구에 못 가면 탈출 불가

        // 4. 두 구간 합이 정답
        return toLever + toExit;
    }

    // 문자 a가 있는 칸의 좌표를 {행, 열}로 돌려줌
    public int[] find(String[] maps, char a) {
        int n = maps.length;        // 배열 → .length
        int m = maps[0].length();   // 문자열 → .length()

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maps[i].charAt(j) == a) {   // char 비교는 ==, 작은따옴표
                    return new int[]{i, j};     // 찾자마자 좌표를 상자에 담아 반환
                }
            }
        }
        return null;   // 못 찾은 경우 (문제 조건상 실행 안 됨, 컴파일용)
    }

    // (sr, sc)에서 (er, ec)까지 최단 이동 횟수, 못 가면 -1
    public int bfs(String[] maps, int sr, int sc, int er, int ec) {
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[]{sr,sc});
        
        int n = maps.length;
        int m = maps[0].length();
        
        boolean[][] visited = new boolean[n][m];
        int[][] dist = new int[n][m];
        
        visited[sr][sc] = true;
        dist[sr][sc] = 0;
        
        while(!q.isEmpty()){
            int[] tmp = q.poll();
            for(int d = 0; d < 4 ; d ++){
                int nr = tmp[0] + dr[d];
                int nc = tmp[1] + dc[d];
                
                if(nr < 0 || nc < 0 || nr  >= n || nc>=m) continue;
                if(maps[nr].charAt(nc) == 'X') continue;
                if(visited[nr][nc]) continue;
                
                q.add(new int[]{nr,nc});    
                visited[nr][nc] = true;
                dist[nr][nc] = dist[tmp[0]][tmp[1]] + 1;
            }
           
        }
        
        if(visited[er][ec]) return dist[er][ec];
        return -1;
    }
}