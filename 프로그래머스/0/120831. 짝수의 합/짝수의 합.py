

def solution(n):
    if n%2 == 0 :
        answer = sum(range(n,0,-2))
        print(list(range(n,0,-2)))
    else:
        answer = sum(range(n-1,0,-2))
        print(list(range(n-1,0,-2)))
    return answer