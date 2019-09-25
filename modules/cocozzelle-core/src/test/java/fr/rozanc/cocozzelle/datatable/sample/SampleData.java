package fr.rozanc.cocozzelle.datatable.sample;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SampleData {

    private Integer id;
    private String name;
    private Double value;
}
