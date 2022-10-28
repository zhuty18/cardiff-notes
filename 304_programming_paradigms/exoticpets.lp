first_name(abner;bruce;chuck;duane).
last_name(engels;foster;gunter;halevy).
pet(iguana;jackal;llama;cobra).

{full_name(F,L):last_name(L)}=1:-first_name(F).
{full_name(F,L):first_name(F)}=1:-last_name(L).
{owns(F,P):pet(P)}=1:-first_name(F).
{owns(F,P):first_name(F)}=1:-pet(P).

:-full_name(abner,engels).
:-full_name(abner,foster).

:-owns(chuck,iguana).
:-owns(duane,iguana).

:-full_name(X,foster),owns(X,jackal).
:-full_name(X,foster),owns(X,cobra).

:-owns(duane,llama).
:-full_name(duane,engels).

:-owns(abner,cobra).
:-full_name(abner,gunter).

:-full_name(bruce,foster).

:-owns(X,iguana),full_name(X,halevy).

#show full_name/2.
#show owns/2.

%full_name(abner,halevy) owns(bruce,iguana) full_name(chuck,foster) owns(duane,cobra) owns(abner,jackal) full_name(bruce,engels) full_name(duane,gunter) owns(chuck,llama)