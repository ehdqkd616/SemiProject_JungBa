# JungBa (정부지원금 바로 지금)
국가에서 제공해주는 복지 지원이 많지만 많은 사람들이 정보를 제대로 얻지 못하거나, 시기를 놓쳐 혜택을 받지 못하는 사람들이 많다. 더 많은 사람들이 국가에서 진행하는 복지 사업의 신청 시기와 정보를 편하고 정확하게 습득하여 혜택을 놓치지 않도록 도와주기 위한 사이트를 구축하였다.

### 개발 환경
___
- **Operating System** : Window OS
- **Server** : Apache Tomcat 9.0
- **Database** : Oracle Database 18c
- **Development Tool** : Eclipse, Visual Studio Code, sqlDeveloper version 19.2.1.247
- **Development Language** :  Java, JavaScript, jQuery, HTML5, CSS3, SQL
- **Team Coop** : Github, ERDCloud, kakaoOven
- **API** : Swipper, Kakao Map, Kakao Login
- **Technologies used** : Servlet, jQuery, Ajax


### 구현 기능
___
- **회원관리** : 회원가입(주소 다음 맵 api), 회원정보 보기, 회원정보 수정, 회원탈퇴, 로그인(일반 로그인, 카카오 로그인 api), 로그아웃, 계정 찾기(이름, 휴대폰번호, 이메일 이용), 마이페이지(프로필 사진 등록/수정/삭제, 내가 올린 게시물 모아보기)

- **게시판** : Ajax를 이용한 게시글 리스트 조회 및 검색(카테고리 별 검색) 및 등록/수정/삭제, 댓글 등록/삭제, 파일 업로드 및 다운로드 기능

- **관리자** : 게시글 등록요청 승인 혹은 거부 처리, 회원 목록 조회, Q/A 답변, 회원 정보 열람(회원별 게시글 모아보기), 게시글 리스트 조회 및 검색(카테고리별 검색, 승인 여부별 검색)

- **메인페이지** : 네비게이션 바, 게시판 카테고리별 최신글 TOP5, 배너 슬라이드 이미지(최신 국가지원 프로그램)

