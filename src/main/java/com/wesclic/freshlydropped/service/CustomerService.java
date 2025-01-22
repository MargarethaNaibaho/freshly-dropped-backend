package com.wesclic.freshlydropped.service;

import com.wesclic.freshlydropped.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer createNewCustomer(Customer customer);
}
