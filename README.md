# practical-testing
실용적인 테스트 가이드 🛠️

## ✍️ Commit organizing

<br>

[Spring & JPA 기반 테스트 (3)](https://github.com/mingeonho1/practical-testing/commit/fe52b82f7e95845e219c8b66f8bf063c1d368243)
- Business Layer 테스트 마무리
- SpringBootTest와 DataJpaTest 차이점
- 테스트에서 Transactional 잘 알고 사용하기, tearDown을 했을 때와 어떤 차이가 있는지 (더티체킹)

항상 테스트를 잊지 말자 !

Next todo 리스트
- deleteAll deleteAllInBatch 차이
- createOrderWithNoStock 테스트
그냥 재고가 부족한 경우와 deduct를 하고 재고가 부족한 경우 다음에 알아볼 것

<br>

[Spring & JPA 기반 테스트 (2)](https://github.com/mingeonho1/practical-testing/commit/acfb6f54c5b074e791f22a586e1064fa12b06c4b)
- Persistence Layer 테스트
- Business Layer 테스트

api 흐름대로 TDD 방식으로 테스트 후 로직 구성

<br>

[Spring & JPA 기반 테스트](https://github.com/mingeonho1/practical-testing/commit/65451fba55c183b02d790a6d145f1060074a5bd4)
- 레이어드 아키텍처와 테스트
- Spring / JPA 훑어보기 & 기본 엔티티 설계
- Persistence Layer 테스트 진행중 ~

<br>

[테스트는 문서다.](https://github.com/mingeonho1/practical-testing/commit/b0842086c9711a41366eebe12159ad47d6683736)
- DisplayName
  : 도메인 정책, 용어를 사용한 명확한 문장으로 이해 돕기
- BDD
  : Given / When / Then 주어진 환경, 횅동 상태 변화
- 언어가 사고를 제한한다.
  : 명확하게 표현하지 못한 테스트자체가 오히려 나중에 허들이 돼고, 나중에 사고를 제한하고 발목을 잡을 수 있는 무언가가 될 수 있다.
  
<br>

[TDD 맛보기](https://github.com/mingeonho1/practical-testing/commit/a25346475566872518ebb296793cc138b0f1fbf0)
- Red Green Refactor
- 선 테스트 작성, 후 기능 구현
  : 복잡도가 낮은 테스트 가능한 코드로 구현할 수 있게 한다.
    쉽게 발견하기 어려운 엣지 케이스를 놓치지 않게 해준다.
    구현에 대한 빠른 피드백을 받을 수 있다.
    과감한 리팩토링이 가능해진다.

<br>

[테스트 하기 어려운 영역을 분리하기](https://github.com/mingeonho1/practical-testing/commit/98ab9c87740df3d77e876daf4a53e9082395fefd)
- 외부로 분리할수록 테스트 가능한 코드는 많아진다.
- 테스트하기 여러운 영역
  : 관측할 때마다 다른 값에 의존하는 코드
    - 현재 날짜/시간, 랜덤 값, 전역 변수/ 함수, 사용자 입력 등
    외부 세계에 영향을 주는 코드
    - 표준 출력, 메시지 발송, 데이터베이스에 기록하기 등

<br>

[테스트 케이스 세분화하기](https://github.com/mingeonho1/practical-testing/commit/1fec4e1c171b39574d1536ee9a41f9ca8c2dce4e)
- 테스트 케이스를 시야를 넓히고 질문을 던지는 습관을 기르고
- 경계값에 대한 테스트를 해보자 !

<br>

[단위 테스트 진행완료](https://github.com/mingeonho1/practical-testing/commit/ede967d42a3bd68425a8729759fcf489eb999472)
- JUnit5로 테스트하기

<br>

[Practical Testing: 실용적인 테스트 가이드](https://github.com/mingeonho1/practical-testing/commit/dcca045d4ff447357092f3951a9e547b1bbd71e4)
- 수동테스트가 아닌
- 자동화된 테스트 시작 !
