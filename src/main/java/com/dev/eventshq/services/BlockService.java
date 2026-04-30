package com.dev.eventshq.services;

import com.dev.eventshq.dto.BlockDTO;
import com.dev.eventshq.entities.Block;
import com.dev.eventshq.repositories.BlockRepository;
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
public class BlockService {

    @Autowired
    public BlockRepository repository;

    @Autowired
    public ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public BlockDTO findById(Long id){
        Block block = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return modelMapper.map(block, BlockDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<BlockDTO> findAll(Pageable pageable){
        Page<Block> blocks = repository.findAll(pageable);
        return blocks.map(b -> modelMapper.map(b, BlockDTO.class));
    }

    @Transactional
    public BlockDTO insert(BlockDTO dto){
        Block block = new Block();
        modelMapper.map(dto, block);
        block = repository.save(block);
        return modelMapper.map(block, BlockDTO.class);
    }

    @Transactional
    public BlockDTO update(Long id, BlockDTO dto){
        try{
            Block block = repository.getReferenceById(id);
            block.setStart(dto.getStart());
            block.setFinish(dto.getFinish());
            block = repository.save(block);
            return modelMapper.map(block, BlockDTO.class);
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
            throw new DatabaseException("Referencial integrity Failure");
        }
    }

}
