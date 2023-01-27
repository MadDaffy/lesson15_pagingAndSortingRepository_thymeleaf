package com.geekbrains.demoboot.repositories.specifications;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

import com.geekbrains.demoboot.dto.filter.ProductFilter;
import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.entities.Product_;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class ProductSpecs implements Specification<Product>{
    private final ProductFilter filter;

    @Override
    public Predicate toPredicate(Root<Product> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        return cb.and(applyFilters(root, cb).toArray(new Predicate[0]));
    }

    private List<Predicate> applyFilters(Root<Product> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        List<Predicate> titleContains = titleContains(root, cb, filter.getTitle());
        List<Predicate> priceGreater = priceGreaterThanOrEq(root, cb, filter.getPriceFrom());
        List<Predicate> priceless = priceLesserThanOrEq(root, cb, filter.getPriceTo());

        if (isNotEmpty(titleContains)) {
            predicates.addAll(titleContains);
        }
        if (isNotEmpty(priceGreater)) {
            predicates.addAll(priceGreater);
        }
        if (isNotEmpty(priceless)) {
            predicates.addAll(priceless);
        }

        return predicates;
    }

    public List<Predicate> titleContains(Root<Product> root, CriteriaBuilder cb, String word) {
                return List.of(
                        cb.like(
                                root.get(Product_.title),
                                "%" + word + "%")
                );
    }

    public List<Predicate> priceGreaterThanOrEq(Root<Product> root, CriteriaBuilder cb, Integer value) {
            return List.of(cb.greaterThanOrEqualTo(root.get(Product_.price), value));
    }

    public List<Predicate> priceLesserThanOrEq(Root<Product> root, CriteriaBuilder cb, Integer value) {
        return List.of(cb.lessThanOrEqualTo(root.get(Product_.price), value));
    }
}
