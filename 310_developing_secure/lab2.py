e='''Ivxzoo, Xzvhzi’h xrksvi uzooh rm gsv xzgvtlib lu hfyhgrgfgrlm nlmlzokszyvgrx xrksvih (r.v., vzxsvovnvmg uiln gsv kozrmgvcg droo yv ivkozxvw drgs z fmrjfv vovnvmg uiln gsv hkzxv lu xrksvigvcgh).Uli gsrh ivzhlm, z xrkvsvigvcg kivhvievh gsv ivozgrev uivjfvmxb zg dsrxs kozrmgvcg vovnvmghzkkvzi rm gsv xliivhklmwrmt kozrmgvcg. Rm Evimzn xrksvi, vmxibkgrlm rh kviulinvw yb nvzmh luvCxofhrev-LI (CLI) oltrxzo lkvizgrlm (kozrmgvcg rh CLIvw drgs zm vmxibkgrlm pvb). Ru zmvmxibkgrlm pvb rh xslhvm izmwlnob zmw rh zg ovzhg zh olmt zh gsv kozrmgvcg gl yv vmxibkgvw,CLI vmxibkgrlm (lmv-grnv kzw) rh kilezyob (kviuvxgob) hvxfiv.'''
e=e.upper()

dic={'I':'r',
     'V':'e',
     'X':'c',
     'Z':'a',
     'O':'l',
     'H':'s',
     'R':'i',
     'K':'p',
     'S':'h',
     'G':'t',
     'Y':'b',
     'W':'d',
     'F':'u',
     'U':'f',
     'B':'y',
     'L':'o',
     'M':'n',
     'N':'m',
     'T':'g',
     'C':'x',
     'D':'w',
     'J':'q',
     'E':'v',
     'P':'k'
     }
c=e
for i in dic.keys():
    c=c.replace(i,dic[i])

print(c)
