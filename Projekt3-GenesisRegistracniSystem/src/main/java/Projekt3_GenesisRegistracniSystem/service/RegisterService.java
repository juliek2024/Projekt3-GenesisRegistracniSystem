package Projekt3_GenesisRegistracniSystem.service;

import Projekt3_GenesisRegistracniSystem.model.RegisterUserException;
import Projekt3_GenesisRegistracniSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Service

public class RegisterService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean verifyPersonId(User user) throws RegisterUserException {
        if (user.getPersonID().isEmpty()) {
            throw new RegisterUserException("User creation failed: user's data are not complete: missing name, surname or personID.");
        }
        try {
            Resource resource = new ClassPathResource("personID.txt");
            try (InputStream inputStream = resource.getInputStream();
                 Scanner scanner = new Scanner(inputStream)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");
                    if (parts.length > 0) {
                        String personIDFromFile = parts[0];
                        if (personIDFromFile.equals(user.getPersonID())) {
                           return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RegisterUserException("Error reading the 'PersonID.txt' file: " + e.getMessage());
        }
        return false;
    }

        public String createUser(@RequestBody User user) throws RegisterUserException {
            try {
                if (!verifyPersonId(user)) {
                    return "User creation failed: PersonID vas not successfully validated.";
                }
                if (user.getName().isEmpty() || user.getSurname().isEmpty()) {
                    return "User creation failed: user's data are incomplete (missing name, surname or personID).";
                }
                String countQuery = "select count(*) from genesisdb.genesisusers where personID = ?";
                Integer count = jdbcTemplate.queryForObject(countQuery, Integer.class, user.getPersonID());
                if (count != null && count > 0) {
                    return "User creation failed: provided PersonID is already used in the 'Genesis database'.";
                }
                if (user.getUuid() == null) {
                    user.setUuid(UUID.randomUUID().toString());
                }
                String insertSql = "insert into genesisdb.genesisusers (name, surname, personID, uuid) values (?, ?, ?, ?)";
                jdbcTemplate.update(insertSql, user.getName(), user.getSurname(), user.getPersonID(), user.getUuid());
                return "User " + user.getName() + " " + user.getSurname() + " was successfully created.";
            } catch (RegisterUserException e) {
                return "User creation failed: " + e.getMessage();
            } catch (DataAccessException e) {
                return "Database error: " + e.getMessage();
            } catch (Exception e) {
                return "Unexpected error occurred: " + e.getMessage();
            }
        }

    public User getUserInfo(int ID, String detail) throws RegisterUserException {
    String selectSql = "select ID, Name, Surname from genesisdb.genesisusers where ID = ? ";
    if ("true".equalsIgnoreCase(detail)) {
        selectSql = "select * from genesisdb.genesisusers where ID = ? ";
    }
    User user = null;
    try {
        user = jdbcTemplate.queryForObject(selectSql, new Object[]{ID}, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("Name"));
                user.setSurname(rs.getString("Surname"));
                if ("true".equalsIgnoreCase(detail)) {
                    user.setPersonID(rs.getString("PersonID"));
                    user.setUuid(rs.getString("Uuid"));
                }
                return user;
            }
        });
    } catch (EmptyResultDataAccessException e) {
        throw new RegisterUserException("User with ID " + ID + " was not found.");
    } catch (Exception e) {
        throw new RegisterUserException("New error occurred:" + e);
    }
    return user;
    }

    public List<User> getUsersInfo(String detail) throws Exception {
        String selectSql = "select ID, Name, Surname from genesisdb.genesisusers";
        if ("true".equalsIgnoreCase(detail)) {
            selectSql = "select * from genesisdb.genesisusers";
        }
        List<User> users;
        try {
            users = jdbcTemplate.query(selectSql, new RowMapper<User>() {
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getInt("ID"));
                    user.setName(rs.getString("Name"));
                    user.setSurname(rs.getString("Surname"));
                    if ("true".equalsIgnoreCase(detail)) {
                        user.setPersonID(rs.getString("PersonID"));
                        user.setUuid(rs.getString("Uuid"));
                    }
                    return user;
                }
            });
        } catch (Exception e) {
            throw new Exception("New error occurred:" + e);
        }
        return users;
    }

    public User amendUserInfo(@RequestBody User user) throws Exception {
        String numberQuery = "select count(*) from genesisdb.genesisusers where ID = ?";
        int count = jdbcTemplate.queryForObject(numberQuery, Integer.class, user.getId());
        if (count == 0) {
            throw new RegisterUserException("No data were updated: User with provided ID was not found.");
        }
        if (user.getName().isEmpty() || user.getSurname().isEmpty()) {
            throw new RegisterUserException( "User creation failed: Name and Surname cannot be empty.");
        }
        String updateSql = "update genesisdb.genesisusers set Name = ?, Surname = ? where ID = ? ";
        try {
            int updatedCount = jdbcTemplate.update(updateSql, user.getName(), user.getSurname(), user.getId());
            String selectAmendedSql = "select ID, Name, Surname from genesisdb.genesisusers where ID = ? ";
            User amendedUser = jdbcTemplate.queryForObject(selectAmendedSql, new Object[]{user.getId()}, new RowMapper<User>() {
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getInt("ID"));
                    user.setName(rs.getString("Name"));
                    user.setSurname(rs.getString("Surname"));
                    return user;
                }
            });
            return amendedUser;
        } catch (DataAccessException e) {
            throw new RegisterUserException("New error occurred:" + e.getMessage());
        }
    }

    public String deleteUser(@PathVariable("id") int ID) throws Exception {
        String deleteSql = "delete from genesisdb.genesisusers where ID = ?";
        try {
            int deletedCount = jdbcTemplate.update(deleteSql, ID);
            if (deletedCount == 0) {
                return "User with ID " + ID + " was not found.";
            }
            return "User with ID " + ID + " was successfully deleted.";
        } catch (DataAccessException e) {
            throw new RegisterUserException("New error occurred:" + e.getMessage());
        }
    }
}



