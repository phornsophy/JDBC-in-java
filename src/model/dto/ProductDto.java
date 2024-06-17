package model.dto;

import lombok.Builder;

import java.util.Date;
@Builder
public record ProductDto(
        Integer id,
        String ProductName,
        Boolean isDeleted,
        Date importAt,
        Date expiredAt,
        String Product_description
) {
}
