package com.contactbookapi.repository;

import com.contactbookapi.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Repository do contato
 *
 * @author Samuel Stalschus
 *
 * */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact findByNumber(String number);

    @Query("SELECT c FROM Contact c WHERE c.number LIKE %:number%")
    List<Contact> findByNumberContains(String number);

    @Query("SELECT c FROM Contact c WHERE c.name LIKE %:name%")
    List<Contact> findByNameContains(String name);

    @Query("SELECT c FROM Contact c WHERE c.nickname LIKE %:nickname%")
    List<Contact> findByNicknameContains(String nickname);

    @Query("SELECT c FROM Contact c WHERE c.email LIKE %:email%")
    List<Contact> findByEmailContains(String email);
}
