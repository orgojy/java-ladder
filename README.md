# 사다리 게임

## 진행 방법

* 사다리 게임 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정

* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/nextstep-step/nextstep-docs/tree/master/codereview)

## 기능 요구사항

* 사다리 게임에 참여하는 사람에 이름을 최대5글자까지 부여할 수 있다. 사다리를 출력할 때 사람 이름도 같이 출력한다.
* 사람 이름은 쉼표(,)를 기준으로 구분한다.
* 사람 이름을 5자 기준으로 출력하기 때문에 사다리 폭도 넓어져야 한다.
* 사다리 타기가 정상적으로 동작하려면 라인이 겹치지 않도록 해야 한다.
    * |-----|-----| 모양과 같이 가로 라인이 겹치는 경우 어느 방향으로 이동할지 결정할 수 없다.
* 사다리 실행 결과를 출력해야 한다.
* 개인별 이름을 입력하면 개인별 결과를 출력하고, "all"을 입력하면 전체 참여자의 실행 결과를 출력한다.
* 추가로 제공되는 "객체 설계 힌트"를 참고해 철저하게 TDD로 재구현해 본다.

## 프로그래밍 요구사항

* 자바 8의 스트림과 람다를 적용해 프로그래밍한다.
* 규칙 6: 모든 엔티티를 작게 유지한다.
* 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.

## 실행 결과

* 위 요구사항에 따라 4명의 사람을 위한 5개 높이 사다리를 만들 경우, 프로그램을 실행한 결과는 다음과 같다.

