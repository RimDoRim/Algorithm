def solution(i, j, k):
    k = str(k)
    answer = 0
    a= ""
    
    while i <= j :
        a += str(i)
        i = i+1
        
    for b in a :
        if b == k :
            answer += 1 
    
    return answer