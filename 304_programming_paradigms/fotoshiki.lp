#const n=3.
larger(1,1,1,2).
at(3,1,1).

{at(1..n,R,C)}=1:-R=1..n,C=1..n.
{at(N,R,1..n)}=1:-N=1..n,R=1..n.
{at(N,1..n,C)}=1:-N=1..n,C=1..n.
:-larger(R1,C1,R2,C2), at(X1,R1,C1),at(X2,R2,C2),X1<X2.

#show at/3.