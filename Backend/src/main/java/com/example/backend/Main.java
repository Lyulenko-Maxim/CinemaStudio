package com.example.backend;

import com.example.backend.entities.*;
import com.example.backend.entities.Currency;
import com.example.backend.exceptions.InvalidPhoneNumberException;
import com.example.backend.shared.Salary;
import com.example.backend.types.ExperienceType;
import com.example.backend.types.GenderType;
import com.example.backend.types.SalaryType;
import com.example.backend.types.ScheduleType;
import com.example.backend.utils.HibernateUtil;
import com.github.javafaker.Faker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.math.BigDecimal;
import java.util.*;


public class Main {
    private static <T> Set<T> getRandomSubset(List<T> originalList, int subsetSize) {
        if (originalList == null || originalList.isEmpty() || subsetSize <= 0) {
            return new HashSet<>();
        }

        if (subsetSize >= originalList.size()) {
            return new HashSet<>(originalList);
        }

        Set<T> randomSubset = new HashSet<>();
        List<T> remainingValues = new ArrayList<>(originalList);

        Random random = new Random();
        while (randomSubset.size() < subsetSize) {
            int randomIndex = random.nextInt(remainingValues.size());
            T randomValue = remainingValues.get(randomIndex);
            randomSubset.add(randomValue);
            remainingValues.remove(randomValue);
        }

        return randomSubset;
    }

