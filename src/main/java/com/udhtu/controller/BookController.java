package com.udhtu.controller;

import com.udhtu.model.dto.AuthorDTO;
import com.udhtu.model.dto.BookDTO;
import com.udhtu.model.entity.AuthorEntity;
import com.udhtu.model.entity.BookEntity;
import com.udhtu.repository.AuthorRepository;
import com.udhtu.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping({"books"})
public class BookController implements ICrudController<BookDTO, Long> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookDTO.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<BookDTO> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всіх книг");

        List<BookEntity> entityList = bookRepository.findAll();

        List<BookDTO> dtoList = new ArrayList<>(entityList.size());

        for (BookEntity entity : entityList)
            dtoList.add(buildDto(entity));

        return dtoList;
    }

    @Override
    public BookDTO getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання книги за ідентифікатором {}", id);

        BookDTO dto = null;

        Optional<BookEntity> preloadOptional = bookRepository.findById(id);

        if (preloadOptional.isPresent())
            dto = buildDto(preloadOptional.get());

        return dto;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення книги за ідентифікатором {}", id);
        bookRepository.deleteById(id);
    }

    @Override
    public BookDTO saveOrUpdate(BookDTO dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження/редагування книги {} {} {}", new Object[]{dto.getDate(), dto.getEdition(), dto.getAuthor(), dto.getName(),dto.getBookType(),dto.getDescription(),dto.getCountOfPages(),dto.getRating()});

        BookEntity preload;
        if (dto.getId() == null) {
            preload = new BookEntity();
        } else {
            Optional<BookEntity> preloadOptional = bookRepository.findById(dto.getId());
            preload = preloadOptional.orElseGet(BookEntity::new);
        }

        if(dto.getAuthor() != null && dto.getAuthor().getId() != null) {
            Optional<AuthorEntity> optionalAuthor = authorRepository.findById(dto.getAuthor().getId());
            if(optionalAuthor.isPresent()) {
                preload.setAuthor(optionalAuthor.get());
            }
        }

        preload.setDate(dto.getDate());
        preload.setEdition(dto.getEdition());
        preload.setName(dto.getName());
        preload.setBookType(dto.getBookType());
        preload.setDescription(dto.getDescription());
        preload.setCountOfPages(dto.getCountOfPages());
        preload.setRating(dto.getRating());

        preload = bookRepository.save(preload);

        return getById(preload.getId());
    }

    public BookDTO buildDto(BookEntity entity) {
        BookDTO dto = new BookDTO();

        dto.setId(entity.getId());

        AuthorDTO authorDto = null;
        if(entity.getAuthor() != null) {
            AuthorEntity authorEntity = entity.getAuthor();
            authorDto = new AuthorDTO();
            authorDto.setId(authorEntity.getId());
            authorDto.setFirstName(authorEntity.getFirstName());
            authorDto.setLastName(authorEntity.getLastName());
            authorDto.setBooks(authorEntity.getBooks());
            dto.setAuthor(authorDto);
        }
        dto.setDate(entity.getDate());
        dto.setEdition(entity.getEdition());
        dto.setName(entity.getName());
        dto.setBookType(entity.getBookType());
        dto.setDescription(entity.getDescription());
        dto.setCountOfPages(entity.getCountOfPages());
        dto.setRating(entity.getRating());

        return dto;
    }
}
