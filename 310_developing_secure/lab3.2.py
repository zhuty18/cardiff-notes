import math
n,e=(10142789312725007, 5)
d=8114231289041741

def maybe_p(m):
    if m%2==0:
        m-=1
    yield m
    for i in range(1,m//2-1):
        yield m-2*i
    yield 2

m=int(math.sqrt(n))
print(m)

p=0
q=0

for i in maybe_p(m):
    if n//i==n/i:
        p=i
        q=n//i
        print(p,q)
        break

ni=(p-1)*(q-1)
i=(e*d)%ni
print(i)