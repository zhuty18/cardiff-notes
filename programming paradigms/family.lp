ancestor(X,Y):- parent(X,Y).
parent(john, bill).

married(X,Y) :- married(Y,X).
married(X,Y) :- husband(X,Y).
married(X,Y) :- wife(X,Y).

husband(john, jane).
wife(mary, matt).

parent(X,Y) :- father(X,Y).
parent(X,Y) :- mother(X,Y).
father(john, bill).
mother(mary, john).

parent(X,Z):- married(X,Y), parent(Y,Z).
ancestor(X,Z):- ancestor(X,Y), ancestor(Y,Z).

