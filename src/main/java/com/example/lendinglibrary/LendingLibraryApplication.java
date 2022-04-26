package com.example.lendinglibrary;

import com.example.lendinglibrary.models.Ebook;
import com.example.lendinglibrary.repositories.EbookRepository;
import com.example.lendinglibrary.services.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LendingLibraryApplication implements CommandLineRunner {

  private EbookService ebookService;

  @Autowired
  public LendingLibraryApplication(EbookService ebookService){
    this.ebookService=ebookService;
  }

  public static void main(String[] args) {
    SpringApplication.run(LendingLibraryApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    if(ebookService.getAll().isEmpty()){
      ebookService.saveEbook(new Ebook("Learn Java in 5 minutes", "Statee", "Programming"));
      ebookService.saveEbook(new Ebook("How to cook amazing Lunch", "Halina", "Cooking"));
      ebookService.saveEbook(new Ebook("Make your cat happy", "Briene", "Pets"));
      ebookService.saveEbook(new Ebook("Make your dog look like cat", "Igor", "Pets"));
      ebookService.saveEbook(new Ebook("How not to pass the exam", "Fabian", "Programming"));
      ebookService.saveEbook(new Ebook("Petr Konig and Program of Secrets", "Petr", "Programming"));
      ebookService.saveEbook(new Ebook("Frederika prisoner from The Ascaban", "Igor", "Lifestyle"));
      ebookService.saveEbook(new Ebook("Headache my friend", "Veverka", "Lifestyle"));
      ebookService.saveEbook(new Ebook("How to slap your enemies", "Will", "Hobby"));
      ebookService.saveEbook(new Ebook("Manage your anger and stress", "Rambo", "Hobby"));
    }

  }
}
