package com.epam.userentity;

import com.epam.userentity.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserentityApplicationTests extends com.epam.userentity.AbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	@org.junit.Test
	public void getUsersList() throws Exception {
		String uri = "/users";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User[] userslist = super.mapFromJson(content, User[].class);
		assertTrue(userslist.length > 0);
	}
	@org.junit.Test
	public void createUser() throws Exception {
		String uri = "/users";
		User user= new User();
		user.setUsername("krishna_raavi");
		user.setEmail("raavikrishna99@gmail.com");
		user.setName("krishnaRR");

		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "User is created successfully");
	}
	@org.junit.Test
	public void updateUser() throws Exception {
		String uri = "/users/money";
		User user= new User();
		user.setName("krishna");
		user.setEmail("krish2new@gmail.com");


		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "User is updated successfully");
	}
	@Test
	public void deleteUser() throws Exception {
		String uri = "/users/krishna12";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "User is deleted successfully");
	}
}