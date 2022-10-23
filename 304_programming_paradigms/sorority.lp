person(dana;cody;abby;bess).

{like(X,Y)}16:-person(X),person(Y).

like(dana,cody).
:-like(abby,dana).
:-like(dana,abby).
like(bess,cody)|like(bess,dana).
like(abby,X):-like(bess,X).
like(cody,X):-like(X,cody).
:-like(X,X).

#show like/2.

%Answer: 1
%like(dana,cody) like(cody,dana) like(bess,cody) like(abby,cody) like(cody,bess) like(cody,abby)
%Answer: 2
%like(dana,cody) like(cody,dana) like(bess,cody) like(abby,cody) like(cody,bess) like(cody,abby) like(dana,bess)
%Answer: 3
%like(dana,cody) like(cody,dana) like(bess,cody) like(abby,cody) like(cody,bess) like(cody,abby) like(abby,bess)
%Answer: 4
%like(dana,cody) like(cody,dana) like(bess,cody) like(abby,cody) like(cody,bess) like(cody,abby) like(abby,bess) like(dana,bess)