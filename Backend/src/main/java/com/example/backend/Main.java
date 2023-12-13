package com.example.backend;

import com.example.backend.entities.*;
import com.example.backend.exceptions.InvalidPhoneNumberException;
import com.example.backend.shared.Salary;
import com.example.backend.types.ExperienceType;
import com.example.backend.types.SalaryType;
import com.example.backend.types.ScheduleType;
import com.example.backend.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        User user = new User();
        try {
            user.setPhoneNumber("+375299216888");
        } catch (InvalidPhoneNumberException e) {
            System.out.println(e.getMessage());
        }
        user.setPassword("password");

        Role role = new Role();
        role.setName("ROLE_USER");
        user.addRole(role);

        role = new Role();
        role.setName("ROLE_EMPLOYER");
        user.addRole(role);

        Profile profile = new Profile();
        profile.setUser(user);

        profile.setBirthplace("Radun");

        Vacancy vacancy1 = new Vacancy();
        vacancy1.setName("Java Developer");
        vacancy1.setDescription("Senior Java Developer Position");

        Vacancy vacancy2 = new Vacancy();
        vacancy2.setName("Front-end Developer");
        vacancy2.setDescription("UI/UX Design Experience Required");

        Vacancy vacancy3 = new Vacancy();
        vacancy3.setName("Data Scientist");
        vacancy3.setDescription("Machine Learning Expert Needed");

        vacancy1.setEmployerProfile(profile);
        vacancy2.setEmployerProfile(profile);
        vacancy3.setEmployerProfile(profile);

        Profession javaDeveloper = new Profession();
        javaDeveloper.setName("Java Developer");

        Profession frontEndDeveloper = new Profession();
        frontEndDeveloper.setName("Front-end Developer");

        Profession dataScientist = new Profession();
        dataScientist.setName("Data Scientist");

        Location location1 = new Location();
        location1.setCountry("Belarus");
        location1.setCity("Minsk");

        Location location2 = new Location();
        location2.setCountry("Belarus");
        location2.setCity("Grodno");

        Schedule fullTime = new Schedule();
        fullTime.setType(ScheduleType.FULL_TIME);

        Schedule partTime = new Schedule();
        partTime.setType(ScheduleType.PART_TIME);

        Currency currency1 = new Currency();
        currency1.setName("USD");

        Currency currency2 = new Currency();
        currency2.setName("BYN");

        Salary salary1 = new Salary();
        salary1.setType(SalaryType.FIXED);
        salary1.setMinAmount(new BigDecimal("50000.00"));
        salary1.setMaxAmount(new BigDecimal("80000.00"));
        salary1.setCurrency(currency1);

        Salary salary2 = new Salary();
        salary2.setType(SalaryType.NOT_SPECIFIED);

        Salary salary3 = new Salary();
        salary3.setType(SalaryType.FIXED);
        salary3.setMinAmount(new BigDecimal("50000.00"));
        salary3.setCurrency(currency2);

        Position javaDevPosition = new Position();
        javaDevPosition.setVacancy(vacancy1);
        javaDevPosition.setProfession(javaDeveloper);
        javaDevPosition.setExperience(ExperienceType.FROM_3_TO_6_YEARS);
        javaDevPosition.getSchedules().add(fullTime);
        javaDevPosition.setResponsibilities("Developing Java applications");
        javaDevPosition.setRequirements("Experience with Java, Spring, Hibernate");
        javaDevPosition.setLocation(location1);
        javaDevPosition.setSalary(salary1);

        Position frontEndDevPosition = new Position();
        frontEndDevPosition.setVacancy(vacancy2);
        frontEndDevPosition.setProfession(frontEndDeveloper);
        frontEndDevPosition.setExperience(ExperienceType.NO_EXPERIENCE);
        frontEndDevPosition.getSchedules().add(partTime);
        frontEndDevPosition.setResponsibilities("Designing and implementing user interfaces");
        frontEndDevPosition.setRequirements("UI/UX design experience");
        frontEndDevPosition.setLocation(location2);
        frontEndDevPosition.setSalary(salary2);

        Position dataScientistPosition = new Position();
        dataScientistPosition.setVacancy(vacancy3);
        dataScientistPosition.setProfession(dataScientist);
        dataScientistPosition.setExperience(ExperienceType.MORE_THAN_6_YEARS);
        dataScientistPosition.getSchedules().add(fullTime);
        dataScientistPosition.setResponsibilities("Machine learning and data analysis");
        dataScientistPosition.setRequirements("Expertise in machine learning algorithms");
        dataScientistPosition.setLocation(location2);
        dataScientistPosition.setSalary(salary3);

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(user);
            session.persist(profile);

            session.persist(vacancy1);
            session.persist(vacancy2);
            session.persist(vacancy3);

            session.persist(javaDeveloper);
            session.persist(frontEndDeveloper);
            session.persist(dataScientist);

            session.persist(location1);
            session.persist(location2);

            session.persist(fullTime);
            session.persist(partTime);

            session.persist(currency1);
            session.persist(currency2);

            session.persist(javaDevPosition);
            session.persist(frontEndDevPosition);
            session.persist(dataScientistPosition);

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
