package com.app.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.Book;

//Integer : datatype of primary key
//jpaRepositary will generate all the crud methods for book class
public interface IBookDao extends JpaRepository<Book,Integer>{
	
	//search by book title
	Optional<Book> findByTitle(String title);
	//search by primary key
	//Optional<Book> findById(Integer id);
	

}
