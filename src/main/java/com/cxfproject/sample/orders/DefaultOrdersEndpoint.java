package com.cxfproject.sample.orders;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.cxfsample.schema.order.OrderInquiryResponseType;
import com.cxfsample.schema.order.OrderInquiryType;
import com.cxfsample.service.orders.Orders;

@WebService(
		portName = "OrdersSOAP", 
		serviceName = "Orders", 
		endpointInterface = "com.cxfsample.service.orders.Orders",
		targetNamespace = "http://www.cxfsample.com/service/Orders/")

public class DefaultOrdersEndpoint implements Orders {
	
	@Autowired
	private OrderService orderService;

	
	@Override
	public OrderInquiryResponseType processOrderPlacement(OrderInquiryType orderInquiry) {

	    /**
	     * Implementation of processing an order - it processes a message.
	     * This proxies to the service in order to complete this task.  
	     */
		OrderInquiryResponseType response = orderService.processOrder(
	            	orderInquiry.getUniqueOrderId(), 
	            	orderInquiry.getOrderQuantity(),
	            	orderInquiry.getAccountId(), 
	            	orderInquiry.getEan13());
	      return response;
	}

}
