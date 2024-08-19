/**
 * Created by tendaimupezeni for ContactList
 * Date: 8/18/24
 * Time: 3:34 PM
 */

package com.denyaar.contactlist.service;

import com.denyaar.contactlist.Constants.Constant;
import com.denyaar.contactlist.model.Contact;
import com.denyaar.contactlist.repo.ContactRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepo contactRepo;

    @Override
    public Page<Contact> getContacts(int page, int size) {
        return contactRepo.findAll(PageRequest.of(page, size, Sort.by("name", "email")));
    }

    @Override
    public Contact getContactById(String id) {
        return contactRepo.findContactById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepo.save(contact);
    }

    @Override
    public void deleteContact(String id) {
        contactRepo.deleteById(id);
    }

    @Override
    public String uploadImage(String id, MultipartFile file) {
        log.info("Uploading image for contact with id: {}", id);
        Contact contact = contactRepo.findContactById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        String imageUrl = photoFunction.apply(id, file);

        contact.setImageUrl(imageUrl);
        contactRepo.save(contact);
        return imageUrl;
    }

    private final Function<String, String> fileExtensions = filename -> Optional.of(filename)
            .filter(f -> f.contains("."))
            .map(f -> "." + f.substring(filename.lastIndexOf(".") + 1))
            .orElse(".png");

    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String fileName = fileExtensions.apply(image.getOriginalFilename());
        try {
            Path path = Paths.get(Constant.PHOTO_DIRECTORY_PATH).toAbsolutePath().normalize();
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.copy(image.getInputStream(), path.resolve(id + fileName), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/contacts/image/" + id + fileName).toUriString();
        } catch (Exception e) {
            log.error("Error uploading image", e);
            throw new RuntimeException("Error uploading image");
        }
    };
}