    private static String getRandomImage(int width, int height) {
        String originalUrl = String.format("https://picsum.photos/%s/%s", width, height);
        try {
            URL url = new URL(originalUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setInstanceFollowRedirects(true);
            String imageURL = httpURLConnection.getURL().toString();
            httpURLConnection.disconnect();
            return imageURL;
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Faker faker = new Faker(new Locale("ru"));
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Role userRole = new Role("USER");
            Set<Role> candidateRoles = new HashSet<>();
            candidateRoles.add(userRole);
            candidateRoles.add(new Role("CANDIDATE"));

            Set<Role> employerRoles = new HashSet<>();
            employerRoles.add(userRole);
            employerRoles.add(new Role("EMPLOYER"));

            List<User> candidateUsers = new ArrayList<>();
            List<User> employerUsers = new ArrayList<>();
            try {
                candidateUsers.add(new User("+375291111111", "candidatePassword1", candidateRoles));
                candidateUsers.add(new User("+375292222222", "candidatePassword2", candidateRoles));
                candidateUsers.add(new User("+375293333333", "candidatePassword3", candidateRoles));
                candidateUsers.add(new User("+375294444444", "candidatePassword4", candidateRoles));
                candidateUsers.add(new User("+375295555555", "candidatePassword5", candidateRoles));
                candidateUsers.add(new User("+375296666666", "candidatePassword6", candidateRoles));
                candidateUsers.add(new User("+375291234567", "candidatePassword7", candidateRoles));
                candidateUsers.add(new User("+375292345678", "candidatePassword8", candidateRoles));
                candidateUsers.add(new User("+375293456789", "candidatePassword9", candidateRoles));
                candidateUsers.add(new User("+375294567890", "candidatePassword10", candidateRoles));

                employerUsers.add(new User("+375297777777", "employerPassword1", employerRoles));
                employerUsers.add(new User("+375298888888", "employerPassword2", employerRoles));
                employerUsers.add(new User("+375299999999", "employerPassword3", employerRoles));

            } catch (InvalidPhoneNumberException e) {
                System.out.println(e.getMessage());
            }

            for (User user : candidateUsers) {
                session.persist(user);
            }

            for (User user : employerUsers) {
                session.persist(user);
            }

            List<Profession> professions = new ArrayList<>();
            professions.add(new Profession("Режиссер"));
            professions.add(new Profession("Сценарист"));
            professions.add(new Profession("Оператор"));
            professions.add(new Profession("Актер"));
            professions.add(new Profession("Костюмер"));
            professions.add(new Profession("Монтажер"));
            professions.add(new Profession("Дизайнер по звуку"));
            professions.add(new Profession("Гримёр"));
            professions.add(new Profession("Продюсер"));
            professions.add(new Profession("Осветитель"));

            for (Profession profession : professions) {
                session.persist(profession);
            }

            List<Location> locations = new ArrayList<>();
            locations.add(new Location("Беларусь", "Минск"));
            locations.add(new Location("Беларусь", "Брест"));
            locations.add(new Location("Беларусь", "Витебск"));
            locations.add(new Location("Беларусь", "Гродно"));
            locations.add(new Location("Беларусь", "Гомель"));
            locations.add(new Location("Беларусь", "Могилев"));

            for (Location location : locations) {
                session.persist(location);
            }

            List<Schedule> schedules = new ArrayList<>();
            schedules.add(new Schedule(ScheduleType.FULL_TIME));
            schedules.add(new Schedule(ScheduleType.PART_TIME));
            schedules.add(new Schedule(ScheduleType.SHIFT));

            List<Genre> genres = new ArrayList<>();
            genres.add(new Genre("Боевик"));
            genres.add(new Genre("Комедия"));
            genres.add(new Genre("Драма"));
            genres.add(new Genre("Триллер"));
            genres.add(new Genre("Ужасы"));
            genres.add(new Genre("Романтика"));
            genres.add(new Genre("Научная фантастика"));
            genres.add(new Genre("Фэнтези"));
            genres.add(new Genre("Детектив"));
            genres.add(new Genre("Анимация"));

            Currency byn = new Currency("BYN");
            Currency rub = new Currency("RUB");
            Currency eur = new Currency("EUR");
            Currency usd = new Currency("USD");

            //ДЛЯ ПОЗИЦИЙ ВАКАНСИЙ
            List<Salary> positionSalaries = new ArrayList<>();
            positionSalaries.add(new Salary(SalaryType.NEGOTIABLE, null, null, null));
            positionSalaries.add(new Salary(SalaryType.NEGOTIABLE, null, null, null));
            positionSalaries.add(new Salary(SalaryType.NEGOTIABLE, null, null, null));
            positionSalaries.add(new Salary(SalaryType.NOT_SPECIFIED, null, null, null));
            positionSalaries.add(new Salary(SalaryType.NOT_SPECIFIED, null, null, null));
            positionSalaries.add(new Salary(SalaryType.FIXED, new BigDecimal("1800"), new BigDecimal("3000"), byn));
            positionSalaries.add(new Salary(SalaryType.FIXED, new BigDecimal("35000"), null, rub));
            positionSalaries.add(new Salary(SalaryType.FIXED, new BigDecimal("2000"), new BigDecimal("3000"), byn));
            positionSalaries.add(new Salary(SalaryType.FIXED, new BigDecimal("600"), new BigDecimal("700"), usd));
            positionSalaries.add(new Salary(SalaryType.FIXED, new BigDecimal("550"), null, usd));
            positionSalaries.add(new Salary(SalaryType.NEGOTIABLE, null, null, null));
            positionSalaries.add(new Salary(SalaryType.NEGOTIABLE, null, null, null));
            positionSalaries.add(new Salary(SalaryType.NEGOTIABLE, null, null, null));

            //ДЛЯ ПРОФИЛЯ
            List<Salary> profileSalaries = new ArrayList<>();
            profileSalaries.add(new Salary(SalaryType.FIXED, new BigDecimal("400"), null, usd));
            profileSalaries.add(new Salary(SalaryType.FIXED, new BigDecimal("550"), null, usd));
            profileSalaries.add(new Salary(SalaryType.FIXED, new BigDecimal("600"), null, eur));
            profileSalaries.add(new Salary(SalaryType.HOURLY, new BigDecimal("20"), null, eur));
            profileSalaries.add(new Salary(SalaryType.HOURLY, new BigDecimal("25"), null, usd));
            profileSalaries.add(new Salary(SalaryType.HOURLY, new BigDecimal("22"), null, usd));
            profileSalaries.add(new Salary(SalaryType.HOURLY, new BigDecimal("15"), null, usd));
            profileSalaries.add(new Salary(SalaryType.HOURLY, new BigDecimal("30"), null, byn));
            profileSalaries.add(new Salary(SalaryType.HOURLY, new BigDecimal("35"), null, byn));
            profileSalaries.add(new Salary(SalaryType.NOT_SPECIFIED, null, null, null));
            profileSalaries.add(new Salary(SalaryType.NOT_SPECIFIED, null, null, null));
            profileSalaries.add(new Salary(SalaryType.NEGOTIABLE, null, null, null));
            profileSalaries.add(new Salary(SalaryType.NOT_SPECIFIED, null, null, null));


            List<Profile> candidateProfiles = new ArrayList<>();
            Random random = new Random();

            for (User user : candidateUsers) {
                Profile candidateProfile = new Profile(
                        user,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        GenderType.values()[random.nextInt(GenderType.values().length)],
                        random.nextInt(60 - 18 + 1) + 18,
                        getRandomSubset(professions, 2),
                        getRandomSubset(genres, 3),
                        getRandomImage(400, 400),
                        locations.get(random.nextInt(6)),
                        ExperienceType.values()[random.nextInt(ExperienceType.values().length)],
                        "Высшее",
                        faker.university().name(),
                        faker.lorem().paragraph(),
                        random.nextBoolean(),
                        random.nextFloat() * 5.0f,
                        profileSalaries.get(random.nextInt(profileSalaries.size()))
                );
                candidateProfiles.add(candidateProfile);
            }

            for (Profile profile : candidateProfiles) {
                session.persist(profile);
            }

            List<Profile> employerProfiles = new ArrayList<>();
            for (User user : employerUsers) {
                Profile candidateProfile = new Profile(
                        user,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        GenderType.values()[random.nextInt(GenderType.values().length)],
                        random.nextInt(60 - 18 + 1) + 18,
                        getRandomSubset(professions, 2),
                        getRandomSubset(genres, 3),
                        getRandomImage(400, 400),
                        locations.get(random.nextInt(6)),
                        "Высшее",
                        faker.university().name(),
                        faker.lorem().paragraph()
                );
                employerProfiles.add(candidateProfile);
            }

            for (Profile profile : employerProfiles) {
                session.persist(profile);
            }

            List<Vacancy> vacancies = new ArrayList<>();
            List<Position> positions = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                Profile employerProfile = employerProfiles.get(random.nextInt(employerProfiles.size()));

                Vacancy vacancy = new Vacancy(
                        faker.funnyName().name(),
                        faker.lorem().paragraph(),
                        employerProfile,
                        getRandomImage(400, 400)
                );

                vacancies.add(vacancy);

                for (int j = 0; j < 3; j++) {
                    Position position = new Position(
                            vacancy,
                            professions.get(random.nextInt(professions.size())),
                            ExperienceType.values()[random.nextInt(ExperienceType.values().length)],
                            getRandomSubset(schedules, 2),
                            faker.lorem().sentence(),
                            faker.job().keySkills(),
                            locations.get(random.nextInt(locations.size())),
                            positionSalaries.get(random.nextInt(positionSalaries.size()))
                    );
                    positions.add(position);
                }
            }

            for (Vacancy vacancy : vacancies) {
                session.persist(vacancy);
            }

            for (Position position : positions) {
                session.persist(position);
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
