import org.example.utils.util;
import org.example.view.App;
import org.example.view.Massage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class Apptest {
    App app = new App();

    @Test
    @DisplayName("scanner test")
    void scT1(){
        util.setSc(testUtil.genScanner("""
                등록
                내용1
                작가1
                종료""".stripIndent()));

        ByteArrayOutputStream byteArrayOutputStream = testUtil.setOutToByteArray();
        new App().run();

        String out = byteArrayOutputStream.toString().trim();
        testUtil.clearSetOutOtByteArray(byteArrayOutputStream);
        assertThat(out)
                .contains("1"+ Massage.enrollQuote);
    }

}
