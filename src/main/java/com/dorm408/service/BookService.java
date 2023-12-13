package com.dorm408.service;

import com.dorm408.dao.BookDaoImpl;
import com.dorm408.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDaoImpl bookDao;

    public Result findAllBook(int page){
        Result result = new Result();
        List<ShowBook> books = bookDao.findAll((page-1)*10);
        result.setObject(books);
        result.setStatus("success");
        return result;
    }

    public Result countBook(){
        Result result = new Result();
        result.setObject(bookDao.countAll());
        result.setStatus("success");
        return result;
    }

    public Result findBookById(String id){
        Result result = new Result();
        if(bookDao.existBook(id) == 1){
            result.setObject(bookDao.findBookById(id));
            result.setStatus("success");
            return result;
        }
        result.setStatus("error");
        return result;
    }


    public Result findBook(String name,int page){
        Result result = new Result();
        ShowBook book = bookDao.findBook(name,page);
        result.setObject(book);
        if(book != null){
            result.setStatus("success");
            return result;
        }
        result.setStatus("error-exist");
        return result;
    }

    public Result getBook_type(){
        Result result = new Result();
        result.setObject(bookDao.findAllBookType());
        return result;
    }

    public Result insertBook(Book book){
        Result result = new Result();
        int exist = bookDao.existBook(book.getBook_id());
        if(exist == 0){
            String date = book.getBook_date();
            book.setBook_date(date.substring(0,10));
            bookDao.insertBook(book);
            result.setStatus("success");
            return result;
        }
        result.setStatus("书已经存在");
        return result;
    }
    public Result updateBook(ShowBook showBook){
        Result result = new Result();
        if(bookDao.existBook(showBook.getBook_id()) == 1){
            Book book = new Book();
            book.setBook_id(showBook.getBook_id());
            book.setBook_name(showBook.getBook_name());
            book.setBook_author(showBook.getBook_author());
            book.setBook_publisher(showBook.getBook_publisher());
            book.setBook_date(showBook.getBook_date());
            book.setBook_count(showBook.getBook_count());
            List<BookType> types = bookDao.findAllBookType();
            for (int i = 0; i < types.size(); i++) {
                BookType bookType = types.get(i);
                if(showBook.getType_name().equals(bookType.getType_name())){
                    book.setBook_type(bookType.getType_id());
                    break;
                }
            }
            bookDao.updateBook(book);
            result.setStatus("success");
            return result;
        }
        result.setStatus("error-id");
        return result;
    }
    public Result deleteBook(String id){
        Result result = new Result();
        if(bookDao.existBook(id) == 1){
            if(bookDao.existLogByBook(id) == 0){
                bookDao.deleteBook(id);
                result.setStatus("success");
                return result;
            }
            result.setStatus("此书已借，无法删除");
            return result;
        }
        result.setStatus("error-id");
        return result;
    }

    public Result findUserBook(String user,int page){
        Result result = new Result();
        result.setObject(bookDao.findUserBook(user, (page-1)*10));
        result.setStatus("success");
        return result;
    }

    public Result countUserBook(String user_id){
        Result result = new Result();
        result.setObject(bookDao.countUserBook(user_id));
        result.setStatus("success");
        return result;
    }

    public Result borrowBook(String bookId,String user){
        Result result = new Result();
//        int exist = bookDao.existBookLog(bookId,user);
        Book book = bookDao.findBookById(bookId);
        BookLog bookLog = new BookLog();
        bookLog.setUser_id(user);
        bookLog.setBook_id(bookId);
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.getYear()+"-"+localDateTime.getMonthValue()+"-"+localDateTime.getDayOfMonth()+" "+localDateTime.getHour()+":"+localDateTime.getMinute()+":"+localDateTime.getSecond();
        bookLog.setLog_date(date);
        bookLog.setLog_status(1);
        bookDao.borrowBook(bookLog);
        bookDao.updateBookCount(book.getBook_id(),book.getBook_count()-1);
        result.setStatus("success");
        return result;
    }


    public Result backBook(String book_id,String user,String date){
        Result result = new Result();
        bookDao.backBook(book_id, user , date);
        Book book = bookDao.findBookById(book_id);
        bookDao.updateBookCount(book.getBook_id(),book.getBook_count()+1);
        result.setStatus("success");
        return result;
    }


}