### DB 설계
___
![JUNGBA_ERD](https://user-images.githubusercontent.com/64412357/103795243-164fc400-5089-11eb-8750-d29b06b169ae.png)

- 초기 DB 설계 시, 게시판 별로 사용하는 컬럼들이 달라서 테이블을 게시판별로 따로 만들었으나 후에 게시판 이름으로 구분할 수 있도록 구분 컬럼을 추가하여 통합 테이블을 만들었다. 
- 첨부파일 테이블 설계 시, 게시판에서만 첨부파일을 사용할 예정이었기 때문에 테이블을 하나만 만들었으나 후에 프로필 사진 등록기능이 추가되면서 회원테이블의 회원 번호를 외래키로 추가하게 되었다. 회원 프로필을 관리하기 위한 테이블 설계를 고민했으나 임시방편으로 컬럼을 추가하여 해결하였다. 
- 테이블을 최대한 줄이는 방법으로 설계했으나 전체적으로 DB 설계에 아쉬움이 많이 남았다. 게시판별로 비슷한 형태의 게시판이 두 분류로 나누어져 있어서 테이블을 분리했으면 좀 더 효율적이지 않았을까 하는 아쉬움이 남았다. 또한 프로필 사진을 위해 첨부파일 테이블에 외래키를 추가했던 점이 아쉬웠다. 회원 테이블에 프로필사진 파일명 컬럼을 추가하거나 회원 프로필을 위한 테이블을 따로 두었으면 하는 아쉬움이 있다.


## 페이지
 ### 메인 페이지

![JUNGBA_MainPage](https://user-images.githubusercontent.com/64412357/103849507-3367af80-50e8-11eb-9566-cfd1d2f481c2.png)

- 상단의 네비게이션 바에 공지사항, 지원정책, 대외활동, 커뮤니티, 묻고 답하기 메뉴를 클릭하여 각각의 페이지로 이동할 수 있다.
- 화면 상단에 지원정책 혹은 대외활동 등의 최신 정보를 이미지 슬라이드로 보여준다.
- 각각 게시판 별 최신글 TOP5를 보여준다.

<br>

 ### 로그인·계정 찾기

![JUNGBA_LoginPage](https://user-images.githubusercontent.com/64412357/103849812-05cf3600-50e9-11eb-94a8-57ab6dec14d4.png)
![JUNGBA_SearchId](https://user-images.githubusercontent.com/64412357/103850341-5b581280-50ea-11eb-8beb-44ed0ce37007.png)
![JUNGBA_SearchPassword](https://user-images.githubusercontent.com/64412357/103850344-5bf0a900-50ea-11eb-9094-68c38b3a7809.png)
![JUNGBA_ChangePassword](https://user-images.githubusercontent.com/64412357/103850345-5c893f80-50ea-11eb-9fb8-c1bd60b986db.png)

- 로그인, 카카오 로그인, 회원가입이 가능하다.
- 회원 이름과 휴대폰 번호 혹은 이메일을 통해 아이디를 찾을 수 있다.
- 회원 이름과 아이디와 이메일 혹은 휴대폰 번호를 통해 비밀번호를 재설정 할 수 있다.

<br>

 ### 회원가입

![JUNGBA_InsertMember(Sample)](https://user-images.githubusercontent.com/64412357/103849511-3793cd00-50e8-11eb-8737-a36b901f2f9e.png)

1. 이용 약관에 모두 동의 체크를 해야한다.
2. 아이디, 닉네임, 이름, 비밀번호, 이메일, 휴대폰 등의 정보를 정해진 정규표현식에 맞게 작성해야하고, 아이디와 닉네임은 중복확인을 해야한다.
3. 주소는 도로명 주소 api를 이용하여 정확하고 손쉽게 주소를 입력할 수 있다.

<br>

### 메인 게시판(지원정책·대외활동)

**게시글 검색**
![JUNGBA_SearchBoard](https://user-images.githubusercontent.com/64412357/103851310-d6222d00-50ec-11eb-9cd0-030b978523ed.png)

**게시글 검색 결과**
![JUNGBA_SearchBoardResult](https://user-images.githubusercontent.com/64412357/103851507-52b50b80-50ed-11eb-8f89-4207bf969103.png)

- 카테고리, 접수상태, 취업상태, 대상별, 지역별 등 원하는 필터를 설정하여 검색할 수 있다.
- 선택한 필터에 따른 검색 결과가 페이지 리로드 없이(비동기식 Ajax) 검색 부분 하단에 출력된다.

<br>

### 커뮤니티
Feed형식 Board형식

### 알림



### 채팅



 - 마이페이지 일부<br>

![](./images/myPage.JPG)

<br><br>

 - 여행코스 리스트<br>

![](./images/image/courseList.JPG)
<br>
**사용자가 만든 코스를 보여줍니다. 코스제목, 작성자, 여행코스, 총거리, 표지이미지를 볼 수 있습니다. <br>
또한 사용자는 각 테마를 선택하여, 다양한 코스를 볼 수 있습니다.**

<br><br>

 - 여행코스 등록<br>

![](./images/image/insertCourse1.JPG)
![](./images/image/insertCourse2.jpg)
<br>
**①사용자는 표지 이미지를 선택할 수 있습니다. 자바 스크립트를 사용하여, 사용자가 첨부한 이미지는 즉시 화면에 출력됩니다. <br>
②사용자는 코스로 짜고싶은 장소를 키워드를 통해 검색할 수 있고, 여행코스로 추가할 수 있습니다. 사용자가 여행코스를 추가하면, 해당 장소의 이름, 위도, 경도 데이터를 파싱해옵니다. 파싱한 위도 경도를 활용하여 두 장소간의 거리를 계산하고, 오른쪽 화면에 여행지 이름과 두 장소간의 거리가 출력됩니다. <br>
③지도에는 여행코스가 보기 쉽게 선 형태로 나타납니다. 저는 사용자가 동선을 생각하면서 효율적으로 코스를 짤 수 있도록 기능을 구현해 보았습니다. <br><br>
각 장소마다의 이름, 경도, 위도를, 또 다음 장소의 이름, 경도 위도를 모두 저장해야 하기에, 데이터 관리의 문제가 발생하였습니다. 이를 해결하고자 데이터 베이스 등록시 하나의 문자열로 묶었고 쉼표로 구분해 주었습니다.최종적으로 데이터베이스에는 String 타입으로 들어갑니다. 그리고 쉼표로 구성된 문자열을, 뷰로 그대로 가져와 jstl의 split 함수를 활용하여 배열로 변경해주었습니다. 최종적으로 배열을 가지고 여행코스 리스트를 출력합니다.**


<br><br>

 - 여행코스 조회<br>

![](./images/image/courseDetail.JPG)

<br><br>


