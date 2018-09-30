package ru.zhigunov.study.organiser;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zhigunov.study.organiser.commands.HelpCommand;
import ru.zhigunov.study.organiser.commands.OrganiserCommand;
import ru.zhigunov.study.organiser.entity.Contact;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class OrganiserTest {

    private Organiser ctx;
    private File testEnv;

    @BeforeMethod
    private void setEnv() throws IOException {
        testEnv = new File("testEnv.txt");
        prepareNewTestScenario(testEnv);
        ctx = new Organiser(new FileInputStream(testEnv), System.out);
    }

    private void prepareNewTestScenario(File testEnv) throws IOException {
        if (testEnv.exists()) {
            testEnv.delete();
            testEnv.createNewFile();
        }
    }

    @Test(priority = 0)
    public void testRegisterCommand() throws Exception {
        ctx = new Organiser(new FileInputStream(testEnv), System.out);
        OrganiserCommand command = new HelpCommand(ctx);
        ctx.registerCommand(command);
        Assert.assertTrue(ctx.getCommandMap().containsValue(command));
    }

    @Test(priority = 1)
    public void testInsert() throws Exception {
        List<String> testScenario;
        String commandName = "insert";
        String FIO = "Zhigunov";
        String job = "job";
        String org = "org";
        String email = "example@mail.com";
        String phones = "+7-32-12 +72332";

        testScenario = new LinkedList<>();
        testScenario.add(commandName);
        testScenario.add(FIO);
        testScenario.add(job);
        testScenario.add(org);
        testScenario.add(email);
        testScenario.add(phones);
        prepareNewTestScenario(testEnv);
        Files.write(testEnv.toPath(), testScenario);
        ctx.run();

        List<Contact> list = ctx.getContactList().getList();
        Contact contact = list.get(list.size() - 1);
        Assert.assertNotNull(contact);
        Assert.assertEquals(contact.getFIO(), FIO);
        Assert.assertEquals(contact.getJobPost(), job);
        Assert.assertEquals(contact.getOrganisation(), org);
        Assert.assertEquals(contact.getEmail(), email);
        Assert.assertTrue(
                contact.getPhones().containsAll(
                        Arrays.asList(phones.split(" "))
                )
        );

//        testScenario = new LinkedList<>();
//        testScenario.add("delete");
//        testScenario.add(id.toString());
//        prepareNewTestScenario(testEnv);
//        Files.write(testEnv.toPath(), testScenario);
//        ctx.run();
    }

    @Test(priority = 2)
    public void testUpdate() throws Exception {
        List<Contact> list = ctx.getContactList().getList();
        Contact contact = list.get(list.size() - 1);

        List<String> testScenario;
        String commandName = "update";
        Integer id = contact.getId();
        String FIO = "Zhigunov_updated";
        String job = "job_updated";
        String org = "org_updated";
        String email = "example@mail.com_updated";
        String phones = "+7-32-12 +72332 _updated";

        testScenario = new LinkedList<>();
        testScenario.add(commandName);
        testScenario.add(id.toString());
        testScenario.add(FIO);
        testScenario.add(job);
        testScenario.add(org);
        testScenario.add(email);
        testScenario.add(phones);
        prepareNewTestScenario(testEnv);
        Files.write(testEnv.toPath(), testScenario);

        ctx.run();

        contact = list.get(list.size() - 1);
        Assert.assertNotNull(contact);
        Assert.assertEquals(contact.getFIO(), FIO);
        Assert.assertEquals(contact.getJobPost(), job);
        Assert.assertEquals(contact.getOrganisation(), org);
        Assert.assertEquals(contact.getEmail(), email);
        Assert.assertTrue(
                contact.getPhones().containsAll(
                        Arrays.asList(phones.split(" "))
                )
        );
    }

}