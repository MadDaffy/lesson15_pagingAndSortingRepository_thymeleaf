package com.geekbrains.demoboot.dto;

import com.geekbrains.demoboot.entities.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

/**
 * Data transfer object to work with entity {@link Product}.
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {
    private static final long serialVersionUID = -5721335156042419745L;

    @Schema(description = "id", example = "123")
    private Long id;
    @Schema(description = "title", example = "конфеты")
    private String title;
    @Schema(description = "price", example = "11111111")
    private int price;
}
