package com.repository;

import com.model.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryImpTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CompanyRepository sut;

    @Test
    public void testFindAllCompany() {
        //
        Company company = new Company();
        company.setAddress("Hoang Quoc Viet");
        company.setEmail("asadas@gmail.com");
        company.setTelephone("1212112");
        entityManager.persist(company);
        entityManager.flush();
        List<Company> actual = sut.findAllCompany();
        actual.forEach(System.out::println);
    }
}
