package utiltest;

import domain.quotataion.quotation.Apptest;
import org.example.domain.quotation.quotation.datas.Quotation;
import org.example.domain.quotation.quotation.servie.Const;
import org.example.global.App;
import org.example.standard.utils.util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class utiltest {
    App app = new App();

    private void txtTest(List<Quotation> list){
        util.fileSave(Const.txtFilePath, list);

        assertThat(Files.exists(Paths.get(Const.txtFilePath))).isTrue();

        util.fileDelete(Const.txtFilePath);
    }

    @Test
    @DisplayName("파일 읽기 쓰기 테스트")
    void utT1(){
        List<Quotation> list = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            list.add(new Quotation(i,String.valueOf(i),String.valueOf(i)));
        }
        txtTest(list);
    }

    @Test
    @DisplayName("저장 테스트")
    void utT2(){
        String output = Apptest.run("""
                등록
                내용1
                작가1
                등록
                내용2
                작가2
                저장""");

        assertThat(output).contains("파일을 저장합니다.");
        assertThat(Files.exists(Paths.get(Const.txtFilePath + "2.txt"))).isTrue();

        util.fileDelete(Const.txtFilePath);
    }
}
