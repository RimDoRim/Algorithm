def solution(code):
    ret = ''
    idx = 0
    mode= 0
     
    while idx <= len(code) -1:
        if code[idx] == '1' and mode == 1:
            mode = 0
            idx += 1
        elif code[idx] == '1' and mode == 0:
            mode = 1    
            idx += 1
        elif code[idx] != 1 and mode == 0 and idx%2 == 0:
            ret += code[idx]
            idx += 1
        elif code[idx] != 1 and mode == 1 and idx%2 != 0:
            ret += code[idx]
            idx += 1
        else:
            idx += 1
            
    if len(ret) == 0:
        return 'EMPTY'
    else:
        return ret
