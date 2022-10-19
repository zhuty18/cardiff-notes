import random
p=29

euler=[]

def gcd(a,b):
    if b==0:
        return a
    else:
        return gcd(b,a%b)

for i in range(1,p):
    if gcd(p,i)==1:
        euler.append(i)

n=len(euler)
g=0
for i in euler:
    k=1
    while((i**k)%p!=1):
        k+=1
        if k==n-1:
            g=i
            break
    # if g!=0:
    #     break


print(p,g)

a=random.randint(200,500)
b=random.randint(200,500)
print(a,b)

A=(g**a)%p
B=(g**b)%p

s1=(B**a)%p
s2=(A**b)%p

print(s1,s2)