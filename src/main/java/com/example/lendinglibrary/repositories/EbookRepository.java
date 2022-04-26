package com.example.lendinglibrary.repositories;

import com.example.lendinglibrary.models.Ebook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {

  @Query("FROM Ebook e WHERE e.title LIKE %:title%")
  List<Ebook> getAllByTitleLike(@Param("title") String title);

  @Query("FROM Ebook e WHERE e.author LIKE %:author%")
  List<Ebook> getAllByAuthorLike(@Param("author") String author);

  @Query("FROM Ebook e WHERE e.topic LIKE %:topic%")
  List<Ebook> getAllByTopicLike(@Param("topic") String topic);

  @Query("FROM Ebook e WHERE e.title LIKE %:anything% OR e.topic LIKE %:anything% OR e.author LIKE %:anything%")
  List<Ebook> getAllByAllLike(@Param("anything") String anything);

}
