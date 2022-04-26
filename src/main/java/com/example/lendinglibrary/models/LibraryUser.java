package com.example.lendinglibrary.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String password;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "libraryUser")
  private List<Ebook> ebookList;

  public LibraryUser(String email, String password){
    this.email=email;
    this.password=password;
    this.ebookList=new ArrayList<>();
  }

}
