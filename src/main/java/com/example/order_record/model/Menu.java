package com.example.order_record.model;/**/

import java.util.Objects;/**/

/*В данном коде определен класс Menu с приватными полями id, foodName, weight и price, которые представляют различные
характеристики меню.

Класс Menu также содержит конструкторы и методы доступа (геттеры и сеттеры) для этих полей:
- Пустой конструктор `public Menu() {}` : Конструктор не принимает аргументов и используется для создания объекта Menu
без передачи значений полей.
- Конструктор `public Menu(Integer id, String foodName, Integer weight, Integer price)`: Конструктор принимает значения
для всех полей id, foodName, weight и price в качестве аргументов. При создании объекта через этот конструктор, значения
 аргументов присваиваются соответствующим полям объекта Menu.
- Конструктор `public Menu(String foodName, Integer weight, Integer price)`: Конструктор принимает значения для полей
foodName, weight и price в качестве аргументов. При создании объекта через этот конструктор, значения аргументов
присваиваются соответствующим полям объекта Menu. Поле id не инициализируется в данном конструкторе и будет иметь
значение null.

Также класс Menu содержит методы доступа (геттеры и сеттеры) для каждого поля, которые позволяют получать значения и
устанавливать новые значения для полей объекта Menu.

Эти методы доступа позволяют получать и изменять значения полей объекта Menu из других классов или компонентов
приложения.

 */
public class Menu {

    private Integer id;
    private String foodName;
    private Integer weight;
    private Integer price;


    public Menu() {
    }

    public Menu(Integer id, String foodName, Integer weight, Integer price) {
        this.id = id;
        this.foodName = foodName;
        this.weight = weight;
        this.price = price;
    }


    public Menu(String foodName, Integer weight, Integer price) {
        this.foodName = foodName;
        this.weight = weight;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    /*Далее определяем методы доступа (геттеры и сеттеры) для полей weight и price класса Menu.
- Метод `getWeight()` возвращает значение поля `weight`. Он используется для получения значения веса блюда.
- Метод `setWeight(Integer weight)` устанавливает новое значение веса блюда на основе переданного аргумента `weight`.
 Он используется для изменения значения веса блюда.
- Метод `getPrice()` возвращает значение поля `price`. Он используется для получения значения цены блюда.
- Метод `setPrice(Integer price)` устанавливает новое значение цены блюда на основе переданного аргумента `price`.
Он используется для изменения значения цены блюда.

Данные методы доступа позволяют получать и изменять значения полей weight и price объекта Menu из других классов или
компонентов приложения, соблюдая принцип инкапсуляции. Это позволяет более удобно работать с данными объекта Menu и
обеспечивает контроль доступа к полям класса.
Также в коде определен метод `equals()`, который переопределяет метод сравнения объектов `equals()` из класса `Object`
для сравнения объектов класса Menu. Он сравнивает значения всех полей объекта Menu с помощью метода `Objects.equals()`.
Если все значения полей равны, метод возвращает `true`, в противном случае - `false`.
Метод `equals()` позволяет сравнивать два объекта Menu на основе их полей и используется для выполнения операций
сравнения и поиска объектов Menu в различных структурах данных, таких как списки или множества.*/

    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) && Objects.equals(foodName, menu.foodName) && Objects.equals(weight, menu.weight) && Objects.equals(price, menu.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, foodName, weight, price);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }
}
