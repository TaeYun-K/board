타임리프 문법

th:text : 태그의 텍스트를 수정
th:if : 지정된 조건이 참일 경우 HTML 요소를 표시
th:unless : 지정된 조건이 거짓인 경우 HTML 요소 표시
th:switch : 지정된 조건에 따라 여러 HTML 요소 중 하나 표시
th:case : th:switch 와 함께 사용되어 해당 case 조건이 참일경우 해당 HTML 요소 표시
th:each : 반복문을 실행하여 HTML 요소 생성
th:object : HTML 태그에 객체를 바인딩
th:field : 폼 필드에 데이터를 바인딩
th:action : 폼의 액션 URL 설정
th:method : 폼의 http 메서드 설정

**표현식
1. 변수 표현식
   변수를 표현할 때 $와 중괄호
   ex)${name}

2. 선택 변수 표현식
   객체나 리스트에서 값을 선택하여 출력할때 사용
   ex) {user.name}
