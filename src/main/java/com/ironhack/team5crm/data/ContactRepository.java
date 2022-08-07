package com.ironhack.team5crm.data;

import com.ironhack.team5crm.data.datasources.Datasource;
import com.ironhack.team5crm.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
