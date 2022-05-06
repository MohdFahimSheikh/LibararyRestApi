package com.epam.libraryservice.Repository;

import com.epam.libraryservice.entiity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LibraryRepository extends JpaRepository<Library,Integer> {
    void deleteByUsernameAndBookId(String username, int bookId);

    List<Library> findByUsername(String username);
}
