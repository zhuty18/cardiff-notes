zone2sensor(z(N),s(N..N+2)):-N=1..6.
#const n=4.
#const unitCap=2.
#const interUnitCap=2.


{unit2zone(1..n,Z)}=1:-zone2sensor(Z,_).
{unit2sensor(1..n,S)}=1:-zone2sensor(_,S).

partner(U1,U2):-unit2zone(U1,Z),zone2sensor(Z,S),unit2sensor(U2,S),U1!=U2.
partner(U1,U2):-partner(U2,U1).

:- #count{Z:unit2zone(U,Z)}>unitCap,U=1..n.
:- #count{S:unit2sensor(U,S)}>unitCap,U=1..n.
:- #count{U2:partner(U1,U2)}>interUnitCap,U1=1..n.

#show unit2zone/2.
#show unit2sensor/2.
#show partner/2.