package com.dorm408.controller;

import com.dorm408.entity.Book;
import com.dorm408.entity.RequestParam;
import com.dorm408.entity.Result;
import com.dorm408.entity.ShowBook;
import com.dorm408.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    BookService bookService;


    @PostMapping("/findAllBook")
    public Result findAllBook(int page){
        return bookService.findAllBook(page);
    }

    @PostMapping("/countBook")
    public Result countBook(){
        return bookService.countBook();
    }


    @PostMapping("/findBook")
    public Result findBook(@RequestBody RequestParam requestParam){
        return bookService.findBookById(requestParam.getBook_id());
    }

    @PostMapping("/delBook")
    public Result deleteBook(@RequestBody RequestParam requestParam){
        return bookService.deleteBook(requestParam.getBook_id());
    }

    @PostMapping("/countUserBook")
    public Result countUserBook(String user){
        return bookService.countUserBook(user);
    }

    @PostMapping("/borrow")
    public Result borrowBook(String id,String user){
        return bookService.borrowBook(id,user);
    }

    @PostMapping("/backBook")
    public Result backBook(String id,String user,String date){
        return bookService.backBook(id,user,date);
    }

    @PostMapping("/getUserBook")
    public Result findUserBook(String user,int page){
        return bookService.findUserBook(user,page);
    }


    @PostMapping("/updateBook")
    public Result updateBook(@RequestBody ShowBook showBook){
        return bookService.updateBook(showBook);
    }

    @PostMapping("/insertBook")
    public Result insertBook(@RequestBody Book book){
        return bookService.insertBook(book);
    }
}
