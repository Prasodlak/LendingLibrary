package com.example.lendinglibrary.services;

import com.example.lendinglibrary.models.LibraryUser;

public interface LibraryUserService {

  void saveLibraryUser(String email, String password);

  LibraryUser getLibraryUserByEmail(String email);

  LibraryUser getLibraryUserById(Long id);

  boolean isLibraryUserInDatabase(String email);

  boolean isUserInDatabaseById(Long id);

  boolean isEmailAndPasswordMatching(String email, String password);

  boolean isEmailAndIdMatching(String email, Long id);

  void saveUser(LibraryUser libraryUser);


}
