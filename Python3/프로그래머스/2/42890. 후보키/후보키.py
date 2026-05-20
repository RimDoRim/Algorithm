from itertools import combinations

def solution(relation):
    n_col = len(relation[0])
    candidates = []
    
    for r in range(1, n_col + 1):
        for cols in combinations(range(n_col), r):
            # 유일성: 뽑은 컬럼 조합으로 만든 튜플이 중복 없는지
            tuples = [tuple(row[c] for c in cols) for row in relation]
            if len(tuples) != len(set(tuples)):
                continue
            # 최소성: 기존 후보키의 부분집합이 아닌지
            if any(set(c).issubset(set(cols)) for c in candidates):
                continue
            candidates.append(cols)
    
    return len(candidates)