people(marina;william;bob;tina;bert;jane;alyssa).
table(1..3).

2{at(P,T):people(P)}3:-table(T).
1{at(P,T):table(T)}1:-people(P).

same_table(P1,P2):-at(P1,T),at(P2,T),P1!=P2.
:- same_table(marina,william).
% :- at(marina,T), at(william,T).

#show at/2.