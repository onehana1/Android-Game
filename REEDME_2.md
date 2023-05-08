# Android-Game
2020182038 정하나 스마트폰 게임 프로그래밍(02) 텀프로젝트 - 2차 발표

# 게임 소개
➡️ Life is a game : 인생게임 모작  
탄생부터 죽을 때 까지 인생을 사는 런게임 방식의 인생 게임입니다.<br/> 
게임을 진행하면서 나타나는 코인들을 "점프"키로 점프를 하며 획득할 수 있고,<br/>
여러 선택지들을 "선택"키를 통해 선택하며 게임을 진행합니다.<br/>
![image](https://user-images.githubusercontent.com/73771420/229375499-0a246779-467b-4f42-99ba-7219c42a142d.png)<br/> 

플레이어의 선택지와 아이템 획득 유무에 따라 게임의 엔딩이 변화합니다.


# 진행 상황
![image](https://user-images.githubusercontent.com/73771420/236850195-1d8264e2-bd1b-48ee-91a1-3d57cf52e747.png)

# 깃커밋 자료
![image](https://user-images.githubusercontent.com/73771420/236850293-daa8a1c7-ac8c-44de-a6c7-6305937fb670.png)
![image](https://user-images.githubusercontent.com/73771420/236850254-922b273a-2277-4edb-b5b9-c786875dd864.png)

# 변경된 목표
![image](https://user-images.githubusercontent.com/73771420/236851478-de5cf419-7a5a-43b9-8ebb-0f53a3a058d8.png)

주마등 시스템을 넣어서 플레이어가 지금까지 선택한 것들을 다시 역재생처럼 보여준다.

# MainScene에 등장하는 game object들에 대하여
  - Player: 플레이어의 정보들을 가지고 있으며 스프라이트 이미지를 사용하고 있다. 점프와 선택 상태를 가지고 있습니다.
![image](https://user-images.githubusercontent.com/73771420/236852074-0c97fdb2-c720-4b9a-8d41-c543a886075d.png)
![image](https://user-images.githubusercontent.com/73771420/236855526-7bff1494-bf89-42fd-b98d-f3dfea7256e4.png)
- 플레이어는 점프와, 선택 상태가 있으며 점프는 점프를 하고, 선택일시 시간을 기록해서 일정 시간동안 애니메이션을 플레이하고 다시 돌아오게 합니다.
  - button: 점프키, 선택키가 있습니다. 이 키를 누르면 플레이어의 상태를 점프, 선택으로 만들어줍니다.
 ![image](https://user-images.githubusercontent.com/73771420/236855902-e4525cd7-839b-4b29-8cdc-48e04b8142b7.png)
- 버튼키는 인자로 받은 좌표가 해당 RectF 영역 내부에 있는지를 확인하고 터치의 종류에 따라서 눌렀을때랑 뗐을때를 처리했습니다.
  - HorzScrollBackground: 가로로 스크롤 되는 배경이며, 플레이어의 상태에 따라서 배경이 스크롤 되는 속도가 달라지게 설정하였습니다.
![image](https://user-images.githubusercontent.com/73771420/236856771-0ffda481-8a3b-4d5b-93c8-93767b71a190.png)
-  게임 배경을 가로 방향으로 스크롤하는 기능을 담당합니다. Player 객체의 배경 스크롤 속도값을 가져와서 현재 속도값으로 설정하고, scroll 값을 계산합니다. draw() 함수에서는 현재 scroll 값으로부터 배경 이미지를 연속해서 그립니다.
  - MapLoader: 코인과 선택 오브젝트를 생성하는 클래스 입니다. 선택 오브젝트는 맵의 스크롤 위치의 비율에 따라서 생성하게 설정하였습니다.
 ![image](https://user-images.githubusercontent.com/73771420/236855074-cc36fb7d-b54c-4df3-b5f2-7ab380ae4221.png)
- 현재는 하드 코딩으로 맵의 스크롤 위치가 전체 너비의 n지점마다 객체 생성중입니다.
 
