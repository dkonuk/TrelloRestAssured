package files;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class trelloLabelOnBoard {
    private String idBoard;
    private String name;
    private String color;

}
