package sample.cafeKiosk.spring.api.service.product.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafeKiosk.spring.domain.product.Product;
import sample.cafeKiosk.spring.domain.product.ProductSellingStatus;
import sample.cafeKiosk.spring.domain.product.ProductType;

@Getter
@NoArgsConstructor
public class ProductCreateServiceRequest {

    private ProductType type;
    private ProductSellingStatus sellingStatus;
    private String name;
    private int price;

    @Builder
    private ProductCreateServiceRequest(ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public Product toEntity(String nextProductNumber) {
        return Product.builder()
            .productNumber(nextProductNumber)
            .type(type)
            .sellingStatus(sellingStatus)
            .name(name)
            .price(price)
            .build();
    }
}
