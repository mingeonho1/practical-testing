package sample.cafeKiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafeKiosk.spring.domain.product.Product;
import sample.cafeKiosk.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafeKiosk.spring.domain.order.OrderStatus.PAYMENT_COMPLETED;
import static sample.cafeKiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafeKiosk.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * 내가 직접 구현해본 Persistence Layer 테스트
     * - 경계값 테스트를 해야하는데 어떻게 해야하지 ?
     * - 그것은 Business Layer 테스트에서 하면 될까?
     * - Persistence Layer 테스트에서는 조회만 하면되니까 이렇게 마무리 하는게 맞는 것 같다.
     * - 값을 가져오지 못한 실패한 테스트도 해야하나 ?
     * - 우선 조회테스트만 ?
     */
    @DisplayName("원하는 주문시간과 결제완료된 상태를 가진 주문을 조회한다.")
    @Test
    void findOrdersBy() {
        // given
        List<Product> products = List.of(
            createProduct("001", 1000),
            createProduct("002", 2000)
        );
        productRepository.saveAll(products);

        LocalDateTime registeredDateTime = LocalDateTime.of(2023, 9, 25, 10, 0);
        Order order = Order.builder()
            .orderStatus(PAYMENT_COMPLETED)
            .products(products)
            .registeredDateTime(registeredDateTime)
            .build();
        orderRepository.save(order);

        // when
        List<Order> orders = orderRepository.findOrdersBy(registeredDateTime, registeredDateTime.plusDays(1), PAYMENT_COMPLETED);

        // then
        assertThat(orders).hasSize(1)
            .extracting("orderStatus", "totalPrice", "registeredDateTime")
            .contains(
                tuple(PAYMENT_COMPLETED, 3000, registeredDateTime)
            );

    }

    private Product createProduct(String productName, int price) {
        return Product.builder()
            .type(HANDMADE)
            .productNumber(productName)
            .sellingStatus(SELLING)
            .name("메뉴 이름")
            .price(price)
            .build();
    }
}