package com.example.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.finance.BankAccount;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/customers")
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public Customer create()
	{
		return customerDao.create();
	}
	
	@RequestMapping(value="/{customerId}",method=RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getBankAccountDetails_Fallback")
	//@HystrixCommand(fallbackMethod = "getBankAccountDetails_Fallback", commandKey="test")
	public BankAccount getBankDetails(@PathVariable String customerId)
	{
		Integer bankAccountId = customerDao.getBankAccountId(Integer.valueOf(customerId));
		System.out.println("Making a call");
		BankAccount bankAccount =  restTemplate.exchange("http://localhost:9091/bankAccounts/{bankAccountId}",HttpMethod.GET, null, new ParameterizedTypeReference<BankAccount>() {},bankAccountId).getBody();
		System.out.println("Made a call");
		return bankAccount;
	}
	
	@SuppressWarnings("unused")
	private BankAccount getBankAccountDetails_Fallback(@PathVariable String customerId) {
		System.out.println("getBankAccountDetails_Fallback");
		BankAccount bankAccount = new BankAccount(999,"Current",9999L);
		return bankAccount;
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	

}
