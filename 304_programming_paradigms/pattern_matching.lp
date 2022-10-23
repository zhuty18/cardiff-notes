%T=(5,3,1,4,2),P=(2,3,1).
t(1,5;2,3;3,1;4,4;5,2).
p(1,2;2,3;3,1).
#const k=3.

{subseq(K,I,E):t(I,E)}=1:-K=1..k.
I1<I2:-subseq(K1,I1,_),subseq(K2,I2,_),K1<K2.
EP1<EP2:-subseq(K1,_,ET1),subseq(K2,_,ET2),p(K1,EP1),p(K2,EP2),ET1<ET2.
subseq(E):-subseq(_,_,E).
#show subseq/1.

%ex3.11
%first rule: k*n
%first two rules: C_n^k.