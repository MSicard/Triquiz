package com.triquiz.database.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.triquiz.database.beans.Category;

public class CategoryControl {
	private Connection conn;
	
	public CategoryControl(Connection conn){
		this.conn = conn;
	}
	
	public ArrayList<Category> getCategory(){
		ArrayList<Category> categories = new ArrayList<>();
		String sql = "SELECT idCategory, name FROM Category";
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try{
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			while(rs.next()){
				Category category = new Category();
				category.setIdCategory(rs.getInt(1));
				category.setName(rs.getString(2));
				categories.add(category);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return categories;
	}
}
