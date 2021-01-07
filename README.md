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

**게시글 목록 검색**
![JUNGBA_SearchBoard](https://user-images.githubusercontent.com/64412357/103851310-d6222d00-50ec-11eb-9cd0-030b978523ed.png)

**게시글 검색 결과**
![JUNGBA_SearchBoardResult](https://user-images.githubusercontent.com/64412357/103851507-52b50b80-50ed-11eb-8f89-4207bf969103.png)

- 카테고리, 접수상태, 취업상태, 대상별, 지역별 등 원하는 필터를 설정하여 검색할 수 있다.
- 선택한 필터에 따른 검색 결과가 페이지 리로드 없이(비동기식 Ajax) 검색 부분 하단에 출력된다.

<br>

### 게시글 등록
![JUNGBA_BoardWrite](https://user-images.githubusercontent.com/64412357/103854278-6a8f8e00-50f3-11eb-8df0-1d0e43848c63.png)

- 카테고리, 대상, 지역 등을 선택한다.
- 메인 이미지를 첨부하여 게시글 목록에 노출시킬 수 있다.
- 참고자료를 첨부하여 게시글을 조회하는 사람들이 다운로드 할 수 있다.

<br>

### 게시글 상세조회
![JUNGBA_BoardDetail](https://user-images.githubusercontent.com/64412357/103854271-68c5ca80-50f3-11eb-99d2-94bd03c1899f.png)

- 게시글의 내용을 상세하게 조회할 수 있다.
- 첨부파일을 다운로드할 수 있다.
- 로그인 유저는 댓글을 작성 및 삭제할 수 있다.

<br>

### 마이페이지
![JUNGBA_MyPage](https://user-images.githubusercontent.com/64412357/103854290-74b18c80-50f3-11eb-923c-e6a2f86186f5.png)

- 프로필 사진을 업로드, 수정, 삭제할 수 있다.
- 왼쪽 네비게이션 메뉴를 이용하여 내가 쓴 게시글들을 조회할 수 있다.
- 하단에는 게시판별 내가 쓴 게시글들 중 최신 TOP4 들을 불러온다.

<br>

### 관리자
![JUNGBA_Admin](https://user-images.githubusercontent.com/64412357/103857126-39b25780-50f9-11eb-968a-04b8bd909264.png)

- 왼쪽 네비게이션 메뉴를 이용하여 회원 목록 조회, 지원정책 신청, 대외활동 신청 목록을 조회할 수 있다.
- 게시글 상세조회에서 관리자는 등록하기 버튼을 통해 해당 게시글을 메인 게시글로 등록할 수 있다.
- 하단에는 게시판별 최신글 TOP6 들을 불러온다.




