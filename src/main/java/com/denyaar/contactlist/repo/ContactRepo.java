/**
 * Created by tendaimupezeni for ContactList
 * Date: 8/18/24
 * Time: 3:26 PM
 */

package com.denyaar.contactlist.repo;

import com.denyaar.contactlist.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Component
public interface ContactRepo extends JpaRepository<Contact, String> {
    Optional<Contact> findContactById(String id);
}
