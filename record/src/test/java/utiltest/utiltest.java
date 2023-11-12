package utiltest;

import org.example.domain.quotation.quotation.datas.Quotation;
import org.example.domain.quotation.quotation.servie.Const;
import org.example.standard.utils.util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class utiltest {
    @Test
    @DisplayName("파일 읽기 쓰기 테스트")
    void utT1(){
        List<Quotation> list = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            list.add(new Quotation(i,String.valueOf(i),String.valueOf(i)));
        }
        util.fileSave(Const.txtFilePath,list);

        assertThat(Files.exists(Paths.get(Const.txtFilePath))).isTrue();

        util.fileDelete(Const.txtFilePath);
    }
}
