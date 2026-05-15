import java.util.*;

class Solution {
    public int solution(String[] maps) {
        //✅ 주어진 입력을 사용하기 좋게 전처리한다.
        int n = maps.length;
        int m = maps[0].length();
        char[][] maze = new char[n][m];
        int[] start = new int[2];
        int[] lever = new int[2];
        int[] exit = new int[2];

        for (int r = 0; r < n; r++) {
            maze[r] = maps[r].toCharArray();
            for (int c = 0; c < m; c++) {
                if (maze[r][c] == 'S') {
                    start[0] = r;
                    start[1] = c;
                } else if (maze[r][c] == 'L') {
                    lever[0] = r;
                    lever[1] = c;
                } else if (maze[r][c] == 'E') {
                    exit[0] = r;
                    exit[1] = c;
                }
            }
        }

        //✅ 시작 지점 -> 레버까지 최단거리를 구한다. 
        int toLever = bfs(maze, start, lever, n, m);
      	//✅ 경로가 없다면 -1을 반환한다.
        if (toLever == -1) return -1;
        
        //✅ 레버 -> 출구까지 최단거리를 구한다. 
        int toExit = bfs(maze, lever, exit, n, m);
      	//✅ 경로가 없다면 -1을 반환한다.
        if (toExit == -1) return -1;
        
        //✅ 두 개의 최단거리를 더한 값을 반환한다.
        return toLever + toExit;
    }

    private int bfs(char[][] maze, int[] start, int[] target, int n, int m) {
        boolean[][] visited = new boolean[n][m];
        int[] dr = {0, 0, -1, 1};
        int[] dc = {-1, 1, 0, 0};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start[0], start[1], 0}); // 위치와 현재까지의 거리를 함께 저장
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            int r = current[0];
            int c = current[1];
            int distance = current[2];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (nr >= 0 && nc >= 0 && nr < n && nc < m && maze[nr][nc] != 'X') {
                    if (!visited[nr][nc]) {
                        if (nr == target[0] && nc == target[1]) return distance + 1;
                        
                        queue.offer(new int[]{nr, nc, distance + 1});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        return -1; // 목표 지점에 도달할 수 없는 경우
    }
}