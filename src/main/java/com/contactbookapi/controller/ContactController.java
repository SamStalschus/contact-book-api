package com.contactbookapi.controller;

import com.contactbookapi.dto.ContactDTO;
import com.contactbookapi.enums.FilterContactTypes;
import com.contactbookapi.service.impl.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/** Controller de clientes
 *
 * @author Samuel Stalschus
 *
 * */
@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    @Autowired
    ContactService contactService;


    /** Método usado para criar um contato.
     *
     * @author Samuel Stalschus
     *
     * @return Contato criado
     *
     * */
    @PostMapping("")
    public ResponseEntity<ContactDTO> create(@Valid @RequestBody ContactDTO contactDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ContactDTO.convert(contactService.create(ContactDTO.convert(contactDTO)))
        );
    }

    /** Método usado para atualizar um contato.
     *
     * @author Samuel Stalschus
     *
     * @return Contato criado
     *
     * */
    @PutMapping("")
    public ResponseEntity<ContactDTO> update(@Valid @RequestBody ContactDTO contactDTO) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ContactDTO.convert(contactService.update(ContactDTO.convert(contactDTO)))
        );
    }

    /** Método usado para retornar para o client a lista de contatos.
     *
     * @author Samuel Stalschus
     *
     * @return Lista de DTOs de contatos acoplado ao Response Entity
     *
     * */
    @GetMapping("")
    public ResponseEntity<List<ContactDTO>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                contactService.getAll().stream().map(ContactDTO::convert).collect(Collectors.toList())
        );
    }

    /** Método usado para obter um contato pelo ID.
     *
     * @author Samuel Stalschus
     *
     * @return Cliente
     *
     * */
    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> listAll(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ContactDTO.convert(contactService.getById(id))
        );
    }

    /** Método usado para filtrar a busca de um contato.
     *
     * @author Samuel Stalschus
     *
     * @return Cliente
     *
     * */
    @GetMapping("/filter")
    public ResponseEntity<List<ContactDTO>> getByFilter(@RequestParam FilterContactTypes filterType, @RequestParam String filterValue) {
        return ResponseEntity.status(HttpStatus.OK).body(
                contactService.getContactsByFilter(filterType, filterValue).stream().map(ContactDTO::convert).collect(Collectors.toList())
        );
    }

    /** Método usado para deletar um contato pelo ID.
     *
     * @author Samuel Stalschus
     *
     * @return Cliente
     *
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
