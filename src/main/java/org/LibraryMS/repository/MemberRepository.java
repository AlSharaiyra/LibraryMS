package org.LibraryMS.repository;

import org.LibraryMS.model.Book;
import org.LibraryMS.model.Member;
import org.LibraryMS.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    public boolean isEmailOrPhoneNumberTaken(String email, String phoneNumber) {
        String query = "SELECT 1 FROM member WHERE email = ? OR phone_number = ?";
        try (Connection conn = JDBCUtil.getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, phoneNumber);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void save(Member member) {
        String query = "INSERT INTO member (name, email, phone_number, registration_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, member.getName());
            statement.setString(2, member.getEmail());
            statement.setString(3, member.getPhoneNumber());
            statement.setTimestamp(4, member.getRegistrationDate());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        catch (SQLIntegrityConstraintViolationException e) {
//            System.out.println("Error: Email and phone number must be unique. " + e.getMessage());
//        }
    }

    public Member findById(int id) {
        String query = "SELECT * FROM member WHERE id = ?";

        try (Connection conn = JDBCUtil.getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Member(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getTimestamp("registration_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM member";
        try (Connection conn = JDBCUtil.getConnection(); Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                members.add(new Member(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getTimestamp("registration_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public void deleteById(int id) {
        String query = "DELETE FROM member WHERE id = ?";
        try (Connection conn = JDBCUtil.getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> findBorrowedBooksByMemberId(int memberId) {
        String query = "SELECT b.id, b.title, b.author, b.isbn, b.published_year, b.available_copies " +
                "FROM book b " +
                "JOIN book_borrowers bb ON bb.book_id = b.id " +
                "WHERE bb.borrower_id = ?";
        List<Book> borrowedBooks = new ArrayList<>();

        try (Connection conn = JDBCUtil.getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, memberId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getInt("published_year"),
                        resultSet.getInt("available_copies")
                );
                borrowedBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowedBooks;
    }


    public void update(Member member) {
        String query = "UPDATE member SET email = ?, phone_number = ? WHERE id = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, member.getEmail());
            statement.setString(2, member.getPhoneNumber());
            statement.setInt(3, member.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No rows updated. Member ID might be invalid.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating member", e);
        }
    }
}
