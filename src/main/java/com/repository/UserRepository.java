package com.repository;

import java.util.List;

import com.model.User;

public interface UserRepository {

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
    List<User> findUser(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister,
                        String sortType, int limit, int offset);

    /**
     * Count total user with condition search
     *
     * @param companyInternalId company id
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @return total user
     */
    int countTotalUser(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister);

    /**
     * Find user with condition search to export
     *
     * @param companyInternalId company id
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @return list user
     */
    List<User> findUserDataToExport(int companyInternalId, String userFullName, String insuranceNumber,
                                    String placeOfRegister);

    /**
     * Check user existed or not
     *
     * @param userName user name
     * @return true if user not exist and false if user existed
     */
    boolean checkExistUserByUserName(String userName);

    /**
     * Save user data into database
     *
     * @param user object user contains data
     */
    void saveUser(User user);
}
