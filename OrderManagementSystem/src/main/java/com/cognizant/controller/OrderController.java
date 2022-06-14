package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.OrderDetails;
import com.cognizant.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private IOrderService iOrderService;
	//http://localhost:9090/order/saveOrder
	@PostMapping("/saveOrder")
	public ResponseEntity<String> saveOrder(@RequestBody OrderDetails orderDetails){
		ResponseEntity<String> resp=null;
		try {
			
			Integer id = iOrderService.saveOrder(orderDetails);
			resp=new ResponseEntity<String>(new StringBuffer()
					.append("order created with id ")
					.append(id)
					//.append(" saved")
					.toString(),HttpStatus.CREATED);
					
					
			
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to process save order",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	
	}
	//http://localhost:9090/order/getAllOrder
	@GetMapping("/getAllOrder")
	public ResponseEntity<List<OrderDetails>> getAllOrders(){
		
		 ResponseEntity<List<OrderDetails>> resp=null;
		 try {
			 List<OrderDetails> list = iOrderService.getAllOrderDetails();
			 resp=new ResponseEntity<List<OrderDetails>>(list,HttpStatus.OK);
			
		} catch (Exception e) {
			 resp=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			 e.printStackTrace();
		}
		 return resp;
		
	}
	
	//http://localhost:9090/order/getOneOrder/2
	
	@GetMapping("/getOneOrder/{id}")
	public ResponseEntity<?> getOneOrderDetails(@PathVariable Integer id){
		 ResponseEntity<?> resp=null;
		 
		 try {
			 OrderDetails od = iOrderService.getOneOrderDetails(id);
			 resp=new ResponseEntity<OrderDetails>(od,HttpStatus.OK);
			
		}
		 
		 catch (Exception e) {
			 resp=new ResponseEntity<String>("Unable to fetch order with "+id,HttpStatus.NOT_FOUND);
			 e.printStackTrace();
			}
		 return resp;
	}
	
	//http://localhost:9090/order/deleteorder/2
	@DeleteMapping("/deleteorder/{id}")
	public ResponseEntity<String> deleteOrderDetails(@PathVariable  Integer id) {
		ResponseEntity<String> resp=null;
		try {
			iOrderService.deleteOrderDetails(id);
			resp=ResponseEntity.ok("Order Deleted with " +id);
		} catch (Exception e) {
			 resp=new ResponseEntity<String>(" Order not present with "+id,HttpStatus.NOT_FOUND);
			 e.printStackTrace();
		}
		return resp;
		
	}
	
	//http://localhost:9090/order/updateOrder/2
	@PutMapping("/updateOrder/{id}")
	public ResponseEntity<String> updateOrderDetails(@PathVariable Integer id,@RequestBody OrderDetails orderDetails){
		ResponseEntity<String> resp=null;
		
		if(iOrderService.isOrderDetailsExist(id)) {
			orderDetails.setOrderId(id);
			iOrderService.updateOrderDetails(orderDetails);
			
			return ResponseEntity.ok(" Order Updated with id "+id);
			
		}
		
		else {
			
			
			 resp=new ResponseEntity<String>(" Order record not exist with "+id,HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	

}
