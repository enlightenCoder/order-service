package com.codedecode.order.service;

import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.dto.OrderDTOFromFE;
import com.codedecode.order.dto.UserDTO;
import com.codedecode.order.entity.Order;
import com.codedecode.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SequenceGenerator sequenceGenerator;
    @Autowired
    private WebClient webClient;

    public OrderDTO saveOrderInDB(OrderDTOFromFE orderDTOFromFE) {
        Order orderToBeSaved = new Order();
        // Generate dynamic sequence for order id
        orderToBeSaved.setOrderId(sequenceGenerator.generateNextOrderId());
        // Get Restaurant details
        orderToBeSaved.setRestaurantDetails(orderDTOFromFE.getRestaurantDTO());
        // Get Food items list
        orderToBeSaved.setFoodItemDTOList(orderDTOFromFE.getFoodItemList());
        //Get user details by making a rest call to user ms
        UserDTO userDetails = fetchUserDetailsFromUserMsByUserId(orderDTOFromFE.getUserId());
        orderToBeSaved.setUser(userDetails);
        Order savedOrder = orderRepository.save(orderToBeSaved);

        return new OrderDTO(
                savedOrder.getOrderId(),
                savedOrder.getFoodItemDTOList(),
                savedOrder.getRestaurantDetails(),
                savedOrder.getUser()
        );
    }

    private UserDTO fetchUserDetailsFromUserMsByUserId(Integer userId) {

        return webClient.get()
                .uri("/{id}",userId)
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();
    }
}
