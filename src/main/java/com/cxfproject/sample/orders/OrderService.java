package com.cxfproject.sample.orders;

import com.cxfsample.schema.order.OrderInquiryResponseType;

public interface OrderService {

	OrderInquiryResponseType processOrder(int uniqueOrderId, int orderQuantity,
			int accountId, long ean13);
}
