package domain.quotataion.quotation;

import org.example.standard.utils.util;
import org.example.global.App;
import org.example.global.Massage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testUtil.testUtil;

import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class Apptest {
    final App app = new App();
    private String out = "";

    private String run(final String cmd) {
        util.setSc(testUtil.genScanner(cmd.stripIndent() + "\n종료"));

        ByteArrayOutputStream byteArrayOutputStream = testUtil.setOutToByteArray();
        app.run();

        String out = byteArrayOutputStream.toString().trim();
        testUtil.clearSetOutOtByteArray(byteArrayOutputStream);
        return out;

    }

    @Test
    @DisplayName("실행여부 확인")
    public void t0() {
        out = run("""
                """);
        assertThat(out)
                .contains(Massage.startMassage);
    }

    @Test
    @DisplayName("scanner test")
    public void t1() {
        out = run("""
                등록
                내용1
                작가1""");

        assertThat(out)
                .contains("1" + Massage.enrollQuote);
    }

    @Test
    @DisplayName("목록 확인")
    public void t2() {
        out = run("""
                등록
                내용1
                작가1
                목록""");

        assertThat(out)
                .contains("1" + Massage.enrollQuote)
                .contains(Massage.listTitle);

    }

    @Test
    @DisplayName("삭제 확인")
    public void t3() {
        out = run("""
                등록
                내용1
                작가1
                등록
                내용2
                작가2
                등록
                내용3
                작가3
                삭제?id=2
                목록""");

        assertThat(out)
                .contains("1" + Massage.enrollQuote)
                .contains(Massage.listTitle)
                .contains("3 / 작가3 / 내용3")
                .doesNotContain("2 / 작가2 / 내용2")
                .contains("2" + Massage.quoteDelete);
    }

    @Test
    @DisplayName("수정 확인")
    public void t4() {
        out = run("""
                등록
                내용1
                작가1
                등록
                내용2
                작가2
                등록
                내용3
                작가3
                삭제?id=2
                수정?id=1
                내용0
                작가0
                목록""");

        assertThat(out)
                .contains("1" + Massage.enrollQuote)
                .contains(Massage.listTitle)
                .contains("3 / 작가3 / 내용3")
                .doesNotContain("2 / 작가2 / 내용2")
                .contains("2" + Massage.quoteDelete)
                .contains("1 / 작가0 / 내용0");

    }

    @Test
    @DisplayName("입력오류 확인")
    public void t5() {
        out = run("""
                등록
                내용1
                작가1
                등록
                내용2
                작가2
                등록
                내용3
                작가3
                삭제?id=2
                수정?id=1
                내용0
                작가0
                목록
                삭제?id=ejk""");

        assertThat(out)
                .contains("1" + Massage.enrollQuote)
                .contains(Massage.listTitle)
                .contains("3 / 작가3 / 내용3")
                .doesNotContain("2 / 작가2 / 내용2")
                .contains("2" + Massage.quoteDelete)
                .contains("1 / 작가0 / 내용0")
                .contains(Massage.commandError);
    }
}
