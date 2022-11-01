cell(1,1,1;2,1,2;2,2,1;2,2,2).

% count rows to get puzzle dims. since some number may not appear in the puzzle.
dims(K):-K=#count{R:cell(_,R,_)}.

% get a set of black cells from all given cells.
{cellblack(R,C)}:-cell(N,R,C).
% remain cells are not black cells.
remain(R,C):-cell(_,R,C),not cellblack(R,C).

% define horizontally next.
next(R1,C1,R2,C2):-cell(_,R1,C1),cell(_,R2,C2),|R1-R2|=1,C1=C2.
% define vertically next.
next(R1,C1,R2,C2):-cell(_,R1,C1),cell(_,R2,C2),|C1-C2|=1,R1=R2.

% rule 1
% every number occurs at most once in each row.
{cell(N,R,C): remain(R,C),C=1..K}1:-N=1..K,R=1..K,dims(K).
% every number occurs at most once in each column.
{cell(N,R,C): remain(R,C),R=1..K}1:-N=1..K,C=1..K,dims(K).

% rule 2
% two black cells cannot be next to each other.
:-cellblack(R1,C1),cellblack(R2,C2),next(R1,C1,R2,C2).

% rule 3
% all remaining cells must be connected.
:-remain(R1,C1),remain(R2,C2),not connect(R1,C1,R2,C2).
% two remain cells are connected if they're next to each other.
connect(R1,C1,R2,C2):-remain(R1,C1),remain(R2,C2),next(R1,C1,R2,C2).
% tow remain cells are connected if they both connect to a third cell.
connect(R1,C1,R2,C2):-remain(R1,C1),remain(R2,C2),connect(R1,C1,R3,C3),connect(R3,C3,R2,C2).

#show cellblack/2.
