package sample.cafeKiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafeKiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafeKiosk.spring.api.service.product.response.ProductResponse;
import sample.cafeKiosk.spring.domain.product.Product;
import sample.cafeKiosk.spring.domain.product.ProductRepository;
import sample.cafeKiosk.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;


/**
 * readOnly = true : 읽기전용
 * CRUD 에서 CUD 동작 X / Only Read
 * JPA : CUD 스냅샷 저장, 변경감지 X (성능 향상)
 * ---
 * CQRS - Command / Query
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // 동시성 이슈
    // 빈도수가 적다면 productNumber를 유니크값으로 만들어서 만약 등록 실패를 했다면 다시 시도하게 만들기
    // 빈도수가 많다면 정책을 바꿔서 처음부터 UUID로 만들기
    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        String nextProductNumber = createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
            .map(ProductResponse::of)
            .collect(Collectors.toList());
    }

    private String createNextProductNumber() {
        // productNumber
        // 001 002 003 004
        // DB에서 마지막 저장된 Product의 상품 번호를 읽어와서 +1
        // 009 -> 010
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }

        // nextProductNumber
        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }
}
