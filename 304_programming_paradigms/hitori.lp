% Name: Tianyao Zhu
% Student ID: 22106061

% get a set of black cells from all given cells.
{cellblack(R,C)}:-cell(_,R,C).
% remain cells are cells not black.
remain(N,R,C):-cell(N,R,C),not cellblack(R,C).
remain(R,C):-remain(_,R,C).

% define horizontally next.
next(R,C1,R,C2):-cell(_,R,C1),cell(_,R,C2),|C1-C2|=1.
% define vertically next.
next(R1,C,R2,C):-cell(_,R1,C),cell(_,R2,C),|R1-R2|=1.

% rule 1
% every number occurs at most once in each row.
:-remain(N,R,C1),remain(N,R,C2),C1!=C2.
% every number occurs at most once in each column.
:-remain(N,R1,C),remain(N,R2,C),R1!=R2.

% rule 2
% two black cells cannot be next to each other.
:-cellblack(R1,C1),cellblack(R2,C2),next(R1,C1,R2,C2).

% rule 3
% all remaining cells must be connected.
:-remain(R1,C1),remain(R2,C2),not connect(R1,C1,R2,C2).
% two cells are connected if they're next to each other and they remain white.
connect(R1,C1,R2,C2):-remain(R1,C1),remain(R2,C2),next(R1,C1,R2,C2).
% two cells are connected if they're both connected to a third cell.
connect(R1,C1,R2,C2):-connect(R1,C1,R3,C3),connect(R3,C3,R2,C2).

#show cellblack/2.


% test inputs.
% cell(1,1,1;2,1,2;2,2,1;2,2,2).

% cell(1,X,1;5,X,2;3,X,3;1,X,4;2,X,5):-X=1.
% cell(5,X,1;4,X,2;1,X,3;3,X,4;4,X,5):-X=2.
% cell(3,X,1;4,X,2;3,X,3;1,X,4;5,X,5):-X=3.
% cell(4,X,1;4,X,2;2,X,3;3,X,4;3,X,5):-X=4.
% cell(2,X,1;1,X,2;5,X,3;4,X,4;4,X,5):-X=5.

% cell(2,X,1;4,X,2;1,X,3;3,X,4):-X=1.
% cell(2,X,1;2,X,2;4,X,3;1,X,4):-X=2.
% cell(1,X,1;3,X,2;4,X,3;3,X,4):-X=3.
% cell(1,X,1;1,X,2;2,X,3;4,X,4):-X=4.