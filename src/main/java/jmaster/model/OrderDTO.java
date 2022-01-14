package jmaster.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private List<OrderItemDTO> orderItemDTOS;
}
