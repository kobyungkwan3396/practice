package com.ohgiraffers.section01.xmlconfig;

import java.util.List;

public class PrintResult {
    public void printMenuList(List<MenuDTO> menuList) {

        for(MenuDTO menu : menuList){
            System.out.println(menu);

        }
    }

    public void printErrorMessage(String errorCode) {

        String errorMessage = "";
        switch (errorCode) {
            case "selectLList" : errorMessage = "메뉴 목록 조회를 실패하였습니다."; break;
        }
        System.out.println(errorMessage);
    }
}
