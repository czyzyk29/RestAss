package DataStore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@lombok.Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class Data {

    private int id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String date_of_birth;

}
