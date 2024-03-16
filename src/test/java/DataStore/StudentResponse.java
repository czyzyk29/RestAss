package DataStore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudentResponse {

    private String status;
    private Data data;

}
