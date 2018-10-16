package com.repository.imp;

import com.model.User;
import com.repository.UserRepository;
import com.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Repository
public class UserRepositoryImp implements UserRepository {

    @Autowired
    EntityManager entityManager;

    @Autowired
    Common common;

    /**
     * Find all user with condition search
     *
     * @param companyInternalId company id
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @param sortType          sort type of user full name
     * @param limit             limit of user found
     * @param offset            index of start user in database
     * @return list user
     */
    @Override
    public List<User> findUser(int companyInternalId, String userFullName, String insuranceNumber,
                               String placeOfRegister, String sortType, int limit, int offset) {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT * FROM tbl_user u " +
                        " INNER JOIN tbl_company c " +
                        " ON u.company_internal_id = c.company_internal_id " +
                        " INNER JOIN tbl_insurance ins " +
                        " ON u.insurance_internal_id = ins.insurance_internal_id " +
                        " WHERE u.company_internal_id = :company_internal_id ");
        if (common.checkStringEmptyOrNull(userFullName) == true) {
            sql.append(" AND u.user_full_name LIKE :user_full_name ");
        }
        if (common.checkStringEmptyOrNull(insuranceNumber) == true) {
            sql.append(" AND ins.insurance_number = :insurance_number ");
        }
        if (common.checkStringEmptyOrNull(placeOfRegister) == true) {
            sql.append(" AND ins.place_of_register LIKE :place_of_register ");
        }
        sql.append(" ORDER BY u.user_full_name ");
        sql.append(sortType);
        sql.append(" LIMIT ");
        sql.append(limit);
        sql.append(" OFFSET ");
        sql.append(offset);
        Query query = entityManager.createNativeQuery(sql.toString(), User.class);
        query.setParameter("company_internal_id", companyInternalId);
        if (common.checkStringEmptyOrNull(userFullName) == true) {
            query.setParameter("user_full_name", "%" + userFullName + "%");
        }
        if (common.checkStringEmptyOrNull(insuranceNumber) == true) {
            query.setParameter("insurance_number", insuranceNumber);
        }
        if (common.checkStringEmptyOrNull(placeOfRegister) == true) {
            query.setParameter("place_of_register", "%" + placeOfRegister + "%");
        }
        return query.getResultList();
    }

    /**
     * Count total user with condition search
     *
     * @param companyInternalId company id
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @return total user
     */
    @Override
    public int countTotalUser(int companyInternalId, String userFullName, String insuranceNumber,
                              String placeOfRegister) {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT COUNT(u.user_internal_id) FROM tbl_user u " +
                        " INNER JOIN tbl_company c " +
                        " ON u.company_internal_id = c.company_internal_id " +
                        " INNER JOIN tbl_insurance ins " +
                        " ON u.insurance_internal_id = ins.insurance_internal_id " +
                        " WHERE u.company_internal_id = :company_internal_id ");
        if (common.checkStringEmptyOrNull(userFullName) == true) {
            sql.append(" AND u.user_full_name LIKE :user_full_name ");
        }
        if (common.checkStringEmptyOrNull(insuranceNumber) == true) {
            sql.append(" AND ins.insurance_number = :insurance_number ");
        }
        if (common.checkStringEmptyOrNull(placeOfRegister) == true) {
            sql.append(" AND ins.place_of_register LIKE :place_of_register ");
        }
        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("company_internal_id", companyInternalId);
        if (common.checkStringEmptyOrNull(userFullName) == true) {
            query.setParameter("user_full_name", "%" + userFullName + "%");
        }
        if (common.checkStringEmptyOrNull(insuranceNumber) == true) {
            query.setParameter("insurance_number", insuranceNumber);
        }
        if (common.checkStringEmptyOrNull(placeOfRegister) == true) {
            query.setParameter("place_of_register", "%" + placeOfRegister + "%");
        }
        return ((BigInteger) query.getSingleResult()).intValue();
    }

    /**
     * Find user with condition search to export
     *
     * @param companyInternalId company id
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @return list user
     */
    @Override
    public List<User> findUserDataToExport(int companyInternalId, String userFullName, String insuranceNumber,
                                           String placeOfRegister) {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT * FROM tbl_user u " +
                        " INNER JOIN tbl_company c " +
                        " ON u.company_internal_id = c.company_internal_id " +
                        " INNER JOIN tbl_insurance ins " +
                        " ON u.insurance_internal_id = ins.insurance_internal_id " +
                        " WHERE u.company_internal_id = :company_internal_id ");
        if (common.checkStringEmptyOrNull(userFullName) == true) {
            sql.append(" AND u.user_full_name LIKE :user_full_name ");
        }
        if (common.checkStringEmptyOrNull(insuranceNumber) == true) {
            sql.append(" AND ins.insurance_number = :insurance_number ");
        }
        if (common.checkStringEmptyOrNull(placeOfRegister) == true) {
            sql.append(" AND ins.place_of_register LIKE :place_of_register ");
        }
        Query query = entityManager.createNativeQuery(sql.toString(), User.class);
        query.setParameter("company_internal_id", companyInternalId);
        if (common.checkStringEmptyOrNull(userFullName) == true) {
            query.setParameter("user_full_name", "%" + userFullName + "%");
        }
        if (common.checkStringEmptyOrNull(insuranceNumber) == true) {
            query.setParameter("insurance_number", insuranceNumber);
        }
        if (common.checkStringEmptyOrNull(placeOfRegister) == true) {
            query.setParameter("place_of_register", "%" + placeOfRegister + "%");
        }
        return query.getResultList();
    }

    /**
     * Check user existed or not
     *
     * @param userName user name
     * @return true if user not exist and false if user existed
     */
    @Override
    public boolean checkExistUserByUserName(String userName) {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT * FROM tbl_user u WHERE u.username = :user_name");
        Query query = entityManager.createNativeQuery(sql.toString(), User.class);
        query.setParameter("user_name", userName);
        if (query.getResultList().size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Save user data into database
     *
     * @param user object user contains data
     */
    @Override
    public void saveUser(User user) {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "INSERT INTO tbl_user (company_internal_id, insurance_internal_id, username, password, user_full_name, user_sex_division, birthdate) "
                        +
                        "VALUES (:company_internal_id, :insurance_internal_id, :username, :password, :user_full_name, :user_sex_division, :birthdate)");
        Query query = entityManager.createNativeQuery(sql.toString(), User.class);
        query.setParameter("company_internal_id", user.getCompany().getCompanyInternalId());
        query.setParameter("insurance_internal_id", user.getInsurance().getInsuranceInternalId());
        query.setParameter("username", user.getUserName());
        query.setParameter("password", user.getPassword());
        query.setParameter("user_full_name", user.getUserFullName());
        query.setParameter("user_sex_division", user.getUserSexDivision());
        query.setParameter("birthdate", user.getBirthDate());
        query.executeUpdate();
    }
}
