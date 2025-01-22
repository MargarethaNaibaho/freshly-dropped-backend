package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.entity.Customer;
import com.wesclic.freshlydropped.repository.CustomerRepository;
import com.wesclic.freshlydropped.service.CustomerService;
import com.wesclic.freshlydropped.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
//    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer createNewCustomer(Customer customer) {
        try{
            return customerRepository.saveAndFlush(customer);
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
