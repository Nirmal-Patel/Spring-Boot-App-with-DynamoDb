package com.tekion.dynamodblocaldemo.service;

import com.tekion.dynamodblocaldemo.service.beans.*;
import com.tekion.dynamodblocaldemo.service.repository.AuthorRepository;
import com.tekion.dynamodblocaldemo.service.repository.BookRepository;
import com.tekion.dynamodblocaldemo.service.repository.LendRepository;
import com.tekion.dynamodblocaldemo.service.repository.MemberRepository;
import com.tekion.dynamodblocaldemo.service.requestDTOs.AuthorRequest;
import com.tekion.dynamodblocaldemo.service.requestDTOs.BookLendRequest;
import com.tekion.dynamodblocaldemo.service.requestDTOs.BookRequest;
import com.tekion.dynamodblocaldemo.service.requestDTOs.MemberRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final LendRepository lendRepository;
    private final MemberRepository memberRepository;

    public Book readBookById(String id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }
        try {
            throw new Exception("Can't find book fo given Id.");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    public Iterable<Book> readBooks() {
        return bookRepository.findAll();
    }

    public Book readBook(String isbn){
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if(book.isPresent()){
            return book.get();
        }
        try {
            throw new Exception("Can't find book fo given isbn.");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    public Book addBook(BookRequest bookRequest){
        Optional<Author> author = authorRepository.findById(bookRequest.getAuthorId());
        if(!author.isPresent()){
            try {
                throw new Exception("author not found");
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
        Book book = Book.fromRequest(bookRequest);
        return bookRepository.save(book);
    }

    public String deleteBook(String id){
        bookRepository.deleteById(id);
        return "Success";
    }

    public Member addMember(MemberRequest memberRequest){
        Member member = Member.fromRequest(memberRequest);
        return memberRepository.save(member);
    }

    public Member updateMember(String id,MemberRequest memberRequest){
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(!optionalMember.isPresent()){
            try {
                throw new Exception("member is not present");
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
        Member member = Member.fromRequest(memberRequest);
        member.setId(optionalMember.get().getId());
        return memberRepository.save(member);
    }

    public Author addAuthor(AuthorRequest authorRequest){
        Author author = Author.fromRequest(authorRequest);
        return authorRepository.save(author);
    }

    public List<String> lendBook(BookLendRequest bookLendRequest){
        Optional<Member> memberOptional = memberRepository.findById(bookLendRequest.getMemberId());

        if(!memberOptional.isPresent()){
            try {
                throw new Exception("member is not found in database");
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }

        Member member = memberOptional.get();

        if(member.getStatus() != MemberStatus.ACTIVE){
            throw new RuntimeException("user is not active");
        }

        List<String> booksApprovedToBorrow = new ArrayList<>();
        bookLendRequest.getBookIds().forEach(
                bookId -> {
                    Optional<Book> bookForId = bookRepository.findById(bookId);
                    if(!bookForId.isPresent()){
                        try {
                            throw new Exception("book is not found given id");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Optional<Lend> borrowedBook = lendRepository.findByBookIdAndStatus(bookId,LendStatus.BORROWED);
                    if(!borrowedBook.isPresent()){
                        booksApprovedToBorrow.add(bookForId.get().getName());
                        Lend lend = new Lend();

                        lend.setBookId(bookId);
                        lend.setMemberId(member.getId());
                        lend.setStartOn(Instant.now().toString());
                        lend.setDueOn(Instant.now().plus(30, ChronoUnit.DAYS).toString());
                        lend.setStatus(LendStatus.BORROWED);
                        lendRepository.save(lend);
                    }
                });
        return booksApprovedToBorrow;
    }

}
