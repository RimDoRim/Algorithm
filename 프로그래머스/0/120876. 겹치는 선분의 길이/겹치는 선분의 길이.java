class Solution {
    public int solution(int[][] lines) {
        int answer = 0;
        boolean[][] check = new boolean[3][210];

        for(int i = 0; i < 3; i++){
            int a = lines[i][0] + 100;
            int b = lines[i][1] + 100;
            for(int k = a; k < b; k++){
                check[i][k] = true;
            }
        }

        for(int t = 0; t < 210; t++){
            if((check[0][t] && check[1][t]) || (check[0][t] && check[2][t]) || (check[2][t] && check[1][t])){
                answer++;
            }
        }

        return answer;
    }
}