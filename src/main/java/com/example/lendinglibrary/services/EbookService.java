package com.example.lendinglibrary.services;

import com.example.lendinglibrary.models.Ebook;
import com.example.lendinglibrary.models.LibraryUser;
import java.util.List;

public interface EbookService {

  void saveEbook(Ebook ebook);

  List<Ebook> getAll();

  List<Ebook> getAllEbooksByTitle(String title);

  List<Ebook> getAllEbooksByAuthor(String author);

  List<Ebook> getAllEbooksByTopic(String topic);

  List<Ebook> getAllEbooksByAll(String anything);

  List<Ebook> getAllBooksOfUser(String email);

  void checkOut(String email, Long id);

  void returnBook(String email, Long id);

  Ebook getEbookById(Long id);

  List<Ebook> getEbooksByUserId(Long id);

  boolean isEbookInDatabase(Long id);

  void deleteEbook(Long id);

}
