package com.example.directory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class TariffDirectory {

    private static final Logger logger = LoggerFactory.getLogger(TariffDirectory.class);

    public void addTariff(Long utilityTypeId, BigDecimal rate, String unit) {
        logger.info("Попытка добавления: Тариф [Тип услуги: {}, Ставка: {}, Единица: {}]",
                utilityTypeId, rate, unit);
        logger.info("Успешно добавлено: Тариф для типа услуги {} добавлен в справочник", utilityTypeId);
    }

    public void addTariffWithDescription(Long utilityTypeId, BigDecimal rate, String unit, String description) {
        logger.info("Попытка добавления: Тариф [Тип услуги: {}, Ставка: {}, Единица: {}, Описание: {}]",
                utilityTypeId, rate, unit, description);
        logger.info("Успешно добавлено: Тариф для типа услуги {} с описанием '{}' добавлен в справочник",
                utilityTypeId, description);
    }

    public void updateTariff(Long tariffId, BigDecimal newRate) {
        logger.info("Попытка изменения: Тариф [ID: {}, Новая ставка: {}]", tariffId, newRate);
        logger.info("Успешно изменено: Тариф [ID: {}] обновлен с новой ставкой {}", tariffId, newRate);
    }

    public void updateTariffFull(Long tariffId, BigDecimal newRate, String newUnit, String newDescription) {
        logger.info("Попытка изменения: Тариф [ID: {}, Новая ставка: {}, Новая единица: {}, Новое описание: {}]",
                tariffId, newRate, newUnit, newDescription);
        logger.info("Успешно изменено: Тариф [ID: {}] полностью обновлен", tariffId);
    }

    public void deleteTariff(Long tariffId) {
        logger.info("Попытка удаления: Тариф [ID: {}]", tariffId);
        logger.info("Успешно удалено: Тариф [ID: {}] удален из справочника", tariffId);
    }

    public void getTariffInfo(Long tariffId) {
        logger.info("Запрос информации: Тариф [ID: {}]", tariffId);
        logger.info("Информация получена: Данные тарифа [ID: {}] предоставлены", tariffId);
    }

    public void getAllTariffs() {
        logger.info("Запрс всех тарифов: Получение полного списка тарифов");
        logger.info("Список получен: Все тарифы из справочника предоставлены");
    }
}