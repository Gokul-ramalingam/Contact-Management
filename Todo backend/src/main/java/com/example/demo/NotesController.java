package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class NotesController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/notes/get")
	public List<Notes> notesList(){
		List<Notes> noteItems = jdbcTemplate.query("SELECT * FROM LIST",
			(rs,rowNum)->
        new Notes(
        		rs.getInt("id1"),
        		rs.getString("datas"),
        		rs.getBoolean("isDone")
      		 ));
		return noteItems;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/notes/patch")
	public void updateList(@RequestParam("id1") int id1,@RequestParam("datas") String datas){
		jdbcTemplate.update("UPDATE LIST SET datas=? WHERE id1=?","working 9",3);
	}
	
	@RequestMapping("/notes/post")
	public void insertIntoList(@RequestParam("id1") int id1,@RequestParam("datas") String datas,@RequestParam("isDone") String isDone){
		System.out.println("here");
		jdbcTemplate.update("INSERT INTO LIST VALUES(?,?,?)",id1,datas,isDone);
	}
	
	@DeleteMapping(path="/notes/delete/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable int id){
		jdbcTemplate.update("DELETE FROM LIST WHERE id1 = ?",id);
		
		return ResponseEntity.noContent().build();
	          
	  }
}
