class Solution {
    public String solution(String new_id) {
        new_id = new_id.toLowerCase();
        
        new_id = new_id.replaceAll("[^a-z0-9\\-_.]", "");
        
        new_id = new_id.replaceAll("\\.+", ".");
        //여긴 수정필요
        
        new_id = new_id.replaceAll("^\\.|\\.$", "");
        
        if (new_id.isEmpty()) new_id = "a";
        
        if (new_id.length() >= 16){
            new_id = new_id.substring(0,15);
            new_id = new_id.replaceAll("^\\.|\\.$", "");
        }
        
        while(new_id.length() < 3){
            new_id += new_id.charAt(new_id.length() - 1) ;
        }
        
        return new_id;
    }
}

//아이디 길이는 3- 15자  소문자,숫자,-_. 만 가능
//.는 처음과 끝에 사용불가, 연속 불가
//아래 단계 거치기
//toLowerCase
//replaceAll("[^a-z0-9\\-_.]", "")
//replaceAll("\\.\\.", ".") 반복한다..?
//replaceAll("^\\.|\\.$", "")
//if len < 1 -> a , 
// len >16 -> i= 16 ~ len 까지 제거, 리플레이스만 한번더
//if len < 3 -> 반복