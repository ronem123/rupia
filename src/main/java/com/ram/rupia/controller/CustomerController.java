package com.ram.rupia.controller;

import com.ram.rupia.dto.CustomerDTO;
import com.ram.rupia.post_request.CustomerRequestBody;
import com.ram.rupia.service.customer.CustomerService;
import com.ram.rupia.service.kafka.KafkaProducerService;
import com.ram.rupia.service.kafka.KafkaTopicUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ram Mandal on 16/11/2025
 *
 * @System: Apple M1 Pro
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:3000")//remove once the front end is hosted on a real web server
public class CustomerController {

    private final CustomerService customerService;
    private final KafkaProducerService kafkaProducerService;


    //@GetMapping("")
    //is the same thing, the annotation with endpoint is removed due to the RequestMapping annotation as /customers
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomerList() {
        kafkaProducerService.publish(KafkaTopicUtil.quickStartEvent, "Success now walle walle");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerRequestBody requestBody) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createNewCustomer(requestBody));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerRequestBody body) {
        CustomerDTO customerDTO = customerService.updateCustomer(customerId, body);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }
}
