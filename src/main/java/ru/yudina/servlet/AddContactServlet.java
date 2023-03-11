package ru.yudina.servlet;

import ru.yudina.PhoneBook;
import ru.yudina.converter.ContactConverter;
import ru.yudina.converter.ContactValidationConverter;
import ru.yudina.model.Contact;
import ru.yudina.service.ContactService;
import ru.yudina.service.ContactValidation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class AddContactServlet extends HttpServlet {

    private ContactService phoneBookService = PhoneBook.phoneBookService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;
    private ContactValidationConverter contactValidationConverter = PhoneBook.contactValidationConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (OutputStream responseStream = resp.getOutputStream()) {
            String contactJson = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            Contact contact = contactConverter.convertFormJson(contactJson);

            ContactValidation contactValidation = phoneBookService.addContact(contact);
            String contactValidationJson = contactValidationConverter.convertToJson(contactValidation);
            if (!contactValidation.isValid()) {
                resp.setStatus(500);
            }

            responseStream.write(contactValidationJson.getBytes(Charset.forName("UTF-8")));
        } catch (Exception e) {
            System.out.println("error in GetAllContactsServlet GET: ");
            e.printStackTrace();
        }
    }
}
