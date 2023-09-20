package sample.cafeKiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafeKiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafeKiosk.spring.api.service.order.response.OrderResponse;
import sample.cafeKiosk.spring.domain.order.Order;
import sample.cafeKiosk.spring.domain.order.OrderRepository;
import sample.cafeKiosk.spring.domain.product.Product;
import sample.cafeKiosk.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
        List<String> productNumbers = request.getProductNumbers();

        // Product
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

        Order order = Order.create(products, registeredDateTime);
        Order savedOrder = orderRepository.save(order);

        // Order
        return OrderResponse.of(savedOrder);
    }
}
