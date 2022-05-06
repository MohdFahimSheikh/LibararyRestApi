package com.epam.libraryservice.service;

import com.epam.libraryservice.Repository.LibraryRepository;
import com.epam.libraryservice.Repository.LibraryUserClient;
import com.epam.libraryservice.entiity.Library;
import com.epam.libraryservice.entiity.LibraryDto;
import com.epam.libraryservice.entiity.UserDto;
import com.epam.libraryservice.exception.UserAlreadyHaveThisBook;
import com.epam.libraryservice.exception.UserAlreadyHaveThreeBooks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class LibraryUserService {

    @Autowired
    LibraryUserClient libraryUserClient;

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LibraryBookService libraryBookService;

    public List<UserDto> getAllUsers()
    {
        return libraryUserClient.getAllUsers();
    }
    public UserDto getUserByUserName(String username)
    {
        return libraryUserClient.getUserByUserName(username);
    }
    public void addUser(UserDto userDto)
    {
         libraryUserClient.addUser(userDto);
    }
    public void deleteUser(String username)
    {
         libraryUserClient.deleteUser((username));
    }
    public void updateUser(String username,UserDto userDto)
    {
        libraryUserClient.updateUser(username,userDto);
    }

    public LibraryDto giveUserABook(String username, int bookId) throws UserAlreadyHaveThreeBooks, UserAlreadyHaveThisBook {
        getUserByUserName(username);
        libraryBookService.getBookById(bookId);
        List<Library> libraryUserList=libraryRepository.findByUsername(username);
        if(libraryUserList.size()>=3)
            throw new UserAlreadyHaveThreeBooks("User already have 3 books. So, give away 3books to get a new book");
        for(Library library: libraryUserList){
            if(library.getBookId() == bookId)
                throw new UserAlreadyHaveThisBook("User already have book with id: "+bookId+". So,cannot take this book");
        }
        Library library=new Library(username,bookId);
        Library librarycopy=libraryRepository.save(library);
        return getDtoByLibrary(librarycopy);
    }

    public void releaseBookFromUser(String username, int bookId) {
        libraryRepository.deleteByUsernameAndBookId(username,bookId);
    }
    private LibraryDto getDtoByLibrary(Library librarycopy) {
        return modelMapper.map(librarycopy,LibraryDto.class);
    }
}
