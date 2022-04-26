package com.example.lendinglibrary.models.DTOs;

import com.example.lendinglibrary.models.Ebook;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class EbookDTOCollection {

  private List<EbookDTO> ebooks;

  public EbookDTOCollection(List<Ebook> ebooks){
    this.ebooks =new ArrayList<>();

    for(Ebook ebook: ebooks){
      this.ebooks.add(new EbookDTO(ebook.getTitle(), ebook.getAuthor()));
    }
  }

}
