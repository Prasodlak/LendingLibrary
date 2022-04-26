package com.example.lendinglibrary.controllers;

import com.example.lendinglibrary.models.LibraryUser;
import com.example.lendinglibrary.services.EbookService;
import com.example.lendinglibrary.services.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  private EbookService ebookService;
  private LibraryUserService libraryUserService;

  @Autowired
  public MainController(EbookService ebookService, LibraryUserService libraryUserService){
    this.libraryUserService=libraryUserService;
    this.ebookService=ebookService;
  }

  @GetMapping(value = "/")
  public String renderMainPage(Model model,
      @RequestParam(required = false) String error,
      @RequestParam(required = false) String email) {

    if (error != null) {
      if (error.equals("loginError")) {
        model.addAttribute("error", " Error: login data invalid :(");
      }
      if(error.equals("userError")){
        model.addAttribute("error", " Error: Id and mail are not matching :(");
      }
      if (error.equals("checkOutFail")){
        model.addAttribute("error", "Book cannot be checked out, sorry");
      }
      if (error.equals("returnFail")){
        model.addAttribute("error", "Error: e-book was not returned, try again.");
      }
    }
    if (email != null) {
      model.addAttribute("libraryUser", libraryUserService.getLibraryUserByEmail(email));
    }
    model.addAttribute("ebooks", ebookService.getAll());
    return "index";
  }

  @PostMapping(value = "/user/login")
  public String login(@RequestParam String email, @RequestParam String password) {
    if (libraryUserService.isLibraryUserInDatabase(email)
        && libraryUserService.isEmailAndPasswordMatching(email, password)) {
      LibraryUser retrievedUser = libraryUserService.getLibraryUserByEmail(email);
      return "redirect:/?email=" + retrievedUser.getEmail();
    } else if (!libraryUserService.isLibraryUserInDatabase(email)) {
      libraryUserService.saveLibraryUser(email, password);
      return "redirect:/?email=" + email;
    } else if (!libraryUserService.isEmailAndPasswordMatching(email, password)) {
      return "redirect:/?error=loginError";
    } else {
      return "redirect:/?error=loginError";
    }
  }

  @GetMapping(value = "/ebook/search")
  public String getSearchedResults(@RequestParam (required = false) String email, @RequestParam String keyword, @RequestParam String type, Model model){
    if(type.equals("title")){
      model.addAttribute("ebooks", ebookService.getAllEbooksByTitle(keyword));
    }
    if(type.equals("author")){
      model.addAttribute("ebooks", ebookService.getAllEbooksByAuthor(keyword));
    }
    if(type.equals("topic")){
      model.addAttribute("ebooks", ebookService.getAllEbooksByTopic(keyword));
    }
    if(type.equals("all")){
      model.addAttribute("ebooks", ebookService.getAllEbooksByAll(keyword));
    }
    model.addAttribute("libraryUser", libraryUserService.getLibraryUserByEmail(email));
    return "index";
  }

  @GetMapping(value = "/user/{id}")
  public String getUserInfo(@PathVariable Long id, @RequestParam String email, Model model){
    if(libraryUserService.isEmailAndIdMatching(email, id)){
      model.addAttribute("libraryUser", libraryUserService.getLibraryUserByEmail(email));
      model.addAttribute("userBooks", ebookService.getAllBooksOfUser(email));
      return "userInfo";
    }else{
      return "redirect:/?error=userError";
    }
  }

  @PostMapping(value = "/ebook/check-out")
  public String checkOut(@RequestParam String email, @RequestParam Long bookId){
    if(email!=null && bookId!=null && libraryUserService.isLibraryUserInDatabase(email)){
      ebookService.checkOut(email, bookId);
      return "redirect:/?email="+email;
    }else {
      return "redirect:/?error=checkOutFail";
    }
  }

  @PostMapping(value = "/ebook/return")
  public String returnBook(@RequestParam String email, @RequestParam Long bookId){
    if(email!=null && bookId!=null && libraryUserService.isLibraryUserInDatabase(email)){
      ebookService.returnBook(email, bookId);
      return "redirect:/?email="+email;
    }else {
      return "redirect:/?error=returnFail";
    }
  }
}


