/**
 * Created by tendaimupezeni for ContactList
 * Date: 8/18/24
 * Time: 4:07 PM
 */

package com.denyaar.contactlist.controller;

import com.denyaar.contactlist.Constants.Constant;
import com.denyaar.contactlist.model.Contact;
import com.denyaar.contactlist.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping(("/api/contacts"))
@RequiredArgsConstructor
@Slf4j
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<Page<Contact>> getContacts(@RequestParam(value= "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(contactService.getContacts(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable String id) {
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable String id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact) {
//        return ResponseEntity.ok(contactService.saveContact(contact));
        return ResponseEntity.created(URI.create("/contacts/userID")).body(contactService.saveContact(contact));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam String id, @RequestParam MultipartFile file) {
        return ResponseEntity.ok(contactService.uploadImage(id, file));
    }

    @GetMapping(value = "/image/{filename}", produces = {IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE})
    public byte[] getImage(@PathVariable String filename) throws IOException {
        return Files.readAllBytes(Paths.get(Constant.PHOTO_DIRECTORY_PATH + filename));
    }

}
