package TestCases.MyTicket;

import Common.utilities.Utilities;
import PageObjects.BookTicketPage;
import PageObjects.LoginPage;
import PageObjects.MyTicketPage;
import PageObjects.RegisterPage;
import TestCases.BaseTest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class filetWithDepartDate extends BaseTest {
    private static final Logger logger = LogManager.getLogger(filetWithDepartDate.class);

    @Test
    public void filterWithDepartStation() {
        RegisterPage registerPage = new RegisterPage();
        logger.info("Register a new account");
        registerPage.registerNewAccount();

        LoginPage loginPage = new LoginPage();
        logger.info("Login with new account");
        loginPage.clickLoginTab();
        loginPage.loginAccount(registerPage.getEmail(), registerPage.getPassword());

        BookTicketPage bookTicketPage = new BookTicketPage();
        logger.info("Book a ticket");
        for (int i = 0; i < 7; i++) {
            String departDate = Utilities.randomNumber(5, 20);
            String departFrom = Utilities.randomNumber(1, 6);
            String arriveAt = Utilities.randomNumber(2, 6);
            String seatType = Utilities.randomNumber(1, 6);
            String ticketAmount = Utilities.randomNumber(1, 2);
            bookTicketPage.clickBookTicketTab();
            bookTicketPage.bookTicketKet(departDate, departFrom, arriveAt, seatType, ticketAmount);
        }
        MyTicketPage myTicketPage = new MyTicketPage();
        String randomDate = Utilities.randomNumber(10000000,11111111);
        logger.info("Click My Ticket tab");
        myTicketPage.clickMyTicketTab();
        myTicketPage.getTextDepart(randomDate);
        myTicketPage.clickBtnFilter();
        System.out.println(randomDate);
        System.out.println(String.valueOf(myTicketPage.isMatchDate()));
        Assert.assertTrue(myTicketPage.isMatchDate());
        //myTicketPage.isMatchDate();
    }
}
