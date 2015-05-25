package com.cxfproject.sample.orders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import com.cxfsample.schema.order.AccountType;
import com.cxfsample.schema.order.BookType;
import com.cxfsample.schema.order.ObjectFactory;
import com.cxfsample.schema.order.OrderInquiryResponseType;
import com.cxfsample.schema.order.OrderItemType;
import com.cxfsample.schema.order.OrderStatusType;
import com.cxfsample.schema.order.OrderType;


@Service
public class DefaultOrderService implements OrderService {
	
	@Override
	   public OrderInquiryResponseType processOrder(int uniqueOrderId,
	         int orderQuantity, int accountId, long ean13) {
		
		/**
		 * The ObjectFactory was generated as part of the Maven code-gen plug-in.
		 * It wraps the creation of schema objects that were also generated. This
		 * factory should be used for all schema object creation.
		 */
		ObjectFactory factory = new ObjectFactory();
		
		OrderInquiryResponseType response = factory.createOrderInquiryResponseType();
		
		AccountType account = factory.createAccountType();
		account.setAccountId(accountId);
	    response.setAccount(account);
	    
	    OrderItemType orderItem = factory.createOrderItemType();
	    BookType book = factory.createBookType();
	    book.setEan13(ean13);
	    book.setTitle("A CXF Book");
	    orderItem.setBook(book);
	    try {
	    	GregorianCalendar cal = new GregorianCalendar();
	    	cal.setTime(new Date(System.currentTimeMillis()));
	         XMLGregorianCalendar estimatedShippingDate = DatatypeFactory
	               .newInstance().newXMLGregorianCalendar(cal);
	         orderItem.setExpectedShippingDate(estimatedShippingDate);
	    } catch (Exception e) {
	         // TODO: Handle this exception.
	    }
	    
	    orderItem.setLineNumber(Integer.valueOf(1));
	    orderItem.setPrice(new BigDecimal(5));
	    orderItem.setQuantityShipped(orderQuantity);
	    
	    // 4. Create the order and populate it.
	    OrderType order = factory.createOrderType();
	    order.setOrderStatus(OrderStatusType.READY);
	    order.getOrderItems().add(orderItem);
	    response.setOrder(order);

	    return response;
	}
}
