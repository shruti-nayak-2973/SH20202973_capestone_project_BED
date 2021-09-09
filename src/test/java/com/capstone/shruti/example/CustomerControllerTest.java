package com.capstone.shruti.example;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.capstone.shruti.example.entities.Account;
import com.capstone.shruti.example.entities.Customer;
import com.capstone.shruti.example.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.capstone.shruti.example.entities.*;
import com.capstone.shruti.example.service.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerControllerTest {

@Autowired
MockMvc mockmvc;
@MockBean
private CustomerService customerservice;
@Test
public void responseTest() throws Exception{
when(customerservice.getCustomerById(5)).thenReturn(new Customer(1,"Shruti","Bangalore",new Account(1,15300,"saving")));
RequestBuilder request=MockMvcRequestBuilders.get("/Requesttest/{id}",1).accept(MediaType.APPLICATION_JSON);
mockmvc.perform(request).andExpect(status().isAccepted()).andExpect(content().json("{\r\n"
+ "        \"customerId\": 1,\r\n"
+ "        \"name\": \"Shruti\",\r\n"
+ "        \"adress\": \"Bangalore\",\r\n"
+ "        \"account\": {\r\n"
+ "            \"accountId\": 1,\r\n"
+ "            \"balance\": 15300,\r\n"
+ "            \"accountType\": \"saving\"\r\n"
+ "        }\r\n"
+ "    }")).andReturn();
}
@Test
public void Testingtest() throws Exception{
RequestBuilder request=MockMvcRequestBuilders.get("/test").accept(MediaType.APPLICATION_JSON);
MvcResult mvcresult= mockmvc.perform(request).andReturn();
assertEquals("This is working fine",mvcresult.getResponse().getContentAsString());
}
@Test
public void getCustomerByIdTest() throws Exception{
when(customerservice.getCustomerById(1)).thenReturn(new Customer(1,"Shruti","Bangalore",new Account(1,15300,"saving")));
RequestBuilder request=MockMvcRequestBuilders.get("/customer/id/{id}",1).accept(MediaType.APPLICATION_JSON);
mockmvc.perform(request).andExpect(status().isOk()).andExpect(content().json("{\r\n"
+ "        \"customerid\": 1,\r\n"
+ "        \"name\": \"Shruti\",\r\n"
+ "        \"adress\": \"Bangalore\",\r\n"
+ "        \"account\": {\r\n"
+ "            \"accountid\": 1,\r\n"
+ "            \"balance\": 15300,\r\n"
+ "            \"accountType\": \"saving\"\r\n"
+ "        }\r\n"
+ "    }")).andReturn();
}
@Test
public void getallcustomer() throws Exception{
List<Customer> list=new ArrayList<Customer>();
Account a=new Account(2,2000,"saving");
Customer cust1=new Customer(1,"Shruti","Bangalore",a);
list.add(cust1);
when(customerservice.getAllCustomers()).thenReturn(list);
RequestBuilder request=MockMvcRequestBuilders.get("/customer").accept(MediaType.APPLICATION_JSON);
mockmvc.perform(request).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$").exists())
.andExpect(MockMvcResultMatchers.jsonPath("$[*].customerid").isNotEmpty());
}
@Test
public void AddCustomer() throws Exception {
   
   MvcResult mvcResult = mockmvc.perform(MockMvcRequestBuilders.post("/customer")
      .contentType(MediaType.APPLICATION_JSON_VALUE).content(json("{\r\n"
+ "        \"customerid\": 1,\r\n"
+ "        \"name\": \"Shruti\",\r\n"
+ "        \"adress\": \"Bangalore\",\r\n"
+ "        \"account\": {\r\n"
+ "            \"accountid\": 1,\r\n"
+ "            \"balance\": 15300,\r\n"
+ "            \"accountType\": \"saving\"\r\n"
+ "        }\r\n"
+ "    }"))).andReturn();
   
   int status = mvcResult.getResponse().getStatus();
   assertEquals(400, status);
   String content = mvcResult.getResponse().getContentAsString();
   assertEquals(content, "");
}
@Test
public void updateCustomer() throws Exception {
   
   MvcResult mvcResult = mockmvc.perform(MockMvcRequestBuilders.put("/customer")
      .contentType(MediaType.APPLICATION_JSON_VALUE).content(json("{\r\n"
+ "        \"customerid\": 1,\r\n"
+ "        \"name\": \"Shruti\",\r\n"
+ "        \"adress\": \"Bangalore\",\r\n"
+ "        \"account\": {\r\n"
+ "            \"accountid\": 1,\r\n"
+ "            \"balance\": 15300,\r\n"
+ "            \"accountType\": \"saving\"\r\n"
+ "        }\r\n"
+ "    }"))).andReturn();
   
   int status = mvcResult.getResponse().getStatus();
   assertEquals(400, status);
   String content = mvcResult.getResponse().getContentAsString();
   assertEquals(content, "");
}
@Test
public void deleteProduct() throws Exception {
   String uri = "/customer/2";
   MvcResult mvcResult = mockmvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
   int status = mvcResult.getResponse().getStatus();
   assertEquals(500, status);
   String content = mvcResult.getResponse().getContentAsString();
   assertEquals(content, "");
}


private byte[] json(String string) {
// TODO Auto-generated method stub
return null;
}
}