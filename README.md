# 🧑‍💻 Spring Project 공동구매, 공동배송

💡 스프링 핵심원리, 스프링 MVC 강의를 수강하고 새로운 프로젝트를 설계, 구현하면서 단계별 복습을 하고자 한다.
내 주변에서 배송이나 배달을 같이해서 배송비를 아끼거나 최소 주문 금액을 채우기 위한 그룹을 모집할 수 있는 플랫폼을 구현하고자 한다.
(개발 진행 내용을 기록)

## 📂 비즈니스 요구사항과 설계
- 회원
    - 회원을 가입, 조회할 수 있다
    - 회원은 식별자, 이름, 주소
    - 회원데이터는 자체 DB를 구축해 관리한다. (우선은 메모리로 개발/사용)
- 대상물건(음식)
    - 이름, 구매처, 참고링크, 최소주문금액, 배송(배달)비
- 팟
    - 회원은 팟을 생성(모집)할 수 있다.
    - 회원은 팟에 참가할 수 있다.
    - 팟은 대상물건(음식), 인원, 모집기간, 인당 최소 주문 금액, 수령 장소
    - 팟 생성시 팟의 위치 지정 정책에 따라 수령 장소를 지정할 수 있다. (위치지정정책은 변경 가능성 有)
    - 회원은 위치기반 정책에 따라 주위의 팟을 조회할 수 있다. (위치기반정책은 변경 가능성 有)
- 위치
    - 위치지정정책 : 물건(음식) 수령 장소를 팟 생성인의 위치 주변 1000m 안에서 지정할 수 있다
    - 위치기반정책 : 회원의 위치 주변 1000m 안에서 열린 팟만 조회할 수 있다.

## 📂 Domain
### ✏️ 회원 도메인 설계 - Member
- `Member` : 회원 식별자, 로그인 아이디, 이름, 비밀번호, 주소(위도, 경도), 팟 리스트를 필드로 가지는 클래스
- `MemberEditForm` : 회원 정보를 수정할 시, 수정 폼에서 데이터를 받아 저장할 클래스
- `MemberJoinForm` : 회원 가입 시, 가입 폼에서 데이터를 받아 저장할 클래스
- `MemberRepository` : 회원 정보들을 저장할 공간을 활용하는 메소드를 포함한 인터페이스
- `MemberRepositoryMemory` : 회원 정보들을 메모리에 저장하여 활용하는 클래스, `MemberRepository`를 구현함 ( 향후 `MemberRepositoryDB` 클래스 구현 예정 )
- `MemberService` : 회원 정보 관련 서비스(회원 가입, 회원 조회, 회원 정보 수정 등)들을 포함한 인터페이스
- `MemberServiceImpl` : 회원 정보 관련 서비스들을 구현한 클래스, `MemberService`를 구현함
### ✏️ 팟 도메인 설계 - Party
- `Party` : 팟 식별자, 팟 생성 회원 식별자, 팟 대상 상품(음식), 모집인원, 모집기간, 인당 최소 주문 금액, 수령 장소, 팟 참여 멤버 리스트를 필드로 가지는 클래스
- `PartyCreateForm` : 팟 생성 시, 생성 폼에서 데이터를 받아 저장할 클래스
- `PartyRepository` : 팟 정보들을 저장할 공간을 활용하는 메소드를 포함한 인터페이스
- `PartyRepositoryMemory` : 팟 정보들을 메모리에 저장하여 활용하는 클래스, `PartyRepository`를 구현함 ( 향후 `PartyRepositoryDB` 클래스 구현 예정 )
- `PartyService` : 팟 관련 서비스(팟 생성, 팟 조회, 팟 참가, 팟 삭제 등)들을 포함한 인터페이스
- `PartyServiceImpl` : 팟 관련 서비스들을 구현한 클래스, `PartyService`를 구현함
### ✏️ 상품 도메인 설계 - Item
- `Item` : 공동 구매/공동 배송 대상 상품/음식을 나타낸 도메인이며, 상품명(구매처), 참고 링크, 최소 주문 금액, 배송(배달)비를 필드로 가지는 클래스
### ✏️ 위치 도메인 설계 - Location
- `Location` : 회원의 주소, 팟의 수령 장소를 지정할 시 사용하는 위치를 나타낸 도메인이며, 위도, 경도를 필드로 가지는 클래스
- `LocationPolicy` : 팟의 수령 장소를 지정할 시 반영할 위치 지정 정책 만족 여부를 판단하는 메소드를 포함한 인터페이스
- `LocationPolicyImpl` : 팟의 수령 장소를 팟 생성 회원의 주소로부터 일정 거리 내에 있는 것만 지정할 수 있도록 구현한 클래스, `LocationPolicy`를 구현함
- `LocationPolicyImplV2` : 팟의 수령 장소를 어느 곳으로 지정해도 되도록 구현한 클래스, `LocationPolicy`를 구현함
### ✏️ 로그인 도메인 설계 - Login
- `LoginForm` : 로그인 시, 로그인 폼에서 데이터를 받아 저장할 클래스
- `LoginService` : 로그인 서비스를 포함한 인터페이스
- `LoginServiceImpl` : 로그인 서비스를 구현한 클래스, `LoginService`를 구현함

