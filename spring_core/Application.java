package com.ohgiraffers.section01.autowired.subsetion01.field;

import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.ohgiraffers.section01");

        String[] beanNames = context.getBeanDefinitionNames();
        for(String beanName : beanNames) {
            System.out.println("beanName = " + beanName);
        }

        BookService bookService = context.getBean("bookServiceField", BookService.class);

        System.out.println("bookService= " + bookService);

        List<BookDTO> books = bookService.selectAllBooks();
        for(BookDTO book : books) {
            System.out.println("book = " + book);
        }

        System.out.println(bookService.searchBookBySequence(1));

    }
}
