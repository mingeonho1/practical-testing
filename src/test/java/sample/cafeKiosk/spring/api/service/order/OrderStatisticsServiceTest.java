package sample.cafeKiosk.spring.api.service.order;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import sample.cafeKiosk.spring.client.mail.MailSendClient;
import sample.cafeKiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafeKiosk.spring.domain.history.mail.MailSendHistoryRepository;
import sample.cafeKiosk.spring.domain.order.Order;
import sample.cafeKiosk.spring.domain.order.OrderRepository;
import sample.cafeKiosk.spring.domain.orderproduct.OrderProductRepository;
import sample.cafeKiosk.spring.domain.product.Product;
import sample.cafeKiosk.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static sample.cafeKiosk.spring.domain.order.OrderStatus.PAYMENT_COMPLETED;
import static sample.cafeKiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafeKiosk.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
@SpringBootTest
class OrderStatisticsServiceTest {

    @Autowired
    private OrderStatisticsService orderStatisticsService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MailSendHistoryRepository mailSendHistoryRepository;

    @MockBean
    MailSendClient mailSendClient;

    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        mailSendHistoryRepository.deleteAllInBatch();
    }

    @DisplayName("결제완료 주문들을 조회하여 매출 통계 메일을 전송한다.")
    @Test
    void sendOrderStatisticsMail() {
        // given
        LocalDateTime now = LocalDateTime.of(2023, 9, 25, 0, 0);

        List<Product> products = List.of(
            createProduct("001", 1000),
            createProduct("002", 2000)
        );
        productRepository.saveAll(products);

        List<Order> orders = List.of(
            createPaymentCompletedOrder(products, LocalDateTime.of(2023, 9, 24, 23, 59, 59)),
            createPaymentCompletedOrder(products, now),
            createPaymentCompletedOrder(products, LocalDateTime.of(2023, 9, 25, 23, 59, 59)),
            createPaymentCompletedOrder(products, LocalDateTime.of(2023, 9, 26, 0, 0))
        );
        orderRepository.saveAll(orders);

        // stubbing
        when(mailSendClient.sendMail(any(String.class), any(String.class), any(String.class), any(String.class)))
            .thenReturn(true);
        
        // when
        boolean result = orderStatisticsService.sendOrderStatisticsMail(now.toLocalDate(), "test@test.com");

        // then
        assertThat(result).isTrue();

        List<MailSendHistory> histories = mailSendHistoryRepository.findAll();
        assertThat(histories).hasSize(1)
            .extracting("content")
            .contains("총 매출 합계는 6000원입니다.");
    }

    private Order createPaymentCompletedOrder(List<Product> products, LocalDateTime registeredDateTime) {
        Order order = Order.builder()
            .orderStatus(PAYMENT_COMPLETED)
            .registeredDateTime(registeredDateTime)
            .products(products)
            .build();

        return orderRepository.save(order);
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