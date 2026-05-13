import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        //list 만들기
        List<List<Integer>> gra = new ArrayList<>();
        for (int i = 0; i <= n; i++) gra.add(new ArrayList<>());
        
        for (int[] e : edge) {
            gra.get(e[0]).add(e[1]);
            gra.get(e[1]).add(e[0]);
        }
        
        boolean[] visited = new boolean[n+1];
        
        visited[1] = true;
        
        List<Integer> que = new ArrayList<>();
        que.add(1);
        
        while (true) {
            List<Integer> nextQue = new ArrayList<>();
            
            for (int node : que) {
                for (int nei : gra.get(node)) {
                    if (!visited[nei]) {
                        visited[nei] = true;
                        nextQue.add(nei);
                    }
                }
            }
            
            if (nextQue.isEmpty()) break;
            que = nextQue;
        }
            
        
        return que.size();
    }
}

//멀리 떨어진 노드의 개수? -> bfs
//트루로 변할때마다 회차 담는 list 만들기 bool?
//예를들어 1 시작 → 1 t, 전체 tc +1
// 2, 3 queue in, t인 1 빼고  tc +1 
// 이런식으로?