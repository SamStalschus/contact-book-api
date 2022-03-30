package com.contactbookapi.service.impl;

import com.contactbookapi.domain.Contact;
import com.contactbookapi.enums.FilterContactTypes;
import com.contactbookapi.exception.ContactAlreadyExistsException;
import com.contactbookapi.exception.MissingParamsException;
import com.contactbookapi.exception.NotFoundException;
import com.contactbookapi.repository.ContactRepository;
import com.contactbookapi.service.IContactService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ContactService implements IContactService<Contact, Long> {

    /**
     * The Contact repository.
     */
    ContactRepository contactRepository;

    /**
     * Instantiates a new Contact service.
     *
     * @param contactRepository the contact repository
     */
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact create(Contact contact) {
        if(!contactAlreadyExists(contact))
            return contactRepository.save(contact);
        else
            throw new ContactAlreadyExistsException("Contact already exists");
    }

    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    public Contact getById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new NotFoundException("Contact not found"));
    }

    public Contact update(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    public List<Contact> getContactsByFilter(FilterContactTypes filterType, String filterValue) {
        if(filterType.equals(FilterContactTypes.NAME))
            return contactRepository.findByNameContains(filterValue);
        else if(filterType.equals(FilterContactTypes.NICKNAME))
            return contactRepository.findByNicknameContains(filterValue);
        else if(filterType.equals(FilterContactTypes.NUMBER))
            return contactRepository.findByNumberContains(filterValue);
        else if(filterType.equals(FilterContactTypes.EMAIL))
            return contactRepository.findByEmailContains(filterValue);
        else throw new MissingParamsException("Filter used don't exists ");
    }

    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    private boolean contactAlreadyExists(Contact contact) {
        return contactRepository.findByNumber(contact.getNumber()) != null;
    }
}
