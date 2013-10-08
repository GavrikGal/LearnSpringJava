package com.apress.prospring3.ch17.repository;

import org.springframework.data.repository.CrudRepository;

import com.apress.prospring3.ch17.domain.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
