
# Together (스터디 구인 웹 프로젝트)
  
# 제작 기간(2024 . 01 . 19 ~ 2024 . 01 . 30)  / 개인 프로젝트 (네 번째 프로젝트) 

### 개발 환경 spring / thymeleaf / spring data jpa(+querydsl) / spring security / db-mysql 
   
### 주제 = 스터디 / 소모임 / 커뮤니티 를 구하는 웹사이트를 제작       
   
### 사이트 주소 = http://togetherpj.kro.kr/ 현재 인스턴스 종료상태  

 <br/> <br/> <br/> 

## 과정 및 후기
- **Spring Security** 적용 후 **접근 권한 관리**에서 자주 막혔었다, 단순한 src 경로를 열어주는 것 부터 이후 로그인 api 사용을 위한 url 접속까지 거의 모든 부분에서 에러를 만났고 덕분에 접근 권한관리에 좋은 경험을 얻었다. 
- 익숙해진 **Mybatis와 xml대신 Spring Data JPA와 entity를 이용해 DB연결**을 해주니 속도가 훨씬 빨라졌고 코드 관리도 월등히 좋아졌다. 논리적인 흐름을 한번 이해하고 나니 확실히 JPA의 장점을 체감 할 수 있었다.
- **유저간 소통 수단**으로 댓글과 메세지(쪽지, 쪽지함) 을 구현했는데, 수신자측에서 리로드 후 DB에서 다시 가져오는 방식이라 아직 **실시간의 느낌**을 주진 못했다. 다음 프로젝트에서는 **소켓을 사용하여 실시간 채팅기능**을 넣어 볼 예정이다.
- **AWS를 공부하여 ec2 인스턴스**를 빌려 직접 **jar파일을 배포**해봤는데, 단순히 코드적 지식보다도 **네트워크나 서버 지식**도 굉장히 많이 필요하다고 느꼈다. **원격 접속에서의 권한 설정**이 가장 복잡하게 느껴졌고 빌드와 배포에 대한 경험은 물론 **ubuntu를 이용하며 linux에 대한 이해**도 늘었다.

   <br/> <br/> <br/>
  
## ERD
<img src="pictures/ERD.png">
<br/> <br/> <br/> 

## 홈화면
<img src="pictures/home.png">

<br/> <br/> <br/> 

## 로그인 및 회원가입, 아이디 비밀번호 찾기
<img src="pictures/login.png">


<br/> <br/> <br/> 

## 메인 화면. 스터디 / 소모임 / 커뮤니티 를 구하는 글이 있고 작성가능한 곳
<img src="pictures/study.png">
<img src="pictures/group.png">
<img src="pictures/community.png">
<img src="pictures/boardDetail.png">

<br/> <br/> <br/> 

## 마이 페이지와 내 메세지함, 그리고 메세지 보내는 화면
<img src="pictures/mypage.png">
<img src="pictures/mymessage.png">
<img src="pictures/message.png">
