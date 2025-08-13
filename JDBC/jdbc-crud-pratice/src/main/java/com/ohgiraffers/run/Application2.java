package com.ohgiraffers.run;

import java.util.Scanner;

public class Application2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MemberService memberService = new MemberService();

        while (true) {
            System.out.println("========= 🍗 음식점 관리 프로그램 🍗 =========");
            System.out.println("1. 현재 메뉴 테이블의 마지막 번호 조회");
            System.out.println("2. 고객 이름 전체 조회");
            System.out.println("3. 단건 메뉴 상세 조회");
            System.out.println("4. 기존 메뉴 수정");
            System.out.println("5. 기존 메뉴 삭제");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 선택 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1:{
                    memberService.selectedLastId();
                    break;
                }
                case 2:
                    memberService.selectedAllName();
                    break;
                case 3:
                    memberService.selectMenuById();
                    break;
                case 4:
                    memberService.changedMenu();
                    break;
                case 5:
                    memberService.deleteid(sc);
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다");
                    break;

                default:
                    System.out.println("잘못된 번호를 입력하셨습니다.");
                    break;
            }

            if(no == 9) {
                break;
            }
        }
    }
}