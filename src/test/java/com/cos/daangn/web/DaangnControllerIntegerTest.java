package com.cos.daangn.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.cos.daangn.domain.post.Post;
import com.cos.daangn.domain.post.PostRepository;
import com.cos.daangn.domain.user.User;
import com.cos.daangn.domain.user.UserRepository;

/*
* //통합 테스트 (모든 Bean들을 똑같이 IOC 올리고 테스트 하는 것)
*@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
*/

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class DaangnControllerIntegerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private Timestamp creatDate;
	
	@Test
	public void finById_테스트() throws Exception{

		Long id = 2L;
		
//		User user = new User((int) 1L, "테스트","1234");
//		userRepository.save(user);
		List<User> users = new ArrayList<>();
		users.add(new User((int) 1L,"테스트1","1234"));
		users.add(new User((int) 1L,"테스트1","1234"));
		userRepository.saveAll(users);
		
		List<Post> posts = new ArrayList<>();
		posts.add(new Post((int) 1L,"의자팝니다","의자팔아요~~",30000,"test1.jpg",1,10,users.get(1),creatDate));
		posts.add(new Post((int) 2L,"당근팝니다","당근팔아요~~",15000,"test2.jpg",5,30,users.get(1),creatDate));
		postRepository.saveAll(posts);
		
		
		ResultActions resultActions = mockMvc.perform(get("/post/{id}",id)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.title").value("당근팝니다"))
		.andDo(MockMvcResultHandlers.print());

	}
}
