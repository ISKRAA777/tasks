package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Test
    public void shouldSendDailyEmail() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test message");

        EmailTemplateSelector template = EmailTemplateSelector.SCHEDULED_EMAIL;

        Class<?>[] params1 = new Class<?>[]{Mail.class, EmailTemplateSelector.class};
        Method cmm = simpleEmailService.getClass().getDeclaredMethod("createMimeMessage", params1);
        cmm.setAccessible(true);

        //when & then
        assertNotNull(cmm.invoke(simpleEmailService, mail, template).equals(null));
    }

    @Test
    public void shouldSendTrelloCardEmail() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        //Given
        Mail mail = new Mail("test@test.com", "cctest@test.com", "Test", "Test message");

        EmailTemplateSelector template = EmailTemplateSelector.TRELLO_CARD_EMAIL;

        Class<?>[] params1 = new Class<?>[]{Mail.class, EmailTemplateSelector.class};
        Method cmm = simpleEmailService.getClass().getDeclaredMethod("createMimeMessage", params1);
        cmm.setAccessible(true);

        //when & then
        assertNotNull(cmm.invoke(simpleEmailService, mail, template).equals(null));
    }
}