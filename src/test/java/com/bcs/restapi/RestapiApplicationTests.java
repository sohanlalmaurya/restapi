package com.bcs.restapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bcs.restapi.model.BooksModel;
import com.bcs.restapi.service.BooksService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestapiApplicationTests {

	@Autowired
	private BooksService booksService;
	
	@Test
	public void contextLoads() {
		booksService.save(new BooksModel(1l, "Math", "RD Sharma", 355.00, 2003, "/temp/1234/math.pdf"));
		booksService.save(new BooksModel(2l, "Physics", "Kumar Mittal", 365.00, 2003, "/temp/1234/physics.pdf"));
		booksService.save(new BooksModel(3l, "English", "RN Gupta", 255.00, 2003, "/temp/1234/english.pdf"));
		booksService.save(new BooksModel(4l, "Hindi", "SB Goel", 350.00, 2014, "/temp/1234/hindi.pdf"));
		booksService.save(new BooksModel(5l, "Chemistry", "AK Rastogi", 400.00, 2014, "/temp/1234/chemistry.pdf"));
	}

}
