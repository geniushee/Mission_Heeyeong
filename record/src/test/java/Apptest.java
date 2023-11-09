import org.example.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Apptest {
    App app = new App();

    @Test
    @DisplayName("등록 종료 프로세스 실시")
    void t1() {
        app.setScanner(testUtil.genScanner("""
                등록
                명언이다.
                작가
                종료"""));
        app.run();
    }

    @Test
    @DisplayName("등록 및 목록 test")
    void t2() {
        app.setScanner(testUtil.genScanner("""
                등록
                하하하
                하하
                등록
                123
                1
                목록
                종료"""));
        app.run();
    }


    @Test
    @DisplayName("삭제 test")
    void t3() {
        app.setScanner(testUtil.genScanner("""
                등록
                하하하
                하하
                등록
                내용
                작가
                등록
                내용2
                작가2
                목록
                삭제?id=1
                삭제?id=1
                종료"""));
        app.run();
    }

    @Test
    @DisplayName("수정 test")
    void t4() {
        app.setScanner(testUtil.genScanner("""
                등록
                하하하
                하하
                등록
                내용
                작가
                등록
                내용2
                작가2
                목록
                수정?id=1
                수정내용1
                수정작가1
                목록
                수정?id=5
                종료"""));
        app.run();
    }

    @Test
    @DisplayName("빌드 test")
    void t5(){
        app.setScanner(testUtil.genScanner("""
                목록
                빌드
                종료""".stripIndent()));
        app.run();
    }

}
