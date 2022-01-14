package jmaster.model;

import lombok.Data;

@Data
public class OrderItemDTO {
    private int quantity;
    private ProductDTO productDTO;
}
