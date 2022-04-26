package com.example.lendinglibrary.services;

import com.example.lendinglibrary.models.Ebook;
import com.example.lendinglibrary.models.LibraryUser;
import com.example.lendinglibrary.repositories.EbookRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EbookServiceImpl implements EbookService {

  private EbookRepository ebookRepository;
  private LibraryUserService libraryUserService;

  @Autowired
  public EbookServiceImpl(EbookRepository ebookRepository, LibraryUserService libraryUserService) {
    this.ebookRepository = ebookRepository;
    this.libraryUserService = libraryUserService;
  }

  @Override
  public void saveEbook(Ebook ebook) {
    ebookRepository.save(ebook);
  }

  @Override
  public List<Ebook> getAll() {
    return ebookRepository.findAll();
  }

  @Override
  public List<Ebook> getAllEbooksByTitle(String title) {
    return ebookRepository.getAllByTitleLike(title);
  }

  @Override
  public List<Ebook> getAllEbooksByAuthor(String author) {
    return ebookRepository.getAllByAuthorLike(author);
  }

  @Override
  public List<Ebook> getAllEbooksByTopic(String topic) {
    return ebookRepository.getAllByTopicLike(topic);
  }

  @Override
  public List<Ebook> getAllEbooksByAll(String anything) {
    return ebookRepository.getAllByAllLike(anything);
  }

  @Override
  public List<Ebook> getAllBooksOfUser(String email) {
    LibraryUser retrievedUser = libraryUserService.getLibraryUserByEmail(email);
    List<Ebook> ebookList = retrievedUser.getEbookList();
    return ebookList;
  }

  @Override
  public void checkOut(String email, Long id) {
    LibraryUser retrievedUser = libraryUserService.getLibraryUserByEmail(email);
    Ebook retrievedEbook = getEbookById(id);
    if (retrievedUser.getEbookList().size() < 3) {
      retrievedUser.getEbookList().add(retrievedEbook);
      retrievedEbook.setLibraryUser(retrievedUser);
      retrievedEbook.setCheckedOut(true);
      retrievedEbook.setDueDate(LocalDate.now().plusDays(7));
      ebookRepository.save(retrievedEbook);
    }
  }

  @Override
  public void returnBook(String email, Long id) {
    LibraryUser retrievedUser = libraryUserService.getLibraryUserByEmail(email);
    Ebook retrievedEbook = getEbookById(id);
    retrievedUser.getEbookList().remove(retrievedEbook);
    retrievedEbook.setLibraryUser(null);
    retrievedEbook.setCheckedOut(false);
    retrievedEbook.setDueDate(null);
    ebookRepository.save(retrievedEbook);
    libraryUserService.saveUser(retrievedUser);
  }

  @Override
  public Ebook getEbookById(Long id) {
    Optional<Ebook> retrievedEbook = ebookRepository.findById(id);
    if (!retrievedEbook.isEmpty()) {
      return retrievedEbook.get();
    } else {
      return null;
    }
  }

  @Override
  public List<Ebook> getEbooksByUserId(Long id) {
    List<Ebook> retrievedList = libraryUserService.getLibraryUserById(id).getEbookList();
    if (retrievedList.isEmpty()) {
      return new ArrayList<>();
    } else {
      return retrievedList;
    }
  }

  @Override
  public boolean isEbookInDatabase(Long id) {
    return ebookRepository.existsById(id);
  }

  @Override
  public void deleteEbook(Long id) {
    ebookRepository.deleteById(id);
  }
}
