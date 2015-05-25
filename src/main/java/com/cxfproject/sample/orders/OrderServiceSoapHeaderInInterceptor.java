package com.cxfproject.sample.orders;

import javax.xml.namespace.QName;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

public class OrderServiceSoapHeaderInInterceptor extends AbstractSoapInterceptor {
	
	public OrderServiceSoapHeaderInInterceptor() {
		super(Phase.USER_PROTOCOL);
	}
	
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		
		QName qname = new QName("http://www.cxfsample.com/cxfproject/services/Orders/","apikey");
		SoapHeader header = (SoapHeader) message.getHeader(qname);
		
		if(header!=null && header.getObject()!=null && ((Element) header.getObject()).getTextContent()!=null){		
			String apikey = ((Element) header.getObject()).getTextContent();
			System.err.println("API Key = " + apikey);
		}
	}   
		
}
