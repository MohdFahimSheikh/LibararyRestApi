package com.epam.bookservice;


import com.epam.bookservice.entity.Book;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookserviceApplicationTests extends AbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	@Test
	public void getBooksList() throws Exception {
		String uri = "/books";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Book[] booklist = super.mapFromJson(content, Book[].class);
		assertTrue(booklist.length > 0);
	}
	@Test
	public void createBook() throws Exception {
		String uri = "/books";
		Book book= new Book();
		book.setBookId(4);
		book.setBookName("wonders of world");
		book.setPublisher("krishna");
		book.setAuthor("chaitanyakc");

		String inputJson = super.mapToJson(book);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Book is created successfully");
	}
	@Test
	public void updateBook() throws Exception {
		String uri = "/books/6";
		Book book= new Book();
		book.setAuthor("sasank");
		book.setBookName("book of sasank");
		book.setPublisher("chaitanya Ravi");

		String inputJson = super.mapToJson(book);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Book is updated successsfully");
	}
	@Test
	public void deleteBook() throws Exception {
		String uri = "/books/12";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Book is deleted successsfully");
	}
}