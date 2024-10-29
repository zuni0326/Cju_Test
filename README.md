# JAVA 프로그래밍2 중간고사 과제
> **윈도우 계산기 프로그램 개발** <br/> **개발기간: 2024.10.18~2024.10.30**
## 개발자
+ 청주대학교 인공지능소프트웨어학과 3학년 2022012992 정상엽
## 프로젝트 소개
+ 청주대학교 인공지능소프트웨어학과 java 프로그래밍2 중간고사 과제로 윈도우에서 실행가능한 계산기와 흡사한 기능을 가진 
간단한 사칙연산 계산기를 구현해봤습니다.
  + 윈도우 계산기를 직접 실행해 본 결과 간단한 사칙 연산 버튼에서도 여러가지 경우의 수가 있는걸 알았고 비슷하게 구현하기 위해 노력했습니다.
+ ![계산기화면](https://github.com/zuni0326/Cju_Test/blob/main/jsyCalc.png)
+ 상단 패널엔 로그창, 결과창으로 구성되어 있고 하단 패널엔 버튼들로 구성되어 있습니다.
### 버튼 기능
  + **숫자 버튼**
    + 숫자를 누르면 왼쪽부터 입력한 숫자가 기입됩니다.
  + **사칙 연산 버튼**
    + 숫자버튼을 입력하고 사칙 연산 버튼을 입력하면 다음 숫자가 입력되기 전까지 다른 사칙연산 버튼을 누를 시 가장 최근에 누른 사칙연산 버튼으로 갱신됩니다.
    + 결과 값이 출력되고 다른 사칙 연산 버튼과 연산할 숫자를 입력하면 계속해서 연산 할 수 있습니다.
    + 나누기의 경우 0으로 나누면 오류 메세지를 출력합니다.
  + **등호 버튼**
    + 기본 사칙 연산의 결과값을 출력하고 계산을 마무리하여 저장된 값을 초기화합니다.
    + 숫자버튼과 사칙 연산 버튼을 입력 후 등호 버튼을 입력하면 처음 입력한 숫자가 자동으로 연산할 두번째 숫자로 입력됩니다.
      + 결과 값이 출력되고 다시 등호 버튼을 입력하면, 결과값을 저장된 연산자와 자동으로 배치된 두번째 숫자로 계속 연산합니다.
      + 결과 값이 출력되고 다른 숫자 버튼을 입력하면, 로그와 결과값이 초기화되며 처음부터 새로운 계산을 시작합니다.
    + 숫자버튼과 사칙 연산 버튼과 연산할 두번째 숫자 입력 후 등호를 입력하면 계산식과 결과를 출력합니다.
      + 등호를 계속 입력하여 결과값을 저장된 연산자와 입력 된 두번째 숫자로 계속 연산할 수 있습니다.
  + **부호 전환 버튼**
    + 부호 전환 버튼을 입력하여 현재 입력한 숫자의 부호를 변경 할 수 있습니다.
    + 등호 버튼을 입력하여 결과값이 출력되고난 후 부호 전환 버튼을 입력하면 로그가 초기화되고 부호가 전환된 값으로 새로 연산할 수 있습니다.
  + **소수점 버튼**
    + 소수점을 입력 할 수 있습니다.
  + **삭제 버튼**
    + CE(CLEAR ENTRY) 버튼 입력 시 마지막으로 입력한 숫자 전체를 지웁니다.
    + C(CLEAR) 버튼 입력 시 저장된 모든 값을 지웁니다.
    + ⌫(BackSpace) 버튼 입력 시 마지막으로 입력된 숫자의 한 글자를 지웁니다.
## 커밋
  + URL : <https://github.com/zuni0326/Cju_Test/commits/main/>
  + ![커밋](https://github.com/zuni0326/Cju_Test/blob/main/commit.png)

## 자기반성
Java swing과 Git, javadoc 등을 처음으로 다뤄보기에 과제를 진행하는 동안 아쉬운 점이 많았습니다. JAVA에 관련해선 자잘한 곳에서 에러가 많이 발생하여, 처음 구현하고 싶었던 범위보다 한참 축소해서 코딩하게 된 점이 아쉽습니다. 완성된 코드 자체도 스스로가 보기에 미숙한 점이 많이 보이고 타 강의에서 배운 이론적인 부분(ex. MVC 패턴)들을 잘 지키고 싶었으나 안 지켜진 부분 또한 아쉬움이 남습니다. Git 같은 경우엔 commit 하는 기준이 잘 정립되어있지 않아 commit 수가 많이 부족하고, 하나의 commit에 변경 된 점이 너무 많아 히스토리를 제대로 이해 할 수 없는 것이 아쉬웠습니다. 기말 과제 제출 시엔 잘 보완해보도록 하겠습니다. 감사합니다.
