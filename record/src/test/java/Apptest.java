import org.example.utils.util;
import org.example.view.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Apptest {
    App app = new App();

    @Test
    @DisplayName("scanner test")
    void scT1(){
        util.scanner();
    }

}
