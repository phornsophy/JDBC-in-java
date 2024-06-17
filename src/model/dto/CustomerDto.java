package model.dto;
import lombok.Builder;

import java.util.Date;
@Builder
public record CustomerDto(
    Integer id,
    String name,
    String email,
    Boolean is_deleted,
    Date created_date
) {
}
