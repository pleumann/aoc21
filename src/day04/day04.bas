PLUS3DOS �d   d �d                                                                                                         � 

 ��3     M ��"a",1    ,3    ,7    ,15    ,31    ,63  ?  ,127    ,255  �   � ��0     ;�7    ;�1    ;"AoC 2021.04 Giant Squid   ";�2    ;"�";�2    ;�6    ;"�";�6    ;�4    ;"�";�4    ;�5    ;"�";�5    ;�0     ;"��"; (/ �Box(2    ,24    ,7    ,3    ,"Board") 20 �Box(7    ,24    ,7    ,3    ,"Number") <0 �Box(12    ,24    ,7    ,3    ,"First") F/ �Box(17    ,24    ,7    ,3    ,"Last") P �i=0     �5     Z/ �8    +i*32     ,0     :�0     ,160  �   d' �8    ,i*32     :�160  �  ,0      n �i x : � ꆆ��������������������� � : � �n(100  d  ) � �i=1    �100  d  :�n(i):�i �* �firstBoard=9999  ' :�lastBoard=0      � : � ꆆ��������������������� � : � �i=1    �100  d   � ��4    ,25    ;i; � : �= �b(100  d  ,2    ):�r(5    ):�c(5    ):�value=0      � �j=1    �5     �k=1    �5     �xO ��j*4    -1    ,k*4    -2    ;"  ";�j*4    ,k*4    -2    ;x;" ";"0 �b(x+1    ,1    )=j:�b(x+1    ,2    )=k, �value=value+x6 �k@ �jJ :T �j=1    �100  d  ^ �x=n(j)h ��9  	  ,25    ;x;" ";r4 �row=b(x+1    ,1    ):�col=b(x+1    ,2    )| �row=0     ��%480�q ��row*4    ,col*4    -2    ;�7    ;�1    ;�1    ;"  ";�row*4    -1    ,col*4    -2    ;"  ";� �r(row)=r(row)+1    � �c(col)=c(col)+1    � �value=value-x�% �r(row)�5    �c(col)�5    ��%480� �score=value*x�; �j<firstBoard��firstBoard=j:��14    ,25    ;score;" ";�9 �j>lastBoard��lastBoard=j:��19    ,25    ;score;" ";� �%500� �j� :� �i� � : ꆆ��������������������� :& �Box(x,y,w,h,t$)0/ �y*8    +4    ,176  �  -x*8    -4    :^ �w*8    ,0     :�0     ,-h*8    -4    :�-w*8    ,0     :�0     ,h*8    +4    D ��x,y+1    ;t$;N �X :b ꆆ���������������������l :v{�67  C  ,3    ,19    ,4    ,64  @  ,39  '  ,85  U  ,14    ,84  T  ,93  ]  ,79  O  ,26    ,61  =  ,24    ,65  A  ,63  ?  ,15    ,69  E  ,48  0  ,8    ,82  R  ,75  K  ,36  $  ,96  `  ,16    ,49  1  ,28    ,40  (  ,97  a  ,38  &  ,76  L  ,91  [  ,83  S  ,7    ,62  >  ,94  ^  ,21    ,95  _  ,6    ,10  
  ,43  +  ,17    ,31    ,34  "  ,81  Q  ,23    ,52  4  ,60  <  ,54  6  ,29    ,70  F  ,12    ,35  #  ,0     ,57  9  ,45  -  ,20    ,71  G  ,78  N  ,44  ,  ,90  Z  ,2    ,33  !  ,68  D  ,53  5  ,92  \  ,50  2  ,73  I  ,88  X  ,47  /  ,58  :  ,5    ,9  	  ,87  W  ,22    ,13    ,18    ,30    ,59  ;  ,56  8  ,99  c  ,11    ,77  M  ,55  7  ,72  H  ,32     ,37  %  ,89  Y  ,42  *  ,27    ,66  B  ,41  )  ,86  V  ,51  3  ,74  J  ,1    ,46  .  ,25    ,98  b  ,80  P  �� �24    ,75  K  ,59  ;  ,41  )  ,17    ,58  :  ,74  J  ,64  @  ,92  \  ,39  '  ,68  D  ,8    ,78  N  ,85  U  ,72  H  ,18    ,3    ,22    ,4    ,34  "  ,11    ,76  L  ,6    ,28    ,50  2  �� �21    ,31    ,36  $  ,13    ,87  W  ,80  P  ,91  [  ,63  ?  ,62  >  ,77  M  ,46  .  ,93  ]  ,40  (  ,16    ,25    ,47  /  ,66  B  ,30    ,54  6  ,74  J  ,56  8  ,59  ;  ,86  V  ,72  H  ,37  %  �� �92  \  ,43  +  ,68  D  ,60  <  ,81  Q  ,3    ,78  N  ,75  K  ,73  I  ,12    ,90  Z  ,50  2  ,31    ,67  C  ,76  L  ,28    ,63  ?  ,52  4  ,95  _  ,61  =  ,6    ,38  &  ,79  O  ,19    ,17    �� �81  Q  ,20    ,61  =  ,60  <  ,86  V  ,43  +  ,27    ,50  2  ,21    ,85  U  ,77  M  ,84  T  ,68  D  ,76  L  ,24    ,33  !  ,13    ,89  Y  ,1    ,48  0  ,6    ,57  9  ,30    ,11    ,5    �� �66  B  ,24    ,22    ,86  V  ,2    ,67  C  ,77  M  ,72  H  ,88  X  ,87  W  ,21    ,60  <  ,89  Y  ,36  $  ,32     ,39  '  ,37  %  ,29    ,17    ,31    ,78  N  ,97  a  ,63  ?  ,94  ^  ,91  [  �� �85  U  ,71  G  ,86  V  ,16    ,54  6  ,98  b  ,11    ,82  R  ,89  Y  ,17    ,38  &  ,23    ,99  c  ,59  ;  ,69  E  ,58  :  ,12    ,74  J  ,15    ,93  ]  ,41  )  ,5    ,52  4  ,48  0  ,26    �� �3    ,32     ,61  =  ,29    ,27    ,98  b  ,74  J  ,34  "  ,58  :  ,23    ,24    ,54  6  ,76  L  ,79  O  ,88  X  ,71  G  ,90  Z  ,97  a  ,96  `  ,68  D  ,21    ,33  !  ,72  H  ,47  /  ,82  R  �� �13    ,70  F  ,59  ;  ,7    ,91  [  ,74  J  ,88  X  ,85  U  ,50  2  ,15    ,35  #  ,8    ,40  (  ,93  ]  ,6    ,95  _  ,29    ,52  4  ,18    ,99  c  ,57  9  ,64  @  ,0     ,9  	  ,39  '  �� �72  H  ,6    ,74  J  ,64  @  ,0     ,73  I  ,9  	  ,46  .  ,52  4  ,98  b  ,81  Q  ,68  D  ,14    ,69  E  ,48  0  ,25    ,17    ,5    ,54  6  ,19    ,11    ,47  /  ,33  !  ,23    ,62  >  �� �45  -  ,14    ,90  Z  ,59  ;  ,97  a  ,43  +  ,46  .  ,58  :  ,55  7  ,29    ,80  P  ,53  5  ,2    ,37  %  ,78  N  ,40  (  ,79  O  ,57  9  ,52  4  ,72  H  ,92  \  ,13    ,54  6  ,25    ,19    �� �39  '  ,78  N  ,99  c  ,84  T  ,2    ,80  P  ,53  5  ,24    ,51  3  ,5    ,33  !  ,20    ,48  0  ,43  +  ,66  B  ,82  R  ,13    ,52  4  ,30    ,98  b  ,14    ,16    ,26    ,44  ,  ,74  J  �� �38  &  ,35  #  ,45  -  ,83  S  ,94  ^  ,18    ,28    ,41  )  ,22    ,13    ,44  ,  ,9  	  ,10  
  ,98  b  ,58  :  ,64  @  ,73  I  ,24    ,31    ,34  "  ,39  '  ,85  U  ,50  2  ,77  M  ,63  ?  �� �71  G  ,18    ,68  D  ,47  /  ,65  A  ,25    ,40  (  ,82  R  ,69  E  ,44  ,  ,30    ,6    ,72  H  ,73  I  ,7    ,80  P  ,24    ,41  )  ,79  O  ,2    ,11    ,20    ,96  `  ,84  T  ,54  6  � �16    ,6    ,38  &  ,75  K  ,25    ,56  8  ,2    ,51  3  ,69  E  ,81  Q  ,15    ,54  6  ,91  [  ,85  U  ,90  Z  ,94  ^  ,0     ,7    ,71  G  ,30    ,28    ,17    ,76  L  ,67  C  ,31    � �13    ,12    ,23    ,7    ,71  G  ,91  [  ,89  Y  ,84  T  ,40  (  ,78  N  ,44  ,  ,83  S  ,90  Z  ,21    ,31    ,77  M  ,17    ,3    ,95  _  ,42  *  ,87  W  ,82  R  ,38  &  ,30    ,67  C  � �44  ,  ,64  @  ,60  <  ,76  L  ,36  $  ,21    ,39  '  ,86  V  ,89  Y  ,34  "  ,5    ,54  6  ,24    ,14    ,58  :  ,78  N  ,55  7  ,98  b  ,74  J  ,69  E  ,2    ,97  a  ,42  *  ,59  ;  ,51  3   � �51  3  ,35  #  ,78  N  ,54  6  ,40  (  ,9  	  ,52  4  ,5    ,66  B  ,19    ,92  \  ,74  J  ,68  D  ,90  Z  ,73  I  ,76  L  ,11    ,60  <  ,67  C  ,22    ,44  ,  ,7    ,1    ,89  Y  ,15    *� �93  ]  ,72  H  ,3    ,95  _  ,13    ,77  M  ,1    ,32     ,35  #  ,5    ,68  D  ,91  [  ,98  b  ,23    ,51  3  ,59  ;  ,19    ,31    ,57  9  ,56  8  ,54  6  ,46  .  ,92  \  ,88  X  ,26    4� �84  T  ,91  [  ,40  (  ,9  	  ,73  I  ,90  Z  ,41  )  ,51  3  ,12    ,10  
  ,0     ,61  =  ,89  Y  ,13    ,8    ,62  >  ,74  J  ,5    ,45  -  ,92  \  ,65  A  ,27    ,78  N  ,26    ,31    >� �54  6  ,21    ,32     ,84  T  ,42  *  ,68  D  ,25    ,76  L  ,3    ,40  (  ,24    ,15    ,59  ;  ,12    ,2    ,72  H  ,49  1  ,73  I  ,31    ,93  ]  ,35  #  ,67  C  ,70  F  ,60  <  ,91  [  H� �55  7  ,34  "  ,51  3  ,76  L  ,54  6  ,73  I  ,28    ,5    ,87  W  ,52  4  ,24    ,36  $  ,65  A  ,49  1  ,27    ,99  c  ,10  
  ,12    ,44  ,  ,50  2  ,23    ,77  M  ,53  5  ,80  P  ,4    R� �30    ,42  *  ,92  \  ,11    ,40  (  ,83  S  ,49  1  ,41  )  ,72  H  ,54  6  ,73  I  ,97  a  ,18    ,4    ,37  %  ,0     ,15    ,70  F  ,55  7  ,33  !  ,71  G  ,26    ,46  .  ,25    ,81  Q  \� �22    ,35  #  ,41  )  ,71  G  ,58  :  ,55  7  ,39  '  ,18    ,85  U  ,45  -  ,79  O  ,44  ,  ,9  	  ,38  &  ,2    ,47  /  ,4    ,23    ,34  "  ,82  R  ,49  1  ,63  ?  ,88  X  ,81  Q  ,29    f� �49  1  ,82  R  ,40  (  ,37  %  ,77  M  ,17    ,45  -  ,92  \  ,7    ,65  A  ,51  3  ,38  &  ,91  [  ,68  D  ,32     ,73  I  ,57  9  ,69  E  ,85  U  ,50  2  ,87  W  ,10  
  ,95  _  ,59  ;  ,1    p� �57  9  ,27    ,95  _  ,59  ;  ,87  W  ,78  N  ,96  `  ,82  R  ,63  ?  ,52  4  ,39  '  ,17    ,14    ,74  J  ,21    ,47  /  ,64  @  ,28    ,94  ^  ,65  A  ,40  (  ,3    ,49  1  ,25    ,61  =  z� �97  a  ,9  	  ,24    ,80  P  ,27    ,5    ,36  $  ,83  S  ,15    ,29    ,86  V  ,33  !  ,32     ,61  =  ,2    ,87  W  ,48  0  ,82  R  ,91  [  ,4    ,35  #  ,10  
  ,16    ,85  U  ,65  A  �� �17    ,12    ,43  +  ,96  `  ,5    ,36  $  ,76  L  ,29    ,51  3  ,73  I  ,98  b  ,66  B  ,27    ,97  a  ,91  [  ,78  N  ,28    ,2    ,61  =  ,30    ,95  _  ,70  F  ,19    ,47  /  ,54  6  �� �98  b  ,88  X  ,51  3  ,32     ,7    ,89  Y  ,61  =  ,16    ,91  [  ,95  _  ,94  ^  ,23    ,19    ,77  M  ,70  F  ,42  *  ,90  Z  ,36  $  ,26    ,44  ,  ,99  c  ,71  G  ,5    ,57  9  ,13    �� �14    ,43  +  ,97  a  ,72  H  ,83  S  ,39  '  ,73  I  ,58  :  ,16    ,59  ;  ,51  3  ,2    ,28    ,24    ,18    ,40  (  ,70  F  ,65  A  ,27    ,91  [  ,4    ,44  ,  ,68  D  ,74  J  ,56  8  �� �90  Z  ,9  	  ,71  G  ,23    ,73  I  ,11    ,95  _  ,62  >  ,36  $  ,38  &  ,77  M  ,34  "  ,60  <  ,67  C  ,41  )  ,28    ,48  0  ,98  b  ,40  (  ,42  *  ,47  /  ,51  3  ,82  R  ,87  W  ,63  ?  �� �19    ,41  )  ,57  9  ,61  =  ,50  2  ,64  @  ,84  T  ,8    ,81  Q  ,11    ,83  S  ,68  D  ,31    ,66  B  ,90  Z  ,2    ,72  H  ,71  G  ,96  `  ,79  O  ,78  N  ,89  Y  ,77  M  ,60  <  ,4    �� �73  I  ,88  X  ,72  H  ,23    ,68  D  ,98  b  ,52  4  ,21    ,89  Y  ,43  +  ,48  0  ,29    ,10  
  ,8    ,6    ,49  1  ,3    ,54  6  ,37  %  ,12    ,83  S  ,34  "  ,51  3  ,77  M  ,66  B  �� �52  4  ,16    ,94  ^  ,84  T  ,81  Q  ,3    ,87  W  ,99  c  ,72  H  ,98  b  ,48  0  ,10  
  ,44  ,  ,32     ,22    ,9  	  ,69  E  ,36  $  ,74  J  ,62  >  ,51  3  ,42  *  ,91  [  ,68  D  ,60  <  �� �39  '  ,54  6  ,16    ,97  a  ,14    ,58  :  ,84  T  ,89  Y  ,15    ,20    ,67  C  ,49  1  ,19    ,55  7  ,86  V  ,10  
  ,44  ,  ,76  L  ,12    ,96  `  ,74  J  ,36  $  ,51  3  ,41  )  ,2    �� �27    ,22    ,90  Z  ,79  O  ,86  V  ,47  /  ,73  I  ,2    ,53  5  ,58  :  ,31    ,89  Y  ,37  %  ,19    ,12    ,20    ,83  S  ,87  W  ,23    ,30    ,32     ,8    ,92  \  ,55  7  ,68  D  �� �19    ,39  '  ,64  @  ,53  5  ,12    ,32     ,7    ,80  P  ,72  H  ,79  O  ,82  R  ,96  `  ,21    ,13    ,40  (  ,18    ,25    ,61  =  ,9  	  ,70  F  ,84  T  ,95  _  ,42  *  ,36  $  ,52  4  �� �48  0  ,12    ,29    ,61  =  ,7    ,34  "  ,13    ,99  c  ,98  b  ,6    ,74  J  ,36  $  ,66  B  ,91  [  ,88  X  ,75  K  ,85  U  ,93  ]  ,80  P  ,83  S  ,96  `  ,11    ,44  ,  ,47  /  ,39  '  �� �79  O  ,43  +  ,28    ,16    ,75  K  ,66  B  ,64  @  ,17    ,71  G  ,72  H  ,36  $  ,30    ,19    ,60  <  ,38  &  ,1    ,13    ,77  M  ,69  E  ,94  ^  ,78  N  ,6    ,97  a  ,93  ]  ,63  ?  �� �86  V  ,45  -  ,14    ,38  &  ,37  %  ,35  #  ,20    ,15    ,68  D  ,55  7  ,92  \  ,3    ,0     ,90  Z  ,8    ,88  X  ,32     ,87  W  ,17    ,22    ,33  !  ,34  "  ,78  N  ,13    ,43  +  � �87  W  ,92  \  ,58  :  ,95  _  ,6    ,35  #  ,23    ,54  6  ,40  (  ,97  a  ,82  R  ,64  @  ,88  X  ,10  
  ,94  ^  ,63  ?  ,8    ,26    ,98  b  ,18    ,42  *  ,76  L  ,39  '  ,50  2  ,51  3  � �75  K  ,13    ,4    ,72  H  ,95  _  ,11    ,50  2  ,15    ,47  /  ,52  4  ,12    ,73  I  ,80  P  ,74  J  ,70  F  ,68  D  ,30    ,21    ,37  %  ,58  :  ,91  [  ,2    ,24    ,32     ,82  R  � �76  L  ,66  B  ,4    ,68  D  ,79  O  ,19    ,73  I  ,24    ,51  3  ,96  `  ,16    ,52  4  ,26    ,78  N  ,7    ,48  0  ,30    ,17    ,82  R  ,92  \  ,28    ,88  X  ,90  Z  ,71  G  ,59  ;  $� �95  _  ,18    ,69  E  ,85  U  ,63  ?  ,16    ,78  N  ,97  a  ,10  
  ,41  )  ,53  5  ,98  b  ,73  I  ,87  W  ,19    ,15    ,35  #  ,94  ^  ,57  9  ,82  R  ,48  0  ,40  (  ,14    ,3    ,38  &  .� �39  '  ,40  (  ,78  N  ,64  @  ,87  W  ,90  Z  ,69  E  ,83  S  ,18    ,16    ,58  :  ,91  [  ,36  $  ,23    ,74  J  ,25    ,51  3  ,99  c  ,4    ,76  L  ,62  >  ,10  
  ,88  X  ,2    ,1    8� �72  H  ,95  _  ,34  "  ,2    ,84  T  ,38  &  ,12    ,97  a  ,92  \  ,47  /  ,24    ,23    ,41  )  ,10  
  ,75  K  ,56  8  ,87  W  ,68  D  ,45  -  ,89  Y  ,14    ,85  U  ,52  4  ,98  b  ,79  O  B� �96  `  ,97  a  ,15    ,98  b  ,17    ,76  L  ,13    ,6    ,38  &  ,81  Q  ,66  B  ,90  Z  ,51  3  ,36  $  ,85  U  ,95  _  ,48  0  ,40  (  ,99  c  ,94  ^  ,69  E  ,88  X  ,19    ,4    ,1    L� �20    ,36  $  ,93  ]  ,50  2  ,35  #  ,13    ,15    ,6    ,49  1  ,92  \  ,0     ,70  F  ,38  &  ,29    ,22    ,68  D  ,34  "  ,73  I  ,89  Y  ,71  G  ,5    ,10  
  ,12    ,79  O  ,31    V� �16    ,63  ?  ,34  "  ,29    ,2    ,43  +  ,57  9  ,18    ,51  3  ,67  C  ,83  S  ,47  /  ,49  1  ,17    ,96  `  ,84  T  ,33  !  ,40  (  ,7    ,50  2  ,60  <  ,30    ,41  )  ,81  Q  ,76  L  `� �86  V  ,85  U  ,4    ,48  0  ,61  =  ,34  "  ,46  .  ,89  Y  ,78  N  ,23    ,83  S  ,8    ,43  +  ,57  9  ,30    ,21    ,36  $  ,7    ,75  K  ,37  %  ,29    ,40  (  ,62  >  ,60  <  ,54  6  j� �47  /  ,28    ,42  *  ,39  '  ,57  9  ,16    ,46  .  ,54  6  ,52  4  ,55  7  ,78  N  ,84  T  ,32     ,95  _  ,23    ,27    ,26    ,9  	  ,75  K  ,62  >  ,90  Z  ,85  U  ,0     ,65  A  ,37  %  t� �89  Y  ,46  .  ,4    ,81  Q  ,55  7  ,68  D  ,13    ,79  O  ,18    ,90  Z  ,57  9  ,73  I  ,21    ,15    ,32     ,59  ;  ,56  8  ,62  >  ,58  :  ,36  $  ,45  -  ,98  b  ,64  @  ,33  !  ,12    ~� �89  Y  ,92  \  ,3    ,69  E  ,78  N  ,45  -  ,50  2  ,12    ,71  G  ,72  H  ,18    ,87  W  ,64  @  ,48  0  ,88  X  ,84  T  ,77  M  ,53  5  ,17    ,62  >  ,68  D  ,6    ,83  S  ,91  [  ,2    �� �5    ,80  P  ,25    ,90  Z  ,19    ,21    ,86  V  ,66  B  ,69  E  ,61  =  ,22    ,59  ;  ,39  '  ,54  6  ,91  [  ,9  	  ,27    ,14    ,24    ,40  (  ,95  _  ,74  J  ,18    ,63  ?  ,11    �� �84  T  ,63  ?  ,42  *  ,80  P  ,61  =  ,23    ,39  '  ,49  1  ,92  \  ,25    ,56  8  ,64  @  ,70  F  ,2    ,88  X  ,99  c  ,29    ,15    ,26    ,9  	  ,82  R  ,91  [  ,35  #  ,7    ,40  (  �� �4    ,93  ]  ,44  ,  ,42  *  ,16    ,78  N  ,72  H  ,32     ,73  I  ,81  Q  ,84  T  ,91  [  ,85  U  ,82  R  ,69  E  ,88  X  ,49  1  ,59  ;  ,92  \  ,96  `  ,61  =  ,99  c  ,19    ,33  !  ,38  &  �� �87  W  ,2    ,46  .  ,16    ,83  S  ,29    ,31    ,45  -  ,37  %  ,51  3  ,25    ,65  A  ,26    ,89  Y  ,19    ,80  P  ,17    ,27    ,8    ,73  I  ,54  6  ,4    ,76  L  ,0     ,12    �� �50  2  ,65  A  ,47  /  ,43  +  ,31    ,58  :  ,94  ^  ,90  Z  ,71  G  ,12    ,27    ,3    ,81  Q  ,45  -  ,9  	  ,1    ,33  !  ,37  %  ,15    ,83  S  ,96  `  ,26    ,41  )  ,77  M  ,57  9  �� �22    ,54  6  ,71  G  ,73  I  ,5    ,64  @  ,77  M  ,15    ,98  b  ,38  &  ,61  =  ,90  Z  ,20    ,57  9  ,40  (  ,60  <  ,18    ,83  S  ,72  H  ,12    ,34  "  ,91  [  ,87  W  ,41  )  ,21    �� �82  R  ,29    ,51  3  ,16    ,61  =  ,37  %  ,41  )  ,86  V  ,20    ,19    ,59  ;  ,30    ,43  +  ,15    ,53  5  ,17    ,83  S  ,5    ,14    ,89  Y  ,78  N  ,70  F  ,1    ,12    ,62  >  �� �19    ,95  _  ,68  D  ,67  C  ,92  \  ,14    ,70  F  ,73  I  ,62  >  ,29    ,40  (  ,9  	  ,97  a  ,82  R  ,66  B  ,11    ,50  2  ,77  M  ,47  /  ,53  5  ,20    ,75  K  ,88  X  ,94  ^  ,93  ]  �� �48  0  ,39  '  ,62  >  ,56  8  ,44  ,  ,95  _  ,43  +  ,10  
  ,89  Y  ,60  <  ,40  (  ,0     ,73  I  ,17    ,59  ;  ,50  2  ,2    ,8    ,4    ,5    ,24    ,79  O  ,20    ,13    ,96  `  �� �25    ,40  (  ,36  $  ,54  6  ,13    ,46  .  ,48  0  ,37  %  ,71  G  ,26    ,29    ,42  *  ,27    ,44  ,  ,23    ,24    ,61  =  ,79  O  ,3    ,90  Z  ,97  a  ,21    ,43  +  ,86  V  ,18    �� �95  _  ,4    ,14    ,12    ,71  G  ,11    ,55  7  ,50  2  ,83  S  ,85  U  ,9  	  ,43  +  ,29    ,32     ,28    ,78  N  ,20    ,63  ?  ,87  W  ,40  (  ,61  =  ,84  T  ,37  %  ,75  K  ,77  M  �� �4    ,96  `  ,87  W  ,22    ,2    ,95  _  ,70  F  ,39  '  ,35  #  ,49  1  ,23    ,27    ,19    ,43  +  ,0     ,42  *  ,75  K  ,36  $  ,52  4  ,11    ,13    ,8    ,57  9  ,88  X  ,46  .   � �37  %  ,5    ,87  W  ,58  :  ,86  V  ,65  A  ,78  N  ,89  Y  ,57  9  ,79  O  ,70  F  ,40  (  ,14    ,80  P  ,97  a  ,88  X  ,55  7  ,68  D  ,28    ,13    ,53  5  ,59  ;  ,24    ,26    ,1    
� �5    ,95  _  ,59  ;  ,71  G  ,23    ,44  ,  ,57  9  ,34  "  ,65  A  ,83  S  ,49  1  ,93  ]  ,9  	  ,77  M  ,28    ,37  %  ,69  E  ,79  O  ,99  c  ,73  I  ,17    ,27    ,33  !  ,66  B  ,85  U  � �75  K  ,61  =  ,32     ,0     ,16    ,65  A  ,59  ;  ,47  /  ,25    ,81  Q  ,87  W  ,97  a  ,8    ,50  2  ,70  F  ,78  N  ,34  "  ,38  &  ,42  *  ,51  3  ,22    ,63  ?  ,6    ,66  B  ,1    � �65  A  ,68  D  ,77  M  ,1    ,19    ,53  5  ,14    ,7    ,88  X  ,9  	  ,11    ,22    ,40  (  ,25    ,39  '  ,69  E  ,93  ]  ,37  %  ,72  H  ,5    ,90  Z  ,80  P  ,38  &  ,10  
  ,16    (� �15    ,81  Q  ,62  >  ,68  D  ,44  ,  ,26    ,70  F  ,43  +  ,55  7  ,89  Y  ,22    ,69  E  ,8    ,94  ^  ,51  3  ,52  4  ,19    ,79  O  ,96  `  ,10  
  ,24    ,48  0  ,63  ?  ,74  J  ,84  T  2� �36  $  ,9  	  ,57  9  ,4    ,40  (  ,95  _  ,98  b  ,58  :  ,70  F  ,87  W  ,45  -  ,97  a  ,92  \  ,23    ,86  V  ,6    ,31    ,15    ,78  N  ,12    ,90  Z  ,75  K  ,48  0  ,41  )  ,3    <� �34  "  ,97  a  ,31    ,92  \  ,20    ,59  ;  ,6    ,89  Y  ,79  O  ,70  F  ,39  '  ,90  Z  ,16    ,72  H  ,91  [  ,76  L  ,75  K  ,85  U  ,47  /  ,68  D  ,86  V  ,62  >  ,32     ,19    ,64  @  F� �44  ,  ,5    ,72  H  ,25    ,32     ,87  W  ,18    ,93  ]  ,33  !  ,0     ,22    ,96  `  ,46  .  ,4    ,28    ,61  =  ,81  Q  ,77  M  ,52  4  ,80  P  ,66  B  ,24    ,63  ?  ,23    ,45  -  P� �53  5  ,33  !  ,99  c  ,31    ,75  K  ,60  <  ,39  '  ,56  8  ,89  Y  ,57  9  ,76  L  ,81  Q  ,14    ,95  _  ,23    ,8    ,19    ,98  b  ,13    ,5    ,49  1  ,91  [  ,54  6  ,47  /  ,7    Z� �11    ,58  :  ,44  ,  ,6    ,94  ^  ,31    ,87  W  ,50  2  ,77  M  ,22    ,49  1  ,9  	  ,40  (  ,24    ,60  <  ,86  V  ,36  $  ,12    ,3    ,71  G  ,59  ;  ,99  c  ,68  D  ,20    ,66  B  d� �83  S  ,11    ,93  ]  ,36  $  ,6    ,73  I  ,55  7  ,97  a  ,48  0  ,18    ,3    ,43  +  ,51  3  ,90  Z  ,57  9  ,38  &  ,65  A  ,39  '  ,95  _  ,68  D  ,94  ^  ,24    ,59  ;  ,20    ,34  "  n� �53  5  ,57  9  ,69  E  ,3    ,16    ,2    ,91  [  ,22    ,24    ,26    ,44  ,  ,84  T  ,31    ,28    ,82  R  ,46  .  ,94  ^  ,65  A  ,78  N  ,99  c  ,55  7  ,49  1  ,11    ,66  B  ,21    x� �10  
  ,53  5  ,20    ,69  E  ,41  )  ,70  F  ,12    ,56  8  ,2    ,94  ^  ,87  W  ,23    ,74  J  ,60  <  ,55  7  ,59  ;  ,67  C  ,18    ,38  &  ,22    ,71  G  ,4    ,51  3  ,81  Q  ,39  '  �� �59  ;  ,32     ,64  @  ,66  B  ,53  5  ,20    ,11    ,27    ,10  
  ,81  Q  ,41  )  ,93  ]  ,12    ,45  -  ,99  c  ,70  F  ,94  ^  ,77  M  ,16    ,76  L  ,30    ,79  O  ,57  9  ,0     ,90  Z  �� �84  T  ,8    ,76  L  ,13    ,98  b  ,96  `  ,1    ,9  	  ,65  A  ,38  &  ,23    ,30    ,64  @  ,3    ,95  _  ,70  F  ,26    ,34  "  ,86  V  ,79  O  ,2    ,22    ,77  M  ,41  )  ,68  D  �� �77  M  ,11    ,55  7  ,80  P  ,21    ,45  -  ,70  F  ,28    ,0     ,57  9  ,38  &  ,74  J  ,33  !  ,86  V  ,22    ,42  *  ,13    ,66  B  ,61  =  ,83  S  ,46  .  ,94  ^  ,7    ,82  R  ,40  (  �� �53  5  ,68  D  ,94  ^  ,71  G  ,64  @  ,44  ,  ,99  c  ,86  V  ,66  B  ,97  a  ,80  P  ,33  !  ,48  0  ,74  J  ,45  -  ,29    ,13    ,11    ,15    ,62  >  ,36  $  ,89  Y  ,9  	  ,47  /  ,56  8  �� �49  1  ,90  Z  ,16    ,55  7  ,14    ,68  D  ,13    ,27    ,47  /  ,46  .  ,54  6  ,93  ]  ,97  a  ,10  
  ,31    ,33  !  ,58  :  ,6    ,83  S  ,48  0  ,63  ?  ,28    ,95  _  ,8    ,62  >  �� �72  H  ,60  <  ,12    ,24    ,20    ,1    ,22    ,90  Z  ,58  :  ,65  A  ,84  T  ,5    ,96  `  ,80  P  ,33  !  ,64  @  ,15    ,47  /  ,23    ,46  .  ,63  ?  ,36  $  ,6    ,31    ,91  [  �� �19    ,27    ,96  `  ,54  6  ,36  $  ,33  !  ,32     ,65  A  ,11    ,26    ,0     ,47  /  ,25    ,59  ;  ,56  8  ,41  )  ,45  -  ,76  L  ,14    ,98  b  ,52  4  ,22    ,31    ,66  B  ,38  &  �� �7    ,91  [  ,5    ,18    ,14    ,4    ,19    ,54  6  ,42  *  ,71  G  ,31    ,82  R  ,81  Q  ,61  =  ,39  '  ,58  :  ,51  3  ,70  F  ,10  
  ,55  7  ,43  +  ,60  <  ,15    ,89  Y  ,21    �� �85  U  ,31    ,75  K  ,55  7  ,76  L  ,92  \  ,93  ]  ,54  6  ,98  b  ,44  ,  ,21    ,22    ,6    ,79  O  ,20    ,34  "  ,64  @  ,7    ,82  R  ,78  N  ,53  5  ,36  $  ,96  `  ,37  %  ,19    �� �84  T  ,58  :  ,35  #  ,68  D  ,76  L  ,79  O  ,91  [  ,92  \  ,25    ,29    ,93  ]  ,83  S  ,23    ,22    ,80  P  ,51  3  ,28    ,53  5  ,60  <  ,40  (  ,0     ,62  >  ,77  M  ,49  1  ,39  '  �� �35  #  ,38  &  ,24    ,88  X  ,53  5  ,61  =  ,30    ,52  4  ,49  1  ,83  S  ,20    ,97  a  ,6    ,16    ,55  7  ,60  <  ,43  +  ,14    ,67  C  ,4    ,66  B  ,9  	  ,85  U  ,28    ,77  M  �� �73  I  ,57  9  ,65  A  ,36  $  ,50  2  ,18    ,94  ^  ,14    ,59  ;  ,67  C  ,7    ,78  N  ,40  (  ,6    ,13    ,86  V  ,49  1  ,5    ,22    ,66  B  ,63  ?  ,32     ,68  D  ,44  ,  ,80  P  �� �52  4  ,95  _  ,93  ]  ,25    ,16    ,0     ,83  S  ,41  )  ,77  M  ,49  1  ,13    ,63  ?  ,65  A  ,84  T  ,69  E  ,51  3  ,9  	  ,39  '  ,47  /  ,24    ,92  \  ,4    ,14    ,8    ,66  B  � �70  F  ,31    ,33  !  ,69  E  ,50  2  ,36  $  ,29    ,76  L  ,56  8  ,64  @  ,97  a  ,11    ,40  (  ,19    ,81  Q  ,18    ,57  9  ,10  
  ,24    ,15    ,30    ,44  ,  ,42  *  ,89  Y  ,60  <  � �42  *  ,97  a  ,9  	  ,38  &  ,60  <  ,48  0  ,62  >  ,53  5  ,70  F  ,27    ,49  1  ,72  H  ,90  Z  ,86  V  ,18    ,69  E  ,50  2  ,8    ,78  N  ,84  T  ,28    ,13    ,17    ,10  
  ,35  #  � �84  T  ,7    ,60  <  ,17    ,36  $  ,30    ,1    ,3    ,89  Y  ,49  1  ,45  -  ,10  
  ,85  U  ,97  a  ,76  L  ,31    ,38  &  ,16    ,2    ,12    ,43  +  ,58  :  ,11    ,77  M  ,78  N  "� �53  5  ,20    ,98  b  ,94  ^  ,82  R  ,54  6  ,62  >  ,27    ,92  \  ,83  S  ,60  <  ,41  )  ,66  B  ,5    ,30    ,58  :  ,15    ,90  Z  ,88  X  ,3    ,38  &  ,45  -  ,7    ,26    ,37  %  ,� �62  >  ,94  ^  ,17    ,55  7  ,28    ,27    ,86  V  ,26    ,42  *  ,87  W  ,90  Z  ,18    ,84  T  ,20    ,85  U  ,92  \  ,97  a  ,59  ;  ,83  S  ,0     ,89  Y  ,21    ,25    ,36  $  ,11    6� �89  Y  ,60  <  ,41  )  ,91  [  ,54  6  ,16    ,9  	  ,57  9  ,40  (  ,53  5  ,87  W  ,56  8  ,64  @  ,23    ,27    ,13    ,42  *  ,84  T  ,2    ,52  4  ,66  B  ,77  M  ,80  P  ,0     ,38  &  @� �58  :  ,23    ,55  7  ,96  `  ,75  K  ,56  8  ,8    ,19    ,52  4  ,10  
  ,98  b  ,13    ,70  F  ,62  >  ,73  I  ,6    ,64  @  ,86  V  ,4    ,12    ,51  3  ,91  [  ,93  ]  ,29    ,34  "  J� �55  7  ,8    ,99  c  ,51  3  ,70  F  ,33  !  ,30    ,52  4  ,58  :  ,10  
  ,38  &  ,31    ,87  W  ,9  	  ,61  =  ,63  ?  ,46  .  ,15    ,48  0  ,24    ,32     ,94  ^  ,40  (  ,74  J  ,21    T� �5    ,70  F  ,26    ,48  0  ,28    ,41  )  ,35  #  ,93  ]  ,34  "  ,44  ,  ,46  .  ,86  V  ,19    ,0     ,27    ,7    ,81  Q  ,51  3  ,95  _  ,15    ,36  $  ,84  T  ,76  L  ,75  K  ,92  \  ^� �34  "  ,0     ,31    ,21    ,3    ,23    ,96  `  ,49  1  ,51  3  ,11    ,42  *  ,27    ,47  /  ,66  B  ,91  [  ,93  ]  ,89  Y  ,16    ,62  >  ,54  6  ,22    ,71  G  ,26    ,32     ,99  c  