package com.udhtu.controller;

import com.udhtu.model.dto.AuthorDTO;
import com.udhtu.model.entity.AuthorEntity;
import com.udhtu.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"authors"})
public class AuthorController implements ICrudController<AuthorDTO, Long> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorDTO.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<AuthorDTO> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всіх авторів");

        List<AuthorEntity> entityList = authorRepository.findAll();

        List<AuthorDTO> dtoList = new ArrayList<>(entityList.size());

        for (AuthorEntity entity : entityList)
            dtoList.add(buildDto(entity));

        return dtoList;
    }

    @Override
    public AuthorDTO getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання автора за ідентифікатором {}", id);

        AuthorDTO dto = null;

        Optional<AuthorEntity> preloadOptional = authorRepository.findById(id);

        if (preloadOptional.isPresent())
            dto = buildDto(preloadOptional.get());

        return dto;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення автора за ідентифікатором {}", id);
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorDTO saveOrUpdate(AuthorDTO dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження/редагування автора {} {} {}", new Object[]{dto.getFirstName(), dto.getLastName(), dto.getBooks()});

        AuthorEntity preload;
        if (dto.getId() == null) {
            preload = new AuthorEntity();
        } else {
            Optional<AuthorEntity> preloadOptional = authorRepository.findById(dto.getId());
            preload = preloadOptional.orElseGet(AuthorEntity::new);
        }

        preload.setFirstName(dto.getFirstName());
        preload.setLastName(dto.getLastName());
        preload.setBooks(dto.getBooks());

        preload = authorRepository.save(preload);

        dto.setId(preload.getId());

        return dto;
    }

    public AuthorDTO buildDto(AuthorEntity entity) {
        AuthorDTO dto = new AuthorDTO();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setBooks(entity.getBooks());

        return dto;
    }
}