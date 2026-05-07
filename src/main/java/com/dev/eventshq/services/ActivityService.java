package com.dev.eventshq.services;

import com.dev.eventshq.dto.ActivityDTO;
import com.dev.eventshq.entities.Activity;
import com.dev.eventshq.repositories.ActivityRepository;
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
public class ActivityService {

    @Autowired
    public ActivityRepository repository;

    @Autowired
    public ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public ActivityDTO findById(Long id){
        Activity activity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return modelMapper.map(activity, ActivityDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<ActivityDTO> findAll(Pageable pageable){
        Page<Activity> activities = repository.findAll(pageable);
        return activities.map(a -> modelMapper.map(a, ActivityDTO.class));
    }

    @Transactional
    public ActivityDTO insert(ActivityDTO dto){
        Activity activity = new Activity();
        activity.setName(dto.getName());
        activity.setPrice(dto.getPrice());
        activity.setDescription(dto.getDescription());
        activity.setCategory(dto.getCategory());
        activity = repository.save(activity);
        return modelMapper.map(activity, ActivityDTO.class);
    }

    @Transactional
    public ActivityDTO update(Long id, ActivityDTO dto){
        try{
            Activity activity = repository.getReferenceById(id);
            activity.setName(dto.getName());
            activity.setDescription(dto.getDescription());
            activity.setPrice(dto.getPrice());
            activity.setCategory(dto.getCategory());
            activity = repository.save(activity);
            return modelMapper.map(activity, ActivityDTO.class);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Resource not found");
        }
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Referencial integrity failure");
        }
    }

}
