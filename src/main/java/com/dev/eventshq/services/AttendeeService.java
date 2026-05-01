package com.dev.eventshq.services;

import com.dev.eventshq.dto.AttendeeDTO;
import com.dev.eventshq.entities.Attendee;
import com.dev.eventshq.entities.Block;
import com.dev.eventshq.repositories.AttendeeRepository;
import com.dev.eventshq.services.exceptions.DatabaseException;
import com.dev.eventshq.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.dialect.Database;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class AttendeeService {

    @Autowired
    public AttendeeRepository repository;

    @Autowired
    public ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public AttendeeDTO findById(Long id){
        Attendee attendee = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return modelMapper.map(attendee, AttendeeDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<AttendeeDTO> findAll(Pageable pageable){
        Page<Attendee> attendees = repository.findAll(pageable);
        return attendees.map(a -> modelMapper.map(a, AttendeeDTO.class));
    }

    @Transactional
    public AttendeeDTO insert(AttendeeDTO dto){
        Attendee attendee = new Attendee();
        modelMapper.map(dto, attendee);
        attendee = repository.save(attendee);
        return modelMapper.map(attendee, AttendeeDTO.class);
    }

    @Transactional
    public AttendeeDTO update(Long id, AttendeeDTO dto){
        try{
            Attendee attendee = repository.getReferenceById(id);
            attendee.setEmail(dto.getEmail());
            attendee.setName(dto.getName());
            attendee = repository.save(attendee);
            return modelMapper.map(attendee, AttendeeDTO.class);
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
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Referencial integrity failure");
        }
    }

}
