package files;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class trelloListOnBoard {
    private String idBoard;
    private String name;
    private String pos;
}
