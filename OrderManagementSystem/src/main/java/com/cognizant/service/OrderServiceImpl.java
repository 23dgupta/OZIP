package com.cognizant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.exception.OrderNotFoundException;
import com.cognizant.model.OrderDetails;
import com.cognizant.repository.OrderRepository;
@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Integer saveOrder(OrderDetails order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order).getOrderId();
	}

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public OrderDetails getOneOrderDetails(Integer id) {
		// TODO Auto-generated method stub
		/* Optional<OrderDetails> opt = orderRepository.findById(id);
		 if(opt.isPresent()) {
			 OrderDetails od = opt.get();
			 return od;
		 }else {
			throw new OrderNotFoundException("Order Details "+id+"  Not Exist");
		}
		 
		 */
		return  orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("Order Details "+id+"  Not Exist"));
	}

	

	@Override
	public void deleteOrderDetails(Integer id) {
		orderRepository.delete(orderRepository.findById(id)
				.orElseThrow(()-> new OrderNotFoundException("Order Details "+id+"  Not Exist")));
		
	}

	@Override
	public void updateOrderDetails(OrderDetails orderDetails) {
		orderRepository.save(orderDetails);
		
	}

	@Override
	public boolean isOrderDetailsExist(Integer id) {
		// TODO Auto-generated method stub
		return orderRepository.existsById(id);
	}

}
