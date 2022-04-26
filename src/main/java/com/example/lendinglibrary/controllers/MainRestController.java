package com.example.lendinglibrary.controllers;

import com.example.lendinglibrary.models.DTOs.EbookDTOCollection;
import com.example.lendinglibrary.models.DTOs.ErrorDTO;
import com.example.lendinglibrary.models.DTOs.MessageDTO;
import com.example.lendinglibrary.models.Ebook;
import com.example.lendinglibrary.services.EbookService;
import com.example.lendinglibrary.services.LibraryUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {

  private EbookService ebookService;
  private LibraryUserService libraryUserService;

  @Autowired
  public MainRestController(EbookService ebookService, LibraryUserService libraryUserService) {
    this.ebookService = ebookService;
    this.libraryUserService = libraryUserService;
  }

  @GetMapping(value = "/user/{id}/ebooks")
  public ResponseEntity<?> getCheckedOutBooks(@PathVariable Long id) {
    if (libraryUserService.isUserInDatabaseById(id)) {
      List<Ebook> retrievedList = ebookService.getEbooksByUserId(id);
      EbookDTOCollection result = new EbookDTOCollection(retrievedList);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(new ErrorDTO("invalid id"), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping(value = "/ebook/{id}")
  public ResponseEntity<?> deleteEbook(@PathVariable Long id) {
    if (!ebookService.isEbookInDatabase(id)) {
      return new ResponseEntity<>(new ErrorDTO("Invalid id"), HttpStatus.NOT_FOUND);
    } else {
      ebookService.deleteEbook(id);
      return new ResponseEntity<>(new MessageDTO("e-book successfully deleted."),
          HttpStatus.NO_CONTENT);
    }
  }
}
