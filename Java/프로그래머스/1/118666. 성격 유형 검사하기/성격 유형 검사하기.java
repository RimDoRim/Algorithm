import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String ans = "";
        
        //map 생성
        Map<String,Integer> map = new HashMap<>();
        
        String[] type = {"R","T","C","F","J","M","A","N"};
        
        for(int i = 0; i <type.length; i++){
            map.put(type[i], 0);
        }//점수 넣기
        
        for(int j = 0 ; j < survey.length ; j++){
            String f = survey[j].substring(0,1);
            String b = survey[j].substring(1,2);
            
            switch(choices[j]){
                case 1:
                    map.put(f, map.getOrDefault(f, 0) + 3);
                    break;
                case 2:
                    map.put(f, map.getOrDefault(f, 0) + 2);
                    break;
                case 3:
                    map.put(f, map.getOrDefault(f, 0) + 1);
                    break;
                case 5:
                    map.put(b, map.getOrDefault(b, 0) + 1);
                    break;
                case 6:
                    map.put(b, map.getOrDefault(b, 0) + 2);
                    break;
                case 7:
                    map.put(b, map.getOrDefault(b, 0) + 3);
                    break;
            }
        } //for j
        
        for(int k = 0; k< type.length ; k += 2){
            if (map.get(type[k]) >= map.get(type[k+1])){
                ans += type[k];
            }else{
                ans += type[k+1];
            }
        }
        
        
        return ans;
    }
}

//질문