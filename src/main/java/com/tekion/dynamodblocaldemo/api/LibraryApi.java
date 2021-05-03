package com.tekion.dynamodblocaldemo.api;

import com.tekion.dynamodblocaldemo.service.LibraryService;
import com.tekion.dynamodblocaldemo.service.beans.Author;
import com.tekion.dynamodblocaldemo.service.beans.Book;
import com.tekion.dynamodblocaldemo.service.beans.Member;
import com.tekion.dynamodblocaldemo.service.requestDTOs.AuthorRequest;
import com.tekion.dynamodblocaldemo.service.requestDTOs.BookLendRequest;
import com.tekion.dynamodblocaldemo.service.requestDTOs.BookRequest;
import com.tekion.dynamodblocaldemo.service.requestDTOs.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class LibraryApi {

    private final LibraryService libraryService;

    @PostMapping("/book")
    public ResponseEntity<Book> addbook(@RequestBody BookRequest bookRequest){
        return ResponseEntity.ok(libraryService.addBook(bookRequest));
    }

    @GetMapping("/book")
    public ResponseEntity readBooks(@RequestParam(required = false) String isbn){
        if(isbn==null){
            return ResponseEntity.ok(libraryService.readBooks());
        }
        return ResponseEntity.ok(libraryService.readBook(isbn));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> readBook(@PathVariable String id){
        return ResponseEntity.ok(libraryService.readBookById(id));
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id){
        return ResponseEntity.ok(libraryService.deleteBook(id));
    }

    @PostMapping("/member")
    public ResponseEntity<Member> addMember(@RequestBody MemberRequest memberRequest){
        return ResponseEntity.ok(libraryService.addMember(memberRequest));
    }

    @PatchMapping("/member/{id}")
    public ResponseEntity<Member> updateMember(@RequestBody MemberRequest memberRequest,@PathVariable String id){
        return ResponseEntity.ok(libraryService.updateMember(id,memberRequest));
    }

    @PostMapping("/book/lend")
    public ResponseEntity<List<String>> lendABook(@RequestBody BookLendRequest bookLendRequest){
        return ResponseEntity.ok(libraryService.lendBook(bookLendRequest));
    }

    @PostMapping("/author")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorRequest authorRequest){
        return ResponseEntity.ok(libraryService.addAuthor(authorRequest));
    }

}
