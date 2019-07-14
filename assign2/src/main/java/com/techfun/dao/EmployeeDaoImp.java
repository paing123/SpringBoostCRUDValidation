package com.techfun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.techfun.model.Employee;


@Repository("employeeDao")
public class EmployeeDaoImp implements EmployeeDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Employee> findAll() {

		String sql = "select * from employee";
		List<Employee> employee = jdbcTemplate.query(sql, new UserMapper());
		return employee;
	}

	class UserMapper implements RowMapper<Employee> {
		public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
			Employee employee = new Employee();
			employee.setId(rs.getInt("id"));
			employee.setFirstName(rs.getString("firstName"));
			employee.setLastName(rs.getString("lastName"));
			employee.setEmail(rs.getString("email"));
			employee.setOfficeCode(rs.getString("officeCode"));
			employee.setJobTitle(rs.getString("jobTitle"));		
			return employee;
		}
	}
	
	public void save(Employee employee) {
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO employee(firstName,lastName,email,officeCode,jobTitle) " + "values(?,?,?,?,?)", new String[] { "id" });
				ps.setString(1, employee.getFirstName());
				ps.setString(2,employee.getLastName());
				ps.setString(3, employee.getEmail());
				ps.setString(4, employee.getOfficeCode());
				ps.setString(5, employee.getJobTitle());
				return ps;
			}
		});
	}
	
	public void delete(Integer id) {
		jdbcTemplate.update("delete from employee where id=?",id);
	}
	
	public void update(Employee employee) {
		jdbcTemplate.update("update employee set firstName=?, lastName=?, email=?, officeCode=?, jobTitle=? where id=?",
				employee.getFirstName(),employee.getLastName(),employee.getEmail(),employee.getOfficeCode(),employee.getJobTitle(),employee.getId());
	}
	
	public Employee findById(Integer id){
		String sql = "select * from employee where id=" + id;
		List<Employee> employees = jdbcTemplate.query(sql, new UserMapper());
		
		if (employees.isEmpty()) {
			return null;
		} else {
			return employees.get(0);//return as an object
		}
	}
	
	public List<Employee> findByName(String name){
		String sql = "select * from employee where firstName LIKE \'%"+ name+"%\' "
				+ "or lastName LIKE \'%"+ name+"%\' or email LIKE \'%"+name+"%\' or officeCode LIKE \'%"
				+name+"%\' or jobTitle LIKE \'%"+name+"%\'";
		List<Employee> employees = jdbcTemplate.query(sql, new UserMapper());
		return employees;	
	}
}
