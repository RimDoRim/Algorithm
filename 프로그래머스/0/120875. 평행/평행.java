import java.util.*;

class Solution {
    public int solution(int[][] dots) {
        int answer = 0;
        //[][x=0 y=1]
        if(Math.abs((dots[0][0] - dots[1][0]))* Math.abs((dots[2][1] - dots[3][1])) == Math.abs((dots[0][1] - dots[1][1]))* Math.abs((dots[2][0] - dots[3][0]))){
                return 1;
        }
        if(Math.abs((dots[0][0] - dots[2][0]))* Math.abs((dots[1][1] - dots[3][1])) ==  Math.abs((dots[0][1] - dots[2][1]))* Math.abs((dots[1][0] - dots[3][0])) ){
                return 1;
        }      
        if(Math.abs((dots[0][0] - dots[3][0])) *Math.abs((dots[2][1] - dots[1][1])) ==  Math.abs((dots[0][1] - dots[3][1]))* Math.abs((dots[2][0] - dots[1][0])) ){
                return 1;
        }
        
        
        return answer;
    }
}

//x1 x2 / x3 x4 비교 -> 같으면 ->  y1 y2 / y3 y4 비교 -> 같으면 리턴 1 
//x1 x3 / x2 x4 비교 -> 같으면 ->  y1 y3 / y2 y4 비교 -> 같으면 리턴 1 
//x1 x4 / x2 x3 비교 -> 같으면 ->  y1 y4 / y2 y3 비교 -> 같으면 리턴 1 
//아니면 리턴 0
//근데 길이가 다를땐?