package com.example.lendinglibrary.models;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ebook {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String author;
  private String topic;
  private LocalDate dueDate;
  private boolean isCheckedOut;

  @ManyToOne(fetch = FetchType.LAZY)
  private LibraryUser libraryUser;

  public Ebook(String title, String author, String topic){
    this.title=title;
    this.author=author;
    this.topic=topic;
  }

}