## 📂 Controller
### ✏️ Home Controller
💡 메인 홈 화면 페이지를 위한 controller 입니다
- `GET` `/` : 현재 로그인된 상태가 아니면 home 뷰를 렌더링하고, 로그인 된 상태이면 로그인 회원 주변의 팟들과 회원 정보를 모델로 담아 loginHome 뷰를 렌더링
### ✏️ Login Controller
💡 회원 로그인, 회원 로그아웃을 위한 controller 입니다
- `GET` `/login` : 로그인 폼을 보여주기 위해 loginForm 뷰를 렌더링
- `POST` `/login` : 로그인 폼으로부터 받은 데이터를 `LoginForm` 클래스 타입 객체에 담아 `LoginService` 를 통해 로그인 시도, 실패시 오류 메세지와 함께 loginForm 뷰 재 렌더링, 성공시 세션에 로그인 정보 저장하고 리다이렉션
- `POST` `/logout` : 로그인 정보 저장한 세션 삭제하고 리다이렉션
### ✏️ Member Controller
💡 내 회원 정보 조회, 내 정보 수정, 내 참가 팟 조회 등을 위한 controller 입니다
- `GET` `/member` : 로그인한 회원의 회원 정보를 보여주기 위해 member 뷰를 렌더링
- `GET` `/member/edit` : 로그인한 회원의 회원 정보를 수정하기 위한 수정 폼을 보여줄 memberEditForm 뷰를 렌더링
- `POST` `/member/edit` : 회원 정보 수정폼으로부터 받은 데이터를 `MemberEditFrom` 클래스 타입 객체에 담아 `MemberService`를 통해 회원 정보 수정, 수정 후 `/member`로 리다이렉션
- `GET` `/member/parties` : 로그인한 회원의 참가 팟 정보를 보여주기 위해 `partyService`를 통해 '내 참가 팟 리스트'를 가져와 모델로 담아서 myParties 뷰를 렌더링
### ✏️ Party Controller
💡 팟 생성, 나의 팟 조회, 팟 참가 등을 위한 controller 입니다 
[MEMO] : 검증처리 애노테이션 중 @valid 사용하면 해당 대상 객체의 확인 조건을 만족할 경우 통과 가능 ⇒ 중첩으로 객체를 검증 가능
- `GET` `/party/{partyId}` : 특정 팟의 정보를 보여주기 위해 `partyService`를 통해 partyId 식별자의 팟 정보를 찾아 모델로 담아서 party 뷰를 렌더링
- `GET` `/party/add` : 팟을 생성 폼을 보여주기 위해 partyAddForm 뷰를 렌더링
- `POST` `/party/add` : 팟 생성 폼으로부터 받은 데이터를 `PartyCreateFrom` 클래스 타입 객체에 담아 이를 통해 `Party` 클래스 타입의 새 객체 생성. `PartyService`를 통해 팟 생성 시도, 실패시 오류 메세지와 함께 partyAddForm 뷰 재 렌더링, 성공시 `/`으로 리다이렉션.
- `POST` `/party/participate/{partyId}` : 로그인한 회원이 특정 팟에 참가하기 위해 회원 정보와 팟 식별자를 가지고 `PartyService`를 통해 해당 팟에 참가. 참가 완료 후 `/`으로 리다이렉션
### ✏️ Sign Up Controller
💡 회원 가입을 위한 controller 입니다
- `GET` `/signup` : 회원 가입 폼을 보여주기 위해 memberAddForm 렌더링
- `POST` `/signup` : 회원 가입 폼으로부터 받은 데이터를 `MemberJoinForm` 클래스 타입 객체에 담아 이를 통해 `Member` 클래스 타입의 새 객체 생성. `MemberService`를 통해 회원 가입 시도, 실패시 오류 메세지와 함께 memberAddForm 재 렌더링, 성공시 `/`으로 리다이렉션

## 📂 Interceptor
### ✏️ Login Check Interceptor
### ✏️ Log Interceptor

## 📂 Argument Resolver
### ✏️ Login Annotation
### ✏️ Login Member Argument Resolver

