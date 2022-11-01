#const n=5.
%larger(R1,C1,R2,C2).
larger(1,1,1,2).
larger(1,3,1,4).
larger(1,4,1,5).
larger(4,5,4,4).
larger(5,3,5,2).
larger(5,2,5,1).
%at(N,R,C).
at(4,2,1).
at(2,2,5).
at(4,3,3).
at(4,4,5).

{at(1..n,R,C)}=1:-R=1..n,C=1..n.
{at(N,R,1..n)}=1:-N=1..n,R=1..n.
{at(N,1..n,C)}=1:-N=1..n,C=1..n.
X1>X2:-larger(R1,C1,R2,C2), at(X1,R1,C1),at(X2,R2,C2).

#show at/3.