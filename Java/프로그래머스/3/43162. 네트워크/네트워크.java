class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n ; i++){
            if(!visited[i]){
                dfs(i, computers, visited);
                answer++;
            }
        }
            return answer;
   }        
        void dfs(int k, int[][] computers, boolean[] visited){
            visited[k] = true;
            for(int nxt = 0; nxt < computers.length ; nxt++){
                if(computers[k][nxt] == 1 & !visited[nxt]){
                    dfs(nxt, computers, visited);
                }
            }
        }
        
        

}
