#const n=5.
person(1..n).
know(1,X):-X=2..4.
know(2,4).
know(3,2;3,4).
%know(4,2).
know(5,1;5,2;5,4).

1{celebrity(C):person(C)}.
:- celebrity(C),person(P),C!=P,not know(P,C).
% celebrity(C):-person(C),#count {know(P,C):person(P)}=n-1.
:-know(C,X),celebrity(C),not celebrity(X).

#show celebrity/1.