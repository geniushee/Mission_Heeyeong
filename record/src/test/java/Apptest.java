import org.example.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

public class Apptest {
    App app = new App();

    @Test
    @DisplayName("t1 실시")
    void t1(){
//        ByteArrayOutputStream byteArrayOutputStream = setOutToByteArray();
//        app.run();
//        assertThat().(output.toString().trim());
//        App.setScanner(genScanner("""
//                등록
//                하하하
//                하하"""));
//        int id = 1;
//        String cmd = scanner.nextLine();
//        String content = scanner.nextLine();
//        String author = scanner.nextLine();

//        setOutToByteArray();

    }

    Scanner genScanner(String input){
        InputStream in = new ByteArrayInputStream(input.getBytes());
        return new Scanner(in);
    }

    ByteArrayOutputStream setOutToByteArray(){
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        return output;
    }

    void clearSetOutOtByteArray(ByteArrayOutputStream output){
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        try{
            output.close();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
