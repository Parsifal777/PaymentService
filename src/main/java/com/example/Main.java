package com.example;

import com.example.config.AppConfig;
import com.example.directory.TariffDirectory;
import com.example.model.Invoice;
import com.example.model.Resident;
import com.example.model.Payment;
import com.example.service.ResidentService;
import com.example.service.PaymentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("Бины из app.config:");
        Resident resident1 = context.getBean("resident1", Resident.class);
        Resident resident2 = context.getBean("resident2", Resident.class);
        Invoice invoice1 = context.getBean("invoice1", Invoice.class);
        Payment payment1 = context.getBean("payment1", Payment.class);

        System.out.println("resident1: " + resident1);
        System.out.println("resident2: " + resident2);
        System.out.println("invoice1: " + invoice1);
        System.out.println("payment1: " + payment1);
        System.out.println();

        System.out.println("Проверка связей между бинами:");
        System.out.println("Владелец invoice1: " + invoice1.getResident().getFullName());
        System.out.println("Владелец payment1: " + payment1.getResident().getFullName());
        System.out.println();

        ResidentService residentService = context.getBean(ResidentService.class);
        PaymentService paymentService = context.getBean(PaymentService.class);

        Resident newResident1 = new Resident(1L, "Артем", "ул. Новая, 11");
        residentService.addResident(newResident1);

        Resident newResident2 = new Resident(2L, "Иван", "ул. Новая, 45");
        residentService.addResident(newResident2);

        Payment newPayment1 = new Payment(2L, newResident2, LocalDate.now(), 5000.0);
        paymentService.addPayment(newPayment1);

        Payment newPayment2 = new Payment(1L, newResident1, LocalDate.now(), 12345.0);
        paymentService.addPayment(newPayment2);

        Resident res1 = new Resident();
        residentService.setInfo("Улица Пушкина", "Иванов Петр Васильевич", 234L, res1);
        System.out.println("Найденный пользователь:");
        System.out.println(residentService.toId(234L));

        Resident res2 = new Resident();
        residentService.setInfo("Улица Кукушкина", "Сергеев Антон Сильвесторович", 445L, res2);
        System.out.println("Найденный пользователь:");
        System.out.println(residentService.toId(445L));

        System.out.println("Все жильцы:");
        residentService.getAllResidents().forEach(System.out::println);
        System.out.println("Все платежи:");
        paymentService.getAllPayments().forEach(System.out::println);

        TariffDirectory tariffDirectory = context.getBean(TariffDirectory.class);
        System.out.println("Демонстрация работы справочника тарифов:");

        tariffDirectory.addTariff(1L, new BigDecimal("4.50"), "кВт/ч");
        tariffDirectory.addTariff(2L, new BigDecimal("35.20"), "м³");
        tariffDirectory.addTariffWithDescription(3L, new BigDecimal("1800.00"), "м²",
                "Тариф на отопление для жилых помещений");

        tariffDirectory.updateTariff(1L, new BigDecimal("4.75"));
        tariffDirectory.updateTariffFull(2L, new BigDecimal("38.50"), "м³",
                "Обновленный тариф на водоснабжение");

        tariffDirectory.getTariffInfo(1L);
        tariffDirectory.getAllTariffs();

        tariffDirectory.deleteTariff(3L);

        System.out.println("Все операции с тарифами записаны в журнал:");

        System.out.println("Демонстрация аспектов с аннотацией @ToLog:");

        Resident resident = new Resident(1L, "Иванов Иван", "ул. Ленина, 10");
        residentService.addResident(resident);

        Resident resident3 = new Resident();
        residentService.setInfo("ул. Пушкина, 20", "Петров Петр", 2L, resident3);

        try {
            residentService.deleteResident(999L);
        } catch (Exception e) {
            System.out.println("Ожидаемая ошибка при удалении: " + e.getMessage());
        }

        Payment payment = new Payment(1L, resident, LocalDate.now(), 2500.0);
        paymentService.addPayment(payment);

        paymentService.deletePayment(1L);

        System.out.println("Все операции залогированы аспектами:");
    }
}