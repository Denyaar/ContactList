/**
 * Created by tendaimupezeni for ContactList
 * Date: 8/18/24
 * Time: 3:17 PM
 */

package com.denyaar.contactlist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contacts")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Contact {
    @Id
    @UuidGenerator
    @Column(name = "contact_id", updatable = false, nullable = false, unique = true)
    private String id;
    private String name;
    private String email;
    private String title ;
    private String phone;
    private String address;
    private String status;
    private String imageUrl;
}
