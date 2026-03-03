def solution(babbling):
    answer = 0
    for i in babbling :
        k = 0
        while k < len(i):
            if i[k:k+2] in ['ye' , 'ma']:
                k += 2
            elif i[k:k+3] in ['aya' , 'woo']:
                k += 3
            else:
                break
            if k == len(i):
                answer += 1
            
    return answer