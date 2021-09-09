package com.capstone.shruti.example;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.capstone.shruti.example.entities.Account;
import com.capstone.shruti.example.entities.Customer;
import com.capstone.shruti.example.repository.CustomerRepo;
import com.capstone.shruti.example.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.capstone.shruti.example.entities.*;
import com.capstone.shruti.example.repository.*;
import com.capstone.shruti.example.service.*;

@RunWith(MockitoJUnitRunner.class)
public class CusServiceTest {
 
@InjectMocks
CustomerService customerservice;
@Mock
CustomerRepo repo;
@Test
public void getAllCustomersTest() {
List<Customer> list=new ArrayList<Customer>();
Account a=new Account(1,15000,"saving");
Customer cust1=new Customer(1,"Shruti","Bangalore",a);
list.add(cust1);
when(repo.findAll()).thenReturn(list);
List<Customer> customerlist=customerservice.getAllCustomers();
assertEquals(1,customerlist.size());
}
@Test
public void getCustomerByIdTest() {
when(repo.findById(1).get()).thenReturn(new Customer(1,"Shruti","Bangalore",new Account(1,15000,"saving")));
Customer c=customerservice.getCustomerById(1);
assertEquals(1,c.getCustomerId());
assertEquals("Shruti",c.getName());
assertEquals("Bangalore",c.getAdd());
}
@Test
public void createCustomerTest() {
Account a=new Account(1,15000,"saving");
Customer cust1=new Customer(1,"Shruti","Bangalore",a);
customerservice.saveOrUpdate(cust1);
verify(repo,times(1)).save(cust1);
}
@Test
public void deleteCustomerTest() {
Account a=new Account(1,15000,"saving");
Customer cust1=new Customer(1,"Shruti","Bangalore",a);
customerservice.saveOrUpdate(cust1);
customerservice.delete(1);
verify(repo,times(1)).deleteById(1);
}
@Test
public void deleteCustomerByIdTest() {
Account a=new Account(1,15000,"saving");
Customer cust1=new Customer(1,"Shruti","Bangalore",a);
customerservice.saveOrUpdate(cust1);
customerservice.deleteAll();
verify(repo,times(1)).deleteAll();
}
@Test
public void FindByTest() {
List<Customer> list=new ArrayList<Customer>();
Account a=new Account(1,15000,"saving");
Customer cust1=new Customer(1,"Shruti","Bangalore",a);
list.add(cust1);
when(repo.findCustomerByName("Shruti")).thenReturn(list);
List<Customer> c=customerservice.findBy("Shruti");
Customer x=c.get(0);
assertEquals("Shruti",x.getName());
assertEquals(1,c.size());

}
@Test
public void updateCustomerTest() {
Account a=new Account(1,15000,"saving");
Customer cust1=new Customer(1,"Shruti","Bangalore",a);
customerservice.saveOrUpdate(cust1);
Customer cust2=new Customer(2,"abc","xyz",a);
customerservice.update(cust2, 1);
verify(repo,times(1)).save(cust2);
}
@Test
public void emptyTest() {
List<Customer> list=new ArrayList<Customer>();
when(repo.findAll()).thenReturn(list);
List<Customer> customerlist=customerservice.getAllCustomers();
assertEquals(0,customerlist.size());
}
@Test
public void updateCustomer2Test() {
Account a=new Account(1,15000,"saving");
Customer cust1=new Customer(1,"Shruti","Bangalore",a);
customerservice.saveOrUpdate(cust1);
Account a1=new Account(2,1000,"saving");
Customer cust2=new Customer(2,"abc","xyz",a1);
customerservice.update(cust2, 1);
verify(repo,times(1)).save(cust2);
}
}
