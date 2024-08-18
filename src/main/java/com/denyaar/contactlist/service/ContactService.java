/**
 * Created by tendaimupezeni for ContactList
 * Date: 8/18/24
 * Time: 3:33 PM
 */

package com.denyaar.contactlist.service;

import com.denyaar.contactlist.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ContactService {
    Page<Contact> getContacts(int page, int size);

    Contact getContactById(String id);

    Contact saveContact(Contact contact);

    void deleteContact(String id);

    String uploadImage(String id, MultipartFile file);
}
