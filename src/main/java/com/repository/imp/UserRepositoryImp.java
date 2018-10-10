package com.repository.imp;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.User;
import com.repository.UserRepository;

@Repository
public class UserRepositoryImp implements UserRepository {
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	
	@Override
	public List<User> searchUser(int companyInternalId, String userFullName, String insuranceNumber,
			String placeOfRegister, String sortType, int limit, int offset) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT * FROM tbl_user u " +
						" INNER JOIN tbl_company c " +
						" ON u.company_internal_id = c.company_internal_id " +
						" INNER JOIN tbl_insurance ins " +
						" ON u.insurance_internal_id = ins.insurance_internal_id " +
						" WHERE u.company_internal_id = :company_internal_id ");
		if (userFullName.isEmpty() == false) {
			sql.append(" AND u.user_full_name LIKE :user_full_name ");
		}
		if (insuranceNumber.isEmpty() == false) {
			sql.append(" AND ins.insurance_number LIKE :insurance_number ");
		}
		if (placeOfRegister.isEmpty() == false) {
			sql.append(" AND ins.place_of_register LIKE :place_of_register ");
		}
		sql.append(" ORDER BY u.user_full_name ");
		sql.append(sortType);
		sql.append(" LIMIT ");
		sql.append(limit);
		sql.append(" OFFSET ");
		sql.append(offset);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString(), User.class);
		query.setParameter("company_internal_id", companyInternalId);
		if (userFullName.isEmpty() == false) {
			query.setParameter("user_full_name", "%" + userFullName + "%");
		}
		if (insuranceNumber.isEmpty() == false) {
			query.setParameter("insurance_number", "%" + insuranceNumber + "%");
		}
		if (placeOfRegister.isEmpty() == false) {
			query.setParameter("place_of_register", "%" + placeOfRegister + "%");
		}
		return query.getResultList();
	}
	
	@Override
	public int getTotalUser(int companyInternalId, String userFullName, String insuranceNumber,
			String placeOfRegister) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT COUNT(u.user_internal_id) FROM tbl_user u " +
						" INNER JOIN tbl_company c " +
						" ON u.company_internal_id = c.company_internal_id " +
						" INNER JOIN tbl_insurance ins " +
						" ON u.insurance_internal_id = ins.insurance_internal_id " +
						" WHERE u.company_internal_id = :company_internal_id ");
		if (userFullName.isEmpty() == false) {
			sql.append(" AND u.user_full_name LIKE :user_full_name ");
		}
		if (insuranceNumber.isEmpty() == false) {
			sql.append(" AND ins.insurance_number LIKE :insurance_number ");
		}
		if (placeOfRegister.isEmpty() == false) {
			sql.append(" AND ins.place_of_register LIKE :place_of_register ");
		}
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter("company_internal_id", companyInternalId);
		if (userFullName.isEmpty() == false) {
			query.setParameter("user_full_name", "%" + userFullName + "%");
		}
		if (insuranceNumber.isEmpty() == false) {
			query.setParameter("insurance_number", "%" + insuranceNumber + "%");
		}
		if (placeOfRegister.isEmpty() == false) {
			query.setParameter("place_of_register", "%" + placeOfRegister + "%");
		}
		return ((BigInteger) query.getSingleResult()).intValue();
	}
	
	@Override
	public List<User> getUserDataToExport(int companyInternalId, String userFullName, String insuranceNumber,
			String placeOfRegister) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT * FROM tbl_user u " +
						" INNER JOIN tbl_company c " +
						" ON u.company_internal_id = c.company_internal_id " +
						" INNER JOIN tbl_insurance ins " +
						" ON u.insurance_internal_id = ins.insurance_internal_id " +
						" WHERE u.company_internal_id = :company_internal_id ");
		if (userFullName.isEmpty() == false) {
			sql.append(" AND u.user_full_name LIKE :user_full_name ");
		}
		if (insuranceNumber.isEmpty() == false) {
			sql.append(" AND ins.insurance_number LIKE :insurance_number ");
		}
		if (placeOfRegister.isEmpty() == false) {
			sql.append(" AND ins.place_of_register LIKE :place_of_register ");
		}
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString(), User.class);
		query.setParameter("company_internal_id", companyInternalId);
		if (userFullName.isEmpty() == false) {
			query.setParameter("user_full_name", "%" + userFullName + "%");
		}
		if (insuranceNumber.isEmpty() == false) {
			query.setParameter("insurance_number", "%" + insuranceNumber + "%");
		}
		if (placeOfRegister.isEmpty() == false) {
			query.setParameter("place_of_register", "%" + placeOfRegister + "%");
		}
		return query.getResultList();
	}
}
