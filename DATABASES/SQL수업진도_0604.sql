-- DDL문(create; alter;), DCL문(commit; rollback;
-- DML문(DataManufacture Language) insert, update, delete
-- insert문: 테이블에 새로운 레코드(row)를 추가
CREATE TABLE dept02 AS SELECT * FROM dept WHERE 1=0;
--위 쿼리는 테이블을 복제하는 명령.
--위처럼 쿼리를 실행 dept테이블과 구조와 내용이 똑같은 테이블생성.
-- where 조건이 붙으면, 구조는 같으나 내용은 빈 테이블이 생성.
INSERT INTO dept02 (
--필드명
deptno,dname, loc
) VALUES(
10,'개발부서', '천안'
--바인딩값
);
COMMIT; -- 데이터베이스 쿼리로 직접입력한 결과는 반드시 커밋을 해줘야지만 저장이 됩니다.
SELECT * FROM dept02 ORDER BY deptno;
--DELETE 는 레코드1줄을 지우는 명령.
DELETE FROM dept02; --이렇게 사용하면 안됨.
DELETE FROM dept02 WHERE deptno >= 0; --모두 삭제 where반드시 포함.
