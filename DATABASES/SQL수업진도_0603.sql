-- DESC: Description 테이블구조 설명
DESC dept; 

-- select: 테이블내용 조회, where 조회조건, as(Alias)별칭으로 필드명이 길때
-- concat: 오라클내장함수
SELECT
concat(deptno,'번') as "부서번호"
, dname as "부서명"
, concat(loc,'시') as "위치"
FROM dept 
WHERE loc = 'NEW YORK' ;
-- DUAL 가상테이블이름. 테이블이 없는 내용을 select할때
SELECT 3+5 as "3더하기8은" from dual;
-- 레코드(row) : 컬럼(field)들로 구성.
SELECT concat (count(*),'명') as "연봉이 2000이상인 직원" FROM emp WHERE sal >2000;
-- 큰따옴표(필드명), 작은따옴표(문자처리,비교,결합)
SELECT * FROM emp WHERE ename != 'KING';
SELECT * FROM emp WHERE hiredate >= '1982/01/01';
-- OR는 +(합집합)  , AND는 *(교집합)
SELECT * FROM emp WHERE deptno = 10 OR job = 'MANAGER';
SELECT * FROM emp WHERE sal NOT BETWEEN 2000 AND 3000;
SELECT * FROM emp WHERE hiredate BETWEEN '1980/01/01' AND '1980.12.31';
SELECT * FROM emp WHERE comm IN (300,500,1400);
--LIKE 조회, 와일드카드 조회
--키워드에 괄호가 있으면 함수입니다. upper(), cacat(), count() 등.
SELECT * FROM emp WHERE ename LIKE upper('K%');
SELECT * FROM emp WHERE ename LIKE '%N';
-- null 이 중요한 이유: null값이 있으면 검색X
-- 그러면, nill 값이 필드에 있을때, 검색은?
SELECT * FROM emp WHERE comm IS NULL;
-- NVL(Null Value) 널값을 대체하는 함수.
-- 사원중에 커미션을 0원받은 사람은?(아래)
SELECT nvl2(comm,100,0), E.* FROM emp E WHERE NVL(comm,0) = 0;
-- 오라클은 표준쿼리X, ANSI쿼리 표준입니다.
SELECT CASE WHEN comm is null THEN 0
WHEN comm = 0 THEN 100
WHEN comm > 0 THEN comm
END AS "CASE출력문"
,DECODE(comm,null,0,100),NVL2(comm,100,0), E.* FROM emp E; --WHERE NVL(comm,0) = 0;
-- 연봉을 기준으로 정렬 sort = 순서 order by 필드명 오름차순[초기값]|DESC내림차순
-- 서브쿼리?(select쿼리가 중복되어있는...) 입니다.
SELECT ROWNUM, E.* FROM (--테이블명
SELECT * FROM emp ORDER BY sal DESC--내림차순
) E WHERE ROWNUM = 1;
-- 위 정렬에서 1등만 구할 limit는 Mysql(마리아DB)의 명령어. 오라클에 없음.
-- 위 서브쿼리문장을 해석 할떄는 안쪽부터 해석
-- Mysql (마리아DB) 있는 AI(AutoIncrement)자동증가값 명령 오라클에 없음.
-- 중복레코드(row)를 제거하는 명령어 distinct
SELECT deptno as "부서번호" FROM emp;--사원수대로 부서번호가 출력
SELECT DISTINCT deptno as "부서번호" FROM emp;
--(중요!)문자열을 연결할때 concat함수외에 ||파이프라인 2개를 합쳐서 구현.
SELECT ename ||'is a'|| job AS "문자열 연결" FROM emp;
-- 여기까지 select 마무리 Read
-- 이후부터 CRUD중에 Insert, Update, Delete 3개의 쿼리로 끝
-- 함수용어 Abs(Absolute절대값), Floor(바닥함수1.5=1)<->ceil(천정함수1.5=2)
-- Round(반올림), Trunc(Truncate버리는함수), Mod(나머지 구하는 함수)
-- Upper(대문자),Lower(소문자),Length(길이구하는함수)
-- Instr(문자의위치를구하는함수), Substr(매개변수로 입력한 숫자위치만큼 문자열을 추출하는함수)
-- Lpad(Leftpadding왼쪽여백), Rpad(오른쪽여백) 레포트프로그램에서 출력위치를 조정.
-- Trim(왼쪽,오른쪽 문자열을 잘라냄)
-- 날짜 sysdata 오라클전용 함수로써 게시물입력시간, 회원등록 시간
SELECT to_char(systimestamp,'yyyy-mm-dd hh24:mi:ss:ff') FROM dual;
-- 위 to_char(날짜를 문자열로 변환)는 형변환 함수라고 합니다.
SELECT sysdate + 1 FROM dual;
SELECT sysdate - 1 FROM dual;
-- 아래는 6개월간 회원정보 수정이 없는 회원에게 메일발송같은 서비스를처리하기위한 용도.
SELECT * FROM TBL_MEMBER WHERE UPDATE_DATE < ADD_MONTHS(SYSDATE,-6);