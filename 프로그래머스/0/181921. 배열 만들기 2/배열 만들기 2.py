def solution(l, r):
    result = []

    for n in range(l,r+1):
        flag = True
        env = ''
        list_n = list(str(n))
        for j in list_n:
            if j == '5' or j == '0':
                env += j
            else:
                flag = False
                break
            
        if flag == True:
            result.append(int(env))

    if len(result) == 0:
        return [-1]
    else:
        return result
            
        