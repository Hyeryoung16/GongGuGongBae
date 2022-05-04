# Spring Project 공동구매, 공동배송

<aside>
💡 스프링 핵심원리, 스프링 MVC 강의를 수강하고 새로운 프로젝트를 설계, 구현하면서 단계별 복습을 하고자 한다.
내 주변에서 배송이나 배달을 같이해서 배송비를 아끼거나 최소 주문 금액을 채우기 위한 그룹을 모집할 수 있는 플랫폼을 구현하고자 한다.

</aside>

# [1] 비즈니스 요구사항과 설계 [ 1차 - 2022.03.28 ]

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

---

### 회원 도메인 설계

- 회원 클래스 다이어그램

---

### 팟, 위치 도메인 설계

1. 팟 생성 : 회원은 팟 서비스에 팟 생성 ( 회원 id, 대상물건, 인원, 인당 최소 주문 금액, 수령장소 ) 을 요청한다.
2. 팟 서비스는 팟 생성을 요청한 회원의 주소를 멤버(회원)저장소로부터 조회한다. 
3. 수령 장소 지정 적용 : 팟 서비스는 팟을 생성한 회원의 위치에 따른 수령 장소 지정 가능 여부를 위치 정책에게 위임한다.
4. 팟 생성 결과 반환 : 팟 서비스는 생성된 팟을 팟 저장소에 저장하고 결과를 반환한다.

- 팟 클래스 다이어그램

---

# [2-1] 객체 지향 원리 적용

## 새 위치 정책 적용 시 문제점

`LocationPolicyImpl` 대신 `LocationPolicy` 인터페이스를 구현하는 `LocationPolicyImplV2` 로 변경하려고 할 때, `PartyServiceImpl` 을 수정해야하는 상황 발생 한다 ⇒ **OCP 위반** [[OOP] : 객체지향원리](https://www.notion.so/OOP-0d4e30d07c5844fc8225b944912fcc19) 

`PartyServiceImpl` 은 추상인 `LocationPolicy` 과 구체인 `LocationPolicyImplV2` 를 동시에 의존한다 ⇒ **DIP 위반 [[OOP] : 객체지향원리](https://www.notion.so/OOP-0d4e30d07c5844fc8225b944912fcc19)** 

```java
private final MemberRepository memberRepository = new MemberRepositoryMemory(); // 멤버 저장소
private final PartyRepository partyRepository = new PartyRepositoryMemory(); // 파티 저장소
// private final LocationPolicy locationPolicy = new LocationPolicyImpl(); // 위치 정책 ( 수정 전 )
private final LocationPolicy locationPolicy = new LocationPolicyImplV2(); // 위치 정책 ( 수정 후 )
```

## 해결 - 의존성주입

1. 추상(인터페이스)에만 의존하도록 변경하자 ( 공연 - 배역 ) 

```java
private final MemberRepository memberRepository;
private final PartyRepository partyRepository;
private final LocationPolicy locationPolicy;
```

구체가 없으므로 NPE 발생

1. 구체를 생성하고 주입해주는 다른 클래스 `AppConfig` 를 생성하자 ( 공연 - 공연 기획자 ) 
    1. `AppConfig` 에서는 `PartyServiceImpl` , `MemberServiecImpl, MemberRepositoryMemory` , `PartyRepositoryMemory`, `LocationPolicyImplV2` 생성
    2. 생성자를 통해서 주입
        1. `MebmerServiceImpl` ← `MemberRepositoryMemory`
        2. `PartyServiceImpl` ← `MemberRepositoryMemory` , `PartyRepositoryMemory`, `LocationPolicyImplV2`
        
2. 클라이언트(service) 코드에서는 의존관계를 생성자 주입으로 변경하자
3. 다시 위치 정책을 변경하고자 하면 `AppConfig`에서만 변경해주면 된다

## 정리

- 새로운 위치 정책으로 변경하려 하니, `PartyServiceImpl` 코드도 변경해야 하는 상황 발생
- `PartyServiceImpl` 가 추상뿐만 아니라 구체에도 의존하고 있기 때문
- `PartyServiceImpl` 는 의존하는 추상의 구체가 어떤 것이든 상관없이 동작해야 함
    - ex 연극에서 남주는 여주 배우가 누구든지 상관없이 연기해야 함
- `PartyServiceImpl` 가 직접 구체를 지정하는 것이 아닌 다른 클래스(`AppConfig`)에서 구체를 생성하여 `PartyServiceImpl` 에게 주입하는 방식으로 변경하고 자 함
- `AppConfig` 가 구체를 생성하고, 연결하는 일을 담당함
    - ex 연극 기획자가 남주 역할의 배우와 여주 역할의 배우를 선택, 지정 담당
- 애플리케이션이 **사용영역**과 **구성영역**으로 나뉨

---

# [2-2] 스프링 기반으로 변경

---

# Member Controller

<aside>
💡 내 회원 정보 조회, 내 정보 수정을 위한 controller 입니다

</aside>

# Sign Up Controller

<aside>
💡 회원 가입을 위한 controller 입니다

</aside>

# Home Controller

<aside>
💡 메인 홈 화면 페이지를 위한 controller 입니다

</aside>

# Login Controller

<aside>
💡 회원 로그인, 회원 로그아웃을 위한 controller 입니다

</aside>

# Party Controller

<aside>
💡 팟 생성, 나의 팟 조회 및 수정을 위한 controller 입니다

</aside>

[MEMO]

- 검증처리 애노테이션 중 @valid 사용하면 해당 대상 객체의 확인 조건을 만족할 경우 통과 가능 ⇒ 중첩으로 객체를 검증 가능

---

# Interceptor

# Argument Resolver
