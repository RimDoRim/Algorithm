import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
            
        HashSet<String> set = new HashSet<>(Arrays.asList(report));
        HashMap<String, Integer> cm = new HashMap<>();      
        HashMap<String, Integer> mm = new HashMap<>();   
        
        for (String s : set){
            String[] ss = s.split(" ");
            String b = ss[1];
            cm.put(b, cm.getOrDefault(b, 0) + 1);
        }

        for (String s : set){
            String[] ss = s.split(" ");
            String a = ss[0];
            String b = ss[1];
            if(cm.get(b) >= k){
                mm.put(a, mm.getOrDefault(a, 0) + 1);
            }
        }
        
        for (int i = 0; i < id_list.length; i++){
            answer[i] = mm.getOrDefault(id_list[i], 0);
        }
        
        return answer;

    }
}

//유저는 한번에 한명 신고 (매핑된다)
//동일 유저 신고는 중복허용 x (카운트필요없음)
//서로 다른 K명에게 신고 당하면 이용 정지, k명에게 메일 발송 나중에 처리함
//K번 이상 메일 받은 횟수 리턴

//중복 허용 안되니깐 리포트를 hashset으로 처리
//id list에 카운트 추가해서 result
//split 으로 a,b 구분하기
//b랑 매칭하여 카운트 추가하기
//다 돌고 k 보다 b 카운트가 많으면 a랑 동일한 id list 카운트에 추가하기
