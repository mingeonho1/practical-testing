package sample.cafeKiosk.spring.api.service.product.response;

import lombok.Builder;
import lombok.Getter;
import sample.cafeKiosk.spring.domain.product.Product;
import sample.cafeKiosk.spring.domain.product.ProductSellingStatus;
import sample.cafeKiosk.spring.domain.product.ProductType;

@Getter
public class ProductResponse {

    private Long id;
    private String productNumber;
    private ProductType type;
    private ProductSellingStatus sellingStatus;
    private String name;
    private int price;

    @Builder
    private ProductResponse(Long id, String productNumber, ProductType type, ProductSellingStatus sellingType, String name, int price) {
        this.id = id;
        this.productNumber = productNumber;
        this.type = type;
        this.sellingStatus = sellingType;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productNumber(product.getProductNumber())
                .type(product.getType())
                .sellingType(product.getSellingStatus())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}