package com.kstrata.apps.hrm.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kstrata.apps.hrm.dao.EmpDAO;
import com.kstrata.apps.hrm.model.Employee;

@Repository
public class EmpDAOImpl extends BaseDAOImpl implements EmpDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	@Override
	public void saveEmployee(final Employee employee) {
		/*List<Map<String, Object>>  keyList = jdbcTemplate.queryForList("select * from ENCRYPTION_KEY");
		byte[] encKey = null;
		for (Map<String, Object> map : keyList) {
			for (String key : map.keySet()) {
				if ("ENC_KEY".equalsIgnoreCase(key)) {
					encKey = (byte[]) map.get(key);
					System.out.println(map.get(key)+" -- "+encKey);
				}
			}
		}
		EncryptAndDecrypt.prepareKey(encKey);
		System.out.println("-------------------------");
		List<Map<String, Object>>  list = jdbcTemplate.queryForList("select * from employee");
		for (Map<String, Object> map : list) {
			for (String key : map.keySet()) {
				if ("EMP_USERNAME".equalsIgnoreCase(key)) {
					String userName = (String) map.get(key);
					Query query =  getEntityManager().createQuery("select emp from Employee emp where emp.empUsername=:userName");
					query.setParameter("userName", userName);
					Employee emp = (Employee) query.getSingleResult();
					try {
						String passwrd = EncryptAndDecrypt.decrypt((byte[]) map.get("EMP_PASSWORD"));
						emp.setEmpPassword(passwordEncoder.encodePassword(passwrd, userName).getBytes());
						getEntityManager().merge(emp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}*/
		//getEntityManager().merge(employee);
		
		String[] usernames = new String[] {"sathyak","komalig"};
		for (String usr : usernames) {
			Query query =  getEntityManager().createQuery("select emp from Employee emp where emp.empUsername=:userName");
			query.setParameter("userName", usr);
			Employee emp = (Employee) query.getSingleResult();
			emp.setEmpPassword(passwordEncoder.encodePassword("kstrata123", usr).getBytes());
			getEntityManager().merge(emp);
		}
		
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
