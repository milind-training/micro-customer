package com.example.finance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CustomerDao {
	
	private Map<Integer, Customer> customers = new HashMap<>();
	private CustomerIdGenerator customerIdGenerator = new CustomerIdGenerator();
	
	public Customer create()
	{
		Integer customerId = customerIdGenerator.getNextCustomerId();
		Customer customer = new Customer(customerId);
		customer.setBankAccountNo(100*customerId);
		customers.put(customerId, customer);
		return customer;
	}
	
	public Integer getBankAccountId(Integer customerId)
	{
		return customers.get(customerId).getBankAccountNo();
	}
	
	class CustomerIdGenerator {
		private Integer customerId = 1;
		
		Integer getNextCustomerId()
		{
			return customerId++;
		}
	}

}
