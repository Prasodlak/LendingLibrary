package com.example.lendinglibrary.services;

import com.example.lendinglibrary.models.LibraryUser;
import com.example.lendinglibrary.repositories.LibraryUserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryUserServiceImpl implements LibraryUserService{

  private LibraryUserRepository libraryUserRepository;

  @Autowired
  public LibraryUserServiceImpl(LibraryUserRepository libraryUserRepository){
    this.libraryUserRepository=libraryUserRepository;
  }

  @Override
  public void saveLibraryUser(String email, String password) {
      LibraryUser newUser= new LibraryUser(email, password);
      if(!libraryUserRepository.existsByEmail(email)){
        libraryUserRepository.save(newUser);
      }
  }

  @Override
  public LibraryUser getLibraryUserByEmail(String email) {
    if(libraryUserRepository.existsByEmail(email)){
      return libraryUserRepository.findLibraryUserByEmail(email).get();
    }else{
      return null;
    }
  }

  @Override
  public LibraryUser getLibraryUserById(Long id) {
    if(libraryUserRepository.existsById(id)){
      return libraryUserRepository.findLibraryUserById(id).get();
    }else{
      return null;
    }
  }

  @Override
  public boolean isLibraryUserInDatabase(String email) {
    return libraryUserRepository.existsByEmail(email);
  }

  @Override
  public boolean isUserInDatabaseById(Long id) {
    return libraryUserRepository.existsById(id);
  }

  @Override
  public boolean isEmailAndPasswordMatching(String email, String password) {
    if(libraryUserRepository.existsByEmail(email)){
      LibraryUser retrievedUser= getLibraryUserByEmail(email);
      return retrievedUser.getPassword().equals(password);
    }else{
      return false;
    }
  }

  @Override
  public boolean isEmailAndIdMatching(String email, Long id) {
    if(libraryUserRepository.existsByEmail(email)){
      LibraryUser retrievedUser= getLibraryUserByEmail(email);
      return retrievedUser.getId().equals(id);
    }else{
      return false;
    }
  }

  @Override
  public void saveUser(LibraryUser libraryUser) {
    libraryUserRepository.save(libraryUser);
  }


}
