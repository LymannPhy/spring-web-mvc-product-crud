package co.istad.springwebmvc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class Product {

    private Integer id;
    private String uuid;
    private String name;
    private Double price;
    private Integer qty;
    private LocalDateTime importedDate;
    private Boolean status;

}
