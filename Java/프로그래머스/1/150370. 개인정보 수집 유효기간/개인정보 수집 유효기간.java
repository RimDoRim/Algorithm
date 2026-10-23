import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        Map<String, Integer> termMap = new HashMap<>();
        
        for(String t : terms){
            String[] sp = t.split(" ");
            termMap.put(sp[0], Integer.parseInt(sp[1]));
        } //terms를 map에 담았음
        
        int todays = toDays(today);
        
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i <privacies.length; i++){
            String[] p = privacies[i].split(" ");
            int target = toDays(p[0]) + termMap.get(p[1])*28;
            if(target <= todays){
                list.add(i+1);
            }
        }
        
        int[] ans = new int[list.size()];
        for(int i = 0; i <list.size(); i++){
            ans[i] = list.get(i);
        }
        return ans;
        
        
    }
    
    public int toDays(String today){
        String[] tmp = today.split("\\.");
        
        int y = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        int d = Integer.parseInt(tmp[2]);
        
        return y*12*28 + m*28 + d;
        
    }
    
    
}