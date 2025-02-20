package files;

import lombok.Builder;
import lombok.Data;
import org.testng.annotations.Test;


@Builder
@Data
public class trelloBoard {
    private String name;
    private String desc;
    private String prefs_background;

}
