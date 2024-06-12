# MoaMoa
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/9fe65f5e-c41d-4532-bd5f-f3fb2578e41d)


# Introduction

합리적인 소비를 하기 위해 모인 사람들과 필요한 것을 공동으로 구매할 수 있는 사이트

개발 기간 : 2024.02.26 ~ 2024.04.05

---
# Service Architecture
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/3cb0311e-d5df-4794-b0b9-ba04d080ec14)





# Technology
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/b4e80e98-1e41-4017-82db-62c0e979b3ce)
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/4b47b474-05d2-4dee-975f-ca9e879e8c91)
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/147768a3-2363-4e36-9da3-172c958cdadc)
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/e812a620-036e-417b-99b4-a1224ebe4917)
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/b6d68008-7a2a-42ff-8099-51d353297fcf)
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/4338160c-2384-43b5-9372-96ee0d29f8fe)
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/49499ac3-577b-4a2f-8fd7-7e040c6ed04e)
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/03309061-23d6-4713-a7b0-fd95ac957827)
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/b0635d06-9c88-415b-93c4-9e8279c813c1)
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/f335bc87-c354-4662-9c0f-9d1b27d943e1)
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/fccabf1b-0925-479f-bd2d-f9d264cbfed8)
![image](https://github.com/cwc-moamoa/moamoa/assets/149165093/ec44aa65-c9d0-40f3-8565-7ee8abea021e)













---
# DB modeling

<img width="1152" alt="image" src="https://github.com/cwc-moamoa/moamoa/assets/120659405/18f0343f-2184-45a4-af35-c0c9eb9b6858">


---
# Features

- products
    - 전체 상품 조회 
    - 제품 상세 정보 조회
    - 상품 등록
    - 상품 수정
    - 상품 삭제
- groupPurchase
    - 공동구매 참여
    - 개인 일반 구매
- image
    - 이미지 업로드
- payment
    - 결제
    - 결제 검증
- order
    - 전체 주문 조회
    - 주문 상세 정보 조회
    - 주문 생성
    - 주문 수정
    - 주문 취소
    - 주문 추적 상태 변경(판매자)
    - 판매 내역 조회(판매자)
- review
    - 전체 리뷰 조회 
    - 리뷰 등록
    - 리뷰 수정
    - 리뷰 조회
    - 리뷰 삭제
- like
    - 상품 좋아요
    - 상품 좋아요 취소
    - 리뷰 좋아요
    - 리뷰 좋아요 취소
- search
    - 상품 검색
    - 리뷰 검색
    - 좋아요 순 상품 검색
    - 좋아요 순 리뷰 검색
    - 별점 순 상품 검색
    - 인기 검색어 조회
- seller
    - 판매자 가입
    - 판매자 로그인
    - 판매자 탈퇴
- socialUser
    - 카카오 소셜 로그인
    - 조건을 만족하는 데이터로 회원가입
 
---

# Project Structure
**Domain** 패키지 
- Controller: 클라이언트의 요청을 받고 응답을 반환하는 역할. 각각의 엔드포인트에 대한 요청을 처리, 비즈니스 로직 호출.
- Dto: 클라이언트와 서버 간의 데이터 전송을 위한 객체들이 위치. 
- Model(Entity): 비즈니스 객체들을 나타내는 클래스들이 위치. 사용자(User), 제품(Product), 주문(Order) 등의 엔티티 포함.
- Repository: 데이터베이스와 상호작용하기 위한 인터페이스들이 위치. 각 엔티티에 대한 CRUD 동작을 정의하고 구현.
- Services: 비즈니스 로직을 수행하는 메소드들이 위치.

**Infra** 패키지 
- Amazon S3(Simple Storage Service) : 이미지 및 멀티미디어 콘텐츠를 저장하기 위한 Amazon S3 설정 포함.
- Security : 사용자 인증 및 권한 부여를 위한 시큐리티 설정. JWT를 통한 인증 관련 내용도 포함.
- Swagger : API 문서 자동화 및 테스트를 위한 스웨거 설정.
- Exception : 예외 처리와 관련된 클래스들을 담당. 프로그램 실행 중 발생할 수 있는 예외 상황을 처리하기 위한 클래스들 위치.


---

## 기술적 의사결정

| 사용 기술 | 기술 설명 |
| --- | --- |
| GitHub Actions | GitHub와 통합되어 높은 접근성. 수평 확장이 용이. |
| redis | 동시성 제어를 위해 분산lock을 구현하기 위해서 , redis에서 제공하는 클라이언트 중  redisson 에서 pup/sub 기능을 사용하여 락을 획득하기 위하여 사용 |
| portone | Port one이 제공하는 간편한 API를 통해 쉽게 앱에 통합해 개발 시간 단축. 글로벌 서비스를 제공해 다양한 국가에서 사용할 수 있음. (확장성 고려), 또한 테스트 결제를 지원하여 개발단계에서 테스트가 용이해서 채용. |
| supabase | 데이터베이스에 직접 액세스할 수 있는 RESTful API를 제공해 빠른 개발에 도움된다고 판단. 또한 계속 사용해왔던 서비스라 러닝커브가 다른곳에 비해 상대적으로 낮다고 생각하여 한정된 개발기간에 시간단축을 위해서 사용. |
| EC2 | 사용한만큼만 지불하는 가격 체계 제공. |
| Amazon S3 | 높은 확장성. 사용한만큼만 지불하는 가격 체계 제공. |
| ouath2 | 소셜 로그인을 위해 도입, 간편한 로그인으로 더 많은 도메인 유입을 기대 |

---
## 🛠 트러블슈팅


### ✔️ 페이징을통해 리뷰와 상품의 좋아요를 검색하는 작업을 했을 때 Hibernate Lazy Initialization Exception 오류 발생

> **해결**
> 
1. Hibernate5Module을 등록하는 방법. 해당 엔티티를 건드리지 않아도 되는 장점이 있으나 Hibernate5Modul를 알아야만 수월하게 사용 가능.
2. 문제가 발생하는 엔티티에 JsonIgnoreProperties를 추가.매우 간단하지만 근본적인 문제 해결이 아님. 
3. lazy를 eager로 변경.매우 간단하지만 항상 모든 연관된 객체를 로딩하게 되므로, 필요하지 않은 데이터까지 로딩하게 됨. 메모리 낭비 초래하고 성능 저하 가능성 우려.
4. **엔티티에 직접 연결하지 않고 dto를 통해 필요한 정보만 받아오게 하기. 엔티티와 API간의 결합도를 납춤. 엔티티의 변경이 API에 직접적인 영향을 미치는 것을 방지해 유지보수성을 향상시킴.** 

**4번 방법을 통해 오류 해결**

<img width="753" alt="스크린샷 2024-04-02 오후 4 30 22" src="https://github.com/cwc-moamoa/moamoa/assets/149165093/d22e84e6-fc94-483e-acdd-63eb484bc708">

그래서 서비스에서 엔터티에 직접 반환하면 컨트롤러로 돌아가는 순간 영속성 컨텍스트가 준영속 상태로 바뀌기 때문에 이 상황에서 Lazy로딩이 발생하면 LazyInitializationException이 에러가 발생합니다. 
이 경우 엔터티에서 DTO로 매핑하는 코드를 서비스에서 하도록 수정 하면 됩니다(트랜잭션 내에서 모든 작업이 이루어지도록)


---


### ✔️ 동일 데이터를 동시에 갱신할 경우, 이전 트랜젝션이 데이터를 갱신한 후, 트랜잭션을 종료하기 전에 나중 트랜잭션이 갱신 값을 덮어쓰는 경우 발생

<img width="758" alt="스크린샷 2024-04-02 오후 4 30 48" src="https://github.com/cwc-moamoa/moamoa/assets/149165093/465b9598-302f-401f-b90f-641f185d6bd5">

> **해결**

<img width="752" alt="스크린샷 2024-04-02 오후 4 31 04" src="https://github.com/cwc-moamoa/moamoa/assets/149165093/e1e5b15e-b18a-4126-96f9-588d5aceeb63">



---
# Built With
 [김선병](https://github.com/Karox1234) - 팀장, 주문, 결제 시스템, 동시성 제어, 프론트

 [김보성](https://github.com/96KimBoseong)  - 부팀장, 주문, CI/CD, 프론트 ,주소API

 [강민지](https://github.com/mingdorri) - 팀원, 리뷰, 멀티미디어 업로드, PPT 제작

 [이 율](https://github.com/dyorcat) - 팀원, 회원가입, 소셜로그인, 에러처리, 프론트

 [홍성욱](https://github.com/suh75321) - 팀원, 상품, 좋아요, 검색
