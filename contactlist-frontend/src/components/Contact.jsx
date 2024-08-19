import React from "react";
import { Link } from "react-router-dom";

const Contact = ({ contact }) => {
  return (
    <Link to={`/contacts/${contact.id}`} className="contact_item">
      <div className="contact__header">
        <div className="contact__image">
          <img src={contact.imageUrl} alt={contact.name} />
        </div>
        <div className="contact__details">
          <p className="contact_name">{contact.name.substring(0, 15)}</p>
          <p className="contact_title">{contact.title}</p>
        </div>
      </div>

      <div className="contact__body">
        <p>
          <i className="bi bi-envelope"></i>
          {contact.email}
        </p>
        <p>
          <i className="bi bi-geo"></i>
          {contact.address}
        </p>
        <p>
          <i className="bi bi-telephone"></i>
          {contact.phone}
        </p>
        <p>
          {contact.status === "Active" ? <i className="bi bi-check-circle"></i> : <i className="bi bi-x-circle"></i>}
          {contact.status}
        </p>
      </div>
    </Link>
  );
};

export default Contact;
