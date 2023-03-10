package com.geekbrains.demoboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//     14.Предыдущий урок
//        Практическое задание
//        Упрощенный вариант домашнего задания:
//        1. Разберитесь с имеющимся кодом;
//        2. Реализуйте удаление товаров;
//        3. * Добавьте input поле и кнопку “Фильтровать” на странице. По нажатию на кнопку мы должны
//        увидеть страницу с товарами, содержащими в себе указанную подстроку. Сам фильтр при
//        этом может сброситься.
//        Обычный вариант домашнего задания:
//        1. Добавьте возможность редактировать название и цену товаров;
//        2. На странице с товарами добавить фильтры по цене (минимальная, максимальная), названию
//        (найти все товары в которых встречается указанная подстрока, про регистр можно не думать).
//        Фильтр это просто набор input’ов, куда можно вбить ограничения. Имейте ввиду, что после
//        выполнения фильтрации, мы должны увидеть страницу с отобранными товарами, и при этом
//        сами фильтры сброситься не должны.
//     15.Текущий урок
//        1. Перенесите всю работу с товарами на репозитории;
//        2. Добавьте пагинацию, с выводом на страницу по 10 товаров; (с фронтендом можете не
//        заморачиваться, просто сделайте ссылки на страницы 1, 2, 3, 4, 5)
//        3. * Перенесите фильтры на работу через Specification

@SpringBootApplication
public class DemoBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoBootApplication.class, args);
        System.out.println("git5");
    }
}
