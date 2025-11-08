package jeka.tsen.shopping_cart_dream_shop.service.Order;

import jeka.tsen.shopping_cart_dream_shop.dto.OrderDto;
import jeka.tsen.shopping_cart_dream_shop.model.Order;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
}
