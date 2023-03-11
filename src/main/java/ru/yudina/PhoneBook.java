package ru.yudina;

import ru.yudina.converter.ContactConverter;
import ru.yudina.converter.ContactValidationConverter;
import ru.yudina.dao.ContactDao;
import ru.yudina.service.ContactService;

/**
 * Created by Anna on 14.06.2017.
 */
public class PhoneBook {

    public static ContactDao contactDao = new ContactDao();

    public static ContactService phoneBookService = new ContactService();

    public static ContactConverter contactConverter = new ContactConverter();

    public static ContactValidationConverter contactValidationConverter = new ContactValidationConverter();
}
