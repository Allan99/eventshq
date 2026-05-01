package com.dev.eventshq.controllers;

import com.dev.eventshq.dto.AttendeeDTO;
import com.dev.eventshq.services.AttendeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/attendees")
public class AttendeeController {

    @Autowired
    public AttendeeService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AttendeeDTO> findById(@PathVariable Long id){
        AttendeeDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<AttendeeDTO>> findAll(Pageable pageable){
        Page<AttendeeDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AttendeeDTO> insert(@RequestBody AttendeeDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AttendeeDTO> update(@PathVariable Long id,
                                              @RequestBody AttendeeDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
