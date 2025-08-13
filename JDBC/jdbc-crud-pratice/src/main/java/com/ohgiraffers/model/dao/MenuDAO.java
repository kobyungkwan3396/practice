package com.ohgiraffers.model.dao;

import com.ohgiraffers.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class MenuDAO {


    private Properties prop = new Properties();

    public MenuDAO() {

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectedLastId(Connection con) {

        Statement stmt = null;
        ResultSet rset = null;

        int maxId = 0;

        String query = prop.getProperty("selectedLastId");

        try {

            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            if (rset.next()) {
                maxId = rset.getInt("Max_ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
        }

        return maxId;

    }

    public List<Map<Integer, String>> selectedAllName(Connection con) {

        Statement stmt = null;
        ResultSet rset = null;

        List<Map<Integer, String>> nameList = null;

        String query = prop.getProperty("selectedALLNameList");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            nameList = new ArrayList<>();


            while (rset.next()) {
                Map<Integer, String> name = new HashMap<>();
                name.put(rset.getInt("id"), rset.getString("name"));

                nameList.add(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
        }

        return nameList;
    }

    public int insertNewMenu(MenuDTO newMenu, Connection con) {

        PreparedStatement pstmt = null;

        int result = 0;

        String query = prop.getProperty("insertMenu");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, newMenu.getId());
            pstmt.setString(2, newMenu.getName());
            pstmt.setDouble(3, newMenu.getPrice());
            pstmt.setString(4, newMenu.getCategory());
            pstmt.setInt(5, newMenu.getIs_available());
            pstmt.setTimestamp(6, newMenu.getCreated_at());
            pstmt.setTimestamp(7, newMenu.getUpdated_at());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public MenuDTO selectMenuById(int id, Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        MenuDTO menu = null;

        String query = prop.getProperty("selectMenuById");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                menu = new MenuDTO();
                menu.setId(rset.getInt("id"));
                menu.setName(rset.getString("name"));
                menu.setPrice(rset.getDouble("price"));
                menu.setCategory(rset.getString("category"));
                menu.setIs_available(rset.getInt("is_available"));
                menu.setCreated_at(rset.getTimestamp("created_at"));
                menu.setUpdated_at(rset.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }

        return menu;
    }
}





