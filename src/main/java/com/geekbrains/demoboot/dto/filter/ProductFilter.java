package com.geekbrains.demoboot.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * ProductFilter
 *
 * @author efanov@reksoft.ru
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilter implements Serializable {
    private static final long serialVersionUID = 8733292591490300584L;
    private String title;
    private Integer priceFrom;
    private Integer priceTo;
}
