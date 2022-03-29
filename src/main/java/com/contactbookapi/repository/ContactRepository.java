package com.contactbookapi.repository;

import com.contactbookapi.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repository do contato
 *
 * @author Samuel Stalschus
 *
 * */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    public Contact findByNumber(String number);
}
