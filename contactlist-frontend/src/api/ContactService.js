import axios from "axios";

const API_URL = "http://localhost:8080/api/contacts";

export async function getAllContacts(page = 0, size = 10) {
  try {
    const response = await axios.get(`${API_URL}?page=${page}&size=${size}`);
    return response;
  } catch (error) {
    console.error("Error while fetching contacts", error);
  }
}

export async function getContactById(id) {
  try {
    return await axios.get(`${API_URL}/${id}`);
  } catch (error) {
    console.error("Error while fetching contact", error);
  }
}

export async function addContact(contact) {
  try {
    const response = await axios.post(API_URL + "/save", contact);
    return response;
  } catch (error) {
    console.error("Error while adding contact", error);
  }
}

export async function updatePhoto(formData) {
  try {
    const response = await axios.post(`${API_URL}/upload`, formData);
    return response;
  } catch (error) {
    console.error("Error while updating contact", error);
  }
}

export async function deleteContact(id) {
  try {
    const response = await axios.delete(`${API_URL}/${id}`);
    return response;
  } catch (error) {
    console.error("Error while updating contact", error);
  }
}
