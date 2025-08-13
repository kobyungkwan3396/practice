//package com.ohgiraffers.run;
//
//import com.ohgiraffers.model.dao.MenuDAO;
//import com.ohgiraffers.model.dto.MenuDTO;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Scanner;
//
//import static com.ohgiraffers.common.JDBCTemplate.close;
//import static com.ohgiraffers.common.JDBCTemplate.getConnection;
//
//public class Application {
//
//    public static void main(String[] args) {
//
//        Connection con = getConnection();
//        MenuDAO registDAO = new MenuDAO();
//
//        /* 1. 아이디의 마지막 번호 조회 */
//
//        int maxId = registDAO.selectedLastId(con);
//
//        System.out.println("maxId = " + maxId);
//
//        /* 2. 이름 조회 */
//
//        List<Map<Integer, String>> nameList = registDAO.selectedAllName(con);
//
//        System.out.println("============ 😊 회원 이름 전체조회 😊 =============");
//        for(Map<Integer,String> name : nameList) {
//            System.out.println(name);
//        }
//        System.out.println("===============================================");
//
//        /* 3. 단건 메뉴 상세 조회 */
//
//        MenuDTO menu = registDAO.selectMenuById(1, con);
//        System.out.println("단건 메뉴 조회 결과: " + menu);
//
//        /* 4. 기존 데이터 수정 */
//
//        Scanner sc = new Scanner(System.in);
//        System.out.print("변경할 id 번호를 입력하세요 : ");
//        int id = sc.nextInt();
//        System.out.print("변경할 메뉴 이름을 입력하세요 : ");
//        sc.nextLine();
//        String name = sc.nextLine();
//        System.out.print("변경할 카테고리를 입력하세요 : ");
//        String category = sc.nextLine();
//
//        MenuDTO changedMenu = new MenuDTO();
//        changedMenu.setId(id);
//        changedMenu.setName(name);
//        changedMenu.setCategory(category);
//
//        PreparedStatement pstmt = null;
//
//        int result = 0;
//
//        Properties prop = new Properties();
//
//        try {
//            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
//            String query = prop.getProperty("updateMenu");
//
//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1,changedMenu.getName());
//            pstmt.setString(2,changedMenu.getCategory());
//            pstmt.setInt(3,changedMenu.getId());
//
//            result = pstmt.executeUpdate();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            close(pstmt);
//            close(con);
//        }
//
//        if(result > 0) {
//            System.out.println("메뉴 수정 성공!");
//        } else {
//            System.out.println("메뉴 수정 실패!");
//        }
//
//        /* 5. 기존데이터 삭제 */
//
//        pstmt = null;
//
//        result= 0;
//
//        try {
//            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
//            String query = prop.getProperty("deleteMenu");
//
//            System.out.print("삭제할 id 번호를 입력하세요 : ");
//             id = sc.nextInt();
//
//             pstmt = con.prepareStatement(query);
//             pstmt.setInt(1,id);
//
//             result = pstmt.executeUpdate();
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            close(pstmt);
//            close(con);
//        }
//
//        if(result > 0) {
//            System.out.println("메뉴 삭제 성공!");
//        } else {
//            System.out.println("메뉴 삭제 실패!");
//        }
//    }
//}
