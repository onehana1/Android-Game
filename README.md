# Android-Game
2020182038 정하나 스마트폰 게임 프로그래밍(02) 텀프로젝트

# 게임 컨셉
➡️ Life is a game : 인생게임 모작  
탄생부터 죽을 때 까지 인생을 사는 런게임 방식의 인생 게임입니다.<br/> 
게임을 진행하면서 나타나는 코인들을 점프를 하며 획득할 수 있고,<br/>
여러 선택지들을 choice 키를 통해 선택하며 게임을 진행합니다.<br/>
![image](https://user-images.githubusercontent.com/73771420/229375499-0a246779-467b-4f42-99ba-7219c42a142d.png)<br/> 

플레이어의 선택지와 아이템 획득 유무에 따라 게임의 엔딩이 변화합니다.
# 선택
![image](https://user-images.githubusercontent.com/73771420/229376023-ab9c7ecb-7776-41e9-a86b-16ee6304e13f.png)<br/> 
취미 선택, 친구 선택, 직업 이벤트 선택, 프로포즈 선택, 집 선택을 할 수 있습니다.<br/> 

# 코인
1. 젖병 코인<br/> 
유년기에 나오는 코인입니다. 행복도를 얻을 수 있습니다.

2. 취미 코인<br/> 
![image](https://user-images.githubusercontent.com/73771420/229375826-7aa221a8-87c5-4373-950e-cf4bfd5d6886.png)
<br/> 
취미 코인은 학창시절에 나오며 책, 그림, 음악 코인이 있습니다. 얻은 코인들은 하단에 표시됩니다.<br/> 
학창 시절 어느 종류의 취미 코인을 얼마나 얻는지에 따라 직업이 달라집니다.<br/> 
취미와 같은 종류의 코인을 얻는다면 행복도가 오르지만 다른 코인을 얻는다면 행복도가 줄어듭니다.<br/> 

3. 돈 코인, 담배 코인<br/> 
![image](https://user-images.githubusercontent.com/73771420/229376500-bb089fe4-5b0f-461f-b89b-402f933647cd.png)
<br/> 
직장에 다닐때 돈 코인과 담배 코인이 나옵니다. 돈 코인은 상단에, 담배 코인은 하단에 표시됩니다.<br/> 
돈은 직업에 따라, 직업 이벤트 성공에 따라 지급되는 양이 다릅니다.<br/> 
돈 코인은 프로포즈나 집을 구매할 때 사용할 수 있습니다.
<br/> 

4. 앨범 코인, 시계 코인<br/> 
![image](https://user-images.githubusercontent.com/73771420/229376690-a542c26a-dc2a-416f-9ab3-8c8dfc038ccc.png)
<br/> 
노년기에 나오는 코인입니다. 앨범 코인을 얻으면 행복도가 올라가고, 시계 코인을 얻으면 건강과 행복도가 모두 내려갑니다.<br/> 

# 건강도, 행복도
![image](https://user-images.githubusercontent.com/73771420/229376412-f5adc67c-bd78-4bda-9275-946eb1c89f99.png)
<br/> 
상단바에는 차례로 건강도와 행복도가 있습니다.<br/> 
건강도와 행복도가 0이 되면 게임 오버가 됩니다.<br/> 
담배 코인을 7개이상 모아도 게임 오버됩니다.<br/> 

# 취미 
![image](https://user-images.githubusercontent.com/73771420/229376825-533e404b-88d2-4575-97f8-f0d0db87af6b.png)
<br/> 
하단바에 표시됩니다. 차례로 취미, 친구, 가족 스킬입니다. <br/> 
3개의 스킬은 같은 쿨타임을 공유합니다. <br/> 
스킬 레벨에 따라 엔딩이 달라집니다.<br/> 

# 엔딩
![image](https://user-images.githubusercontent.com/73771420/229376959-8baea979-821a-46c6-9d1c-a8320218ab5c.png)
<br/> 노년기에서 건강이 0이 되면 엔딩을 볼 수 있습니다. 플레이어의 선택에 따라 여러 다른 엔딩들을 볼 수 있습니다.<br/> 


# 개발 범위
- 게임 시작 화면<br/> 
- 유년기: 젖병 코인<br/> 
- 청소년기: 취미 선택(책, 음악, 그림), 취미코인(책, 음표, 팔레트), 친구 선택<br/> 
- 청년기: 취미 코인에 따른 직업 3가지(회사원, 가수, 미술가), 프로포즈 선택, 직업 이벤트 선택, 돈 코인, 담배 코인<br/> 
- 중년기: 직업 이벤트 선택, 돈 코인, 담배코인, 집 선택<br/> 
- 노년기: 시계 코인, 앨범 코인<br/> 
- 게임오버, 엔딩 화면<br/> <br/> 
- 코인: 7가지(책, 음표, 팔레트, 돈, 담배, 시계, 앨범)<br/> 
- 스킬(취미, 친구, 가족)<br/> 

# 예상 게임 흐름도
![image](https://user-images.githubusercontent.com/73771420/229431307-37bcd4ab-8fbb-403e-aa4f-0053d35c1d36.png)


# 개발 일정
![image](https://user-images.githubusercontent.com/73771420/229430849-d7408f1c-4884-4e87-9bdf-0c825c5e1098.png)
