import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String,Integer> map = new HashMap<>();
        
        for (String p : participant){
            map.put(p, map.getOrDefault(p,0) + 1);
        }
        
        for (String c : completion){
            if (map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                    }
            }
        
        for (String m : map.keySet()){
            if(map.get(m) > 0){
                return m;
            }
        }
        return answer;
    }
}


//참가자 배열 participant 완주 배열 completion
//완주 못한 선수 return, 1명임 참가자 이름은 알파벳
//동면이인이 있을 수 있다.
// 완주 배열에서 선택한 후 참가자 배열에 있는지 확인하기 (hashmap)
//completion을 hashmap으로 만들고 카운트 진행
//participant에서 있으면 카운트 1빼기
//카운트가 1인거 찾기
// 리턴