> 참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)  
> pobi,honux,crong,jk
>
> 실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)  
> 꽝,5000,꽝,3000
>
> 최대 사다리 높이는 몇 개인가요?  
> 5
>
> 사다리 결과
>
> ![ladder_game_result](https://user-images.githubusercontent.com/15815583/147233380-fa711eab-2f3f-4c5d-8c81-4137bc4e20ac.png)
>
> 결과를 보고 싶은 사람은? ('참여자이름' or 모든 참여자:'all')  
> pobi
>
> 실행 결과  
> 꽝
>
> 결과를 보고 싶은 사람은? ('참여자이름' or 모든 참여자:'all')  
> all
>
> 실행 결과  
> pobi : 꽝  
> honux : 3000  
> crong : 꽝  
> jk : 5000

## 힌트

* 2차원 배열을 ArrayList, Generic을 적용해 구현하면 ArrayList<ArrayList<Boolean>>와 같이 이해하기 어려운 코드가 추가된다.
* 사다리 게임에서 한 라인의 좌표 값을 가지는 객체를 추가해 구현해 본다.

```
public class Line {
    private List<Boolean> points = new ArrayList<>();

    public Line (int countOfPerson) {
        // 라인의 좌표 값에 선이 있는지 유무를 판단하는 로직 추가
    }

    [...]
}
```

* 위와 같이 Line 객체를 추가하면 ArrayList<ArrayList<Boolean>> 코드를 ArrayList<Line>과 같이 구현하는 것이 가능해 진다.
* 각 로직을 구현하기 위해 필요한 데이터를 가지는 객체를 분리하기 위해 노력해본다.
* 로직 구현에 필요한 데이터를 가지는 객체를 잘 분리하면 의외로 쉽게 문제를 해결할 수 있다.
* 각 객체가 2개 이하의 인스턴스 변수만을 가지도록 구현해 본다.

## 객체 설계 힌트

* 사다리 한 Line 추상화
    * 사다리 게임에서 한 Line을 LadderLine으로 이름을 붙이고 다음과 같이 구현
    * 사다리 Line의 모든 Point 초기화와 이동을 담당
* LadderLine의 두 점과 현재 위치를 Point로 추상화
    * LadderLine에서 위치와 각 점의 방향을 관리
* 각 Point의 좌/우 방향을 Direction으로 추상화
    * 각 Point의 좌/우 방향 정보를 가진다.
    * 현재 Point에서 다음 Point를 생성하는 역할

## 기능 구현 목록

- [x] 사다리 게임에 필요한 입력 뷰 `InputView`
    - [x] 참여자 이름 입력 기능 추가
    - [x] 최대 사다리 높이 입력 기능 추가
    - [x] 사다리 실행 결과 입력 기능 추가
    - [x] 참여자의 사다리 결과 입력 기능 추가
    - [x] 리팩토링
- [x] 사다리 게임에 필요한 출력 뷰 `OutputView`
    - [x] 실행결과 출력 기능 추가
    - [x] 실행된 게임 결과 모델을 출력을 위한 전달 기능 추가
    - [x] 사다리 게임의 사용자와 사다리 모형 출력 기능 추가
    - [x] 참여자의 사다리 실행 결과 출력 기능 추가
    - [x] 참여자의 사다리 실행 결과 출력 기능 구현 리팩토링
    - [x] 사다리 게임 실행 후, 사용자의 실행 결과 출력 기능 추가
    - [x] 사다리 게임 참여자 실행 결과 선택 기능 추가
    - [x] 리팩토링
- [x] 사다리 게임 실행 결과를 보기 위한 명령어 `LadderResultCommand`
    - [x] 게임 실행 결과를 보기 위한 명령어 생성 기능
    - [x] 게임 실행 결과를 보기 위한 명령어 생성 검증 기능
    - [x] 명령어가 'all'인지 여부 확인 기능 추가
    - [x] 사용자 이름 명령어 `UserName` 생성 기능 추가
- [x] `LadderController`에 `Ladder` 생성 파라미터를 전달하기 위한 클래스 `CreateLadderRequest`
    - [x] 전달 인스턴스 생성 기능 추가
    - [x] 전달 파라미터 검증 기능 추가
    - [x] `Users`를 만드는 빌더 메서드 추가
    - [x] 사다리 생성시 필요한 파라미터(사용자 수, 사다리 높이)로 변경
    - [x] 리팩토링
- [x] `LadderController`에 게임 결과 확인 파라미터를 전달하기 위한 클래스 `RetrieveResultRequest`
    - [x] 전달 인스턴스 생성 기능 추가
    - [x] 전달 파라미터 검증 기능 추가
    - [x] 리팩토링
- [x] `LadderController`에 게임 결과 확인 파라미터를 전달하기 위한 클래스 `UserLadderResults`
    - [x] 전달 인스턴스 생성 기능 추가
    - [x] 전달 파라미터 검증 기능 추가
    - [x] 사용자 게임 결과 갯수 기능 추가
    - [x] 리팩토링
- [x] 사다리 게임 요청 처리를 위한 컨트롤러 `LadderController`
    - [x] 컨트롤러 생성
    - [x] 참여자/사다리 높이를 입력받아 게임 시작 기능 추가
    - [x] `InputView`에서 전달받는 파라미터 클래스 `RunRequest` 추가
    - [x] 사다리 게임 결과 검색 기능 추가
    - [x] 불필요한 인스턴스화 제거를 위해 정적 메서드 사용 방식으로 변경
- [x] 사다리 게임 실행 모델 `LadderGame`
    - [x] 게임 실행 모델 생성 기능 추가
    - [x] 게임 실행 모델 생성 검증 기능 추가
    - [x] 리팩토링
- [x] 게임 참여자인 사용자 모델 `User`
    - [x] 사용자 모델 생성
    - [x] 사용자 모델 검증 추가
    - [x] 리팩토링
- [x] 사용자 이름 `UserName`
    - [x] 사용자 이름 생성
    - [x] 사용자 이름 검증 VO 추가
    - [x] 리팩토링
- [x] 게임 참여자들을 그룹화하기 위한 모델 `Users`
    - [x] 사용 모델 그룹 생성
    - [x] 게임 참여자들 중 특정 참여자 반환 기능 추가
- [x] 사다리 모델 `Ladder`
    - [x] 사다리 생성 기능 추가
    - [x] 사다리 생성 검증 기능 추가
    - [x] 사다리 높이 반환 메서드 추가
    - [x] 사다리 타기 이동 메서드 추가
    - [x] 리팩토링
- [x] 사다리 연결 라인 모델 `LadderLine`
    - [x] 사다리 연결 라인 생성 기능 추가
    - [x] 사다리 검증 기능 추가
    - [x] 사다리 연결 라인 이동 기능 추가
    - [x] 리팩토링
- [x] 사다리 위치/방향 지점 `LadderPoint`
    - [x] 사다리 위치/방향 지점 생성 기능 추가
    - [x] 사다리 위치/방향 지점 이동 기능 추가
    - [x] 리팩토링
- [x] 사다리 연결 방향 `PointDirection`
    - [x] 사다리 연결 방향 생성 기능 추가
    - [x] 사다리 연결 방향 이동 기능 추가
- [x] 랜덤 변수 생성기 `RandomGenerator`
    - [x] 랜덤 true/false 반환 기능 추가
- [x] 사다리 결과 `LadderResult`
    - [x] 사다리 결과 생성 기능 추가
    - [x] 사다리 결과 검증 기능 추가
    - [x] 리팩토링
- [x] 사다리 결과 그룹 `LadderResults`
    - [x] 사다리 결과 그룹 생성 기능 추가
    - [x] 사다리 결과 그룹 검증 기능 추가
    - [x] 사디리 결과 그룹 중 특정 결과 반환 기능 추가
    - [x] 사다리 결과 사이즈 반환 기능 추가
    - [x] 리팩토링
- [x] 사다리 게임 실행 결과 그룹 `UserResults`
    - [x] 사다리 게임 실행 결과 그룹 생성 기능 추가
    - [x] 사다리 게임 실행 결과 그룹 생성 검증 기능 추가
    - [x] 리팩토링
- [x] 사다리 게임 참여자 개별 실행 결과 `UserResult`
    - [x] 개별 실행 결과 생성 기능 추가
    - [x] 개별 실행 결과 생성 검증 기능 추가
    - [x] 리팩토링

* * *

# Study: 자바8 스트림, 람다, Optional

## 기능 요구사항

* 람다 실습
    * 실습 1 - 익명 클래스를 람다로 전환
        * 다음 테스트 코드에서 MoveStrategy에 대한 익명 클래스로 구현하고 있는데 람다를 적용해 구현한다.
        * nextstep.fp.CarTest의 이동, 정지 method
    * 실습 2 - 람다를 활용해 중복 제거
        * nextstep.fp.Lambda의 sumAll, sumAllEven, sumAllOverThree method 소스 코드를 확인하고 중복 제거한다.

* 스트림 실습
    * 실습 1 - map, reduce, filter
        * List에 담긴 모든 숫자 중 3보다 큰 숫자를 2배 한 후 모든 값의 합을 구한다. 지금까지 학습한 map, reduce, filter를 활용해 구현해야 한다.
        * nextstep.fp.StreamStudyTest 클래스의 sumOverThreeAndDouble() 테스트를 pass해야 한다.
    * 실습 2 - map, reduce, filter
        * nextstep.fp.StreamStudy 클래스의 printLongestWordTop100() 메서드를 구현한다. 요구사항은 다음과 같다.
            * 단어의 길이가 12자를 초과하는 단어를 추출한다.
            * 12자가 넘는 단어 중 길이가 긴 순서로 100개의 단어를 추출한다.
            * 단어 중복을 허용하지 않는다. 즉, 서로 다른 단어 100개를 추출해야 한다.
            * 추출한 100개의 단어를 출력한다. 모든 단어는 소문자로 출력해야 한다.

* Optional 실습
    * 요구사항 1 - Optional을 활용해 조건에 따른 반환
        * nextstep.optional.User의 ageIsInRange1() 메소드는 30살 이상, 45살 이하에 해당하는 User가 존재하는 경우 true를 반환하는 메소드이다.
        * 같은 기능을 Optional을 활용해 ageIsInRange2() 메소드에 구현한다. 메소드 인자로 받은 User를 Optional로 생성하면 stream의 map, filter와 같은 메소드를
          사용하는 것이 가능하다.
        * nextstep.optional.UserTest의 테스트가 모두 pass해야 한다.
    * 요구사항 2 - Optional에서 값을 반환
        * nextstep.optional.Users의 getUser() 메소드를 자바 8의 stream과 Optional을 활용해 구현한다.
            * 자바 8의 stream과 Optional을 사용하도록 리팩토링한 후 UsersTest의 단위 테스트가 통과해야 한다.
        * nextstep.optional.ComputerStore.getVersionOptional() 메소드를 자바 8의 stream과 Optional을 활용해 구현한다.
            * 자바 8의 stream과 Optional을 사용하도록 리팩토링한 후 ComputerStore의 단위 테스트가 통과해야 한다.
    * 요구사항 3 - Optional에서 exception 처리
        * nextstep.optional.ExpressionTest의 테스트가 통과하도록 Expression의 of 메소드를 구현한다.
        * 단, of 메소드를 구현할 때 자바 8의 stream을 기반으로 구현한다.
