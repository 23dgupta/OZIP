package com.cognizant.service;

import java.util.List;

import com.cognizant.model.OrderDetails;

public interface IOrderService {
	
	Integer saveOrder(OrderDetails order);
	List<OrderDetails> getAllOrderDetails();
	
	OrderDetails getOneOrderDetails(Integer id);
	void deleteOrderDetails(Integer id);
	
	//void updateOrderDetails(Integer id,OrderDetails orderDetails);
	void updateOrderDetails(OrderDetails orderDetails);
	
	boolean isOrderDetailsExist(Integer id);

}
