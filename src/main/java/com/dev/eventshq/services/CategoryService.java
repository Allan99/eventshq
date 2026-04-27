package com.dev.eventshq.services;

import com.dev.eventshq.dto.CategoryDTO;
import com.dev.eventshq.entities.Category;
import com.dev.eventshq.repositories.CategoryRepository;
import com.dev.eventshq.services.exceptions.DatabaseException;
import com.dev.eventshq.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Category category = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable){
        Page<Category> categories = repository.findAll(pageable);
        return categories.map(x -> modelMapper.map(x, CategoryDTO.class));
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto){
        Category entity = new Category();
        modelMapper.map(dto, entity);
        entity = repository.save(entity);
        return modelMapper.map(entity, CategoryDTO.class);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto){
        try{
            Category category = repository.getReferenceById(id);
            category.setDescription(dto.getDescription());
            category = repository.save(category);
            return modelMapper.map(category, CategoryDTO.class);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Resource not found");
        }
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Referential integrity failure");
        }
    }


}
