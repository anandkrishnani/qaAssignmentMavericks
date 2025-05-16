package mavericks.consulting.com.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Pet {

    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tags> tags;
    private String status;
}
