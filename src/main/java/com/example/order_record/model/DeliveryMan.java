package com.example.order_record.model;/**/

import java.util.Objects;/**/

/*В данном коде определен класс DeliveryMan, который представляет сущность "доставщик". Класс имеет приватные поля
id, name, surname и phone, которые представляют характеристики доставщика.

Класс содержит два конструктора:
- Пустой конструктор, который не принимает аргументов.
- Конструктор, который принимает значения для всех полей доставщика в качестве аргументов (id, name, surname, phone).
 При создании объекта через этот конструктор, значения аргументов присваиваются соответствующим свойствам объекта
 доставщика.

Таким образом, эти конструкторы позволяют создавать объекты класса DeliveryMan с определенными значениями
характеристик, что упрощает инициализацию объекта при его создании.*/
public class DeliveryMan {

    private Integer id;
    private String name;
    private String surname;
    private String phone;

    public DeliveryMan() {
    }


    public DeliveryMan(Integer id, String name, String surname, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    /*Далее определены конструктор с параметрами и методы геттеров и сеттеров для класса DeliveryMan.

Конструктор с параметрами `DeliveryMan(String name, String surname, String phone)` позволяет создавать объекты класса
DeliveryMan, принимая значения для полей name, surname и phone в качестве аргументов конструктора. Значения этих
аргументов присваиваются соответствующим полям объекта DeliveryMan.

Методы геттеров и сеттеров используются для получения и установки значения свойств объекта DeliveryMan.
Например, метод `getName()` возвращает значение поля name, а метод `setName(String name)` устанавливает значение
поля name на переданное значение.

Таким образом, данный код определяет конструктор с параметрами и методы геттеров и сеттеров для доступа к значениям
полей объекта DeliveryMan.

Дальше определяем метод `toString()` для класса `DeliveryMan`. Данный метод переопределяет метод `toString()` из класса
`Object` для формирования строкового представления объекта типа `DeliveryMan`.

Внутри метода создается строка, которая состоит из значений свойств объекта `DeliveryMan` (id, name, surname, phone), а
также текстовых значений соответствующих свойств.

Затем возвращается сформированная строка.

Таким образом, данный метод позволяет получить строковое представление объекта `DeliveryMan`, которое может
использоваться для отладки, логирования или отображения информации о доставщике в удобочитаемом виде.

*/

    public DeliveryMan(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /*Дальше метод `equals()`, который переопределяет метод сравнения объектов `equals()` из класса `Object`.
Данный метод служит для сравнения текущего объекта с переданным объектом `o` на основе значений свойств.

- Первым условием `if (this == o) return true` проверяется, если объекты `this` и `o` являются одной и той же ссылкой
на объект, то возвращается значение `true`, что означает, что объекты равны.

- Затем условием `if (o == null || getClass() != o.getClass()) return false` проверяется, если объект `o` равен `null`
или объекты `this` и `o` принадлежат к разным классам, то возвращается значение `false`, что означает, что объекты не
равны.

- Далее внутри блока кода объект `o` приводится к типу `DeliveryMan` с помощью оператора приведения типа `(DeliveryMan).
 Затем происходит сравнение значений свойств текущего объекта и объекта `o` с помощью метода `Objects.equals()`.
 Если все значения свойств равны, то возвращается значение `true`, иначе возвращается значение `false`.

Таким образом, данный код реализует сравнение объектов типа `DeliveryMan` на основе значений их свойств. Если все
значения свойств совпадают, то объекты считаются равными.*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryMan that = (DeliveryMan) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(phone, that.phone);
    }
    /*Дальше переопределяем метод `hashCode()` с использованием метода `Objects.hash()`, который вычисляет хеш-код на
    основе значений свойств `id`, `name`, `surname` и `phone` объекта `DeliveryMan`. Возвращается полученный хеш-код.


- Метод `toString()` переопределяем для формирования строки с информацией о свойствах объекта `DeliveryMan`. Внутри
метода создается строка, в которую добавляются значения свойств `id`, `name`, `surname` и `phone`. Значения этих свойств
 преобразуются в строковое представление с использованием метода `toString()`. Возвращается полученная строка.



Таким образом, метод `hashCode()` используется для вычисления хеш-кода объекта, а метод `toString()` возвращает
строковое представление объекта `DeliveryMan`.*/

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phone);
    }


    @Override
    public String toString() {
        return "DeliveryMan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
