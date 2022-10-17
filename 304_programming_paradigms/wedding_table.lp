good_people(bob;tina;bert;jane;alyssa).
bad_people(marina;william).

table(X,Y):- good_people(X),good_people(Y),X!=Y.
table(X,Y):- good_people(X),bad_people(Y).
table(X,Y):- table(Y,X).
table(A,B,C,D,E,F):- table(A,B),table(C,D),table(E,F),not table(marina,william), A!=C,A!=D,A!=E,A!=F,B!=C,B!=D,B!=E,B!=F,C!=E,C!=F,D!=E,D!=F.
table(A,B,C,D,E,F):- table(A,B,E,F,C,D).
table(A,B,C,D,E,F):- table(C,D,A,B,E,F).
table(A,B,C,D,E,F):- table(C,D,E,F,A,B).
table(A,B,C,D,E,F):- table(E,F,A,B,C,D).
table(A,B,C,D,E,F):- table(E,F,C,D,A,B).

#show table/6.

