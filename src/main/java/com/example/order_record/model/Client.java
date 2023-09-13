package com.example.order_record.model;/**/

import java.util.Objects;/**/

/*В данной модели определен класс Client, который представляет сущность "клиент". Класс имеет приватные поля id,
name, surname, address и phone, которые представляют различные характеристики клиента.

Класс содержит несколько конструкторов:

- Пустой конструктор, который не принимает аргументов.
- Конструктор, который принимает все поля клиента в качестве аргументов (id, name, surname, address, phone).

При создании объекта данного класса через этот конструктор происходит установка соответствующих значениям аргументов
полей объекта.

- Конструктор, который принимает значения полей name, surname, address и phone в качестве аргументов.
Этот конструктор не принимает аргумент id, и вместо этого значение данного поля будет установлено по умолчанию.*/
public class Client {

    private Integer id;
    private String name;
    private String surname;
    private String address;
    private String phone;

    /*Пустой конструктоп который определен внутри класса и не принимает аргументов. Используется для создания объекта
    Client без передачи значений полей.*/
    public Client() {
    }
    /**/
    public Client(Integer id, String name, String surname, String address, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
    }
    /*Далее определяем конструктор класса Client, который принимает значения для свойств name, surname, address и
    phone в качестве аргументов.

Конструктор используется для создания нового объекта Client с переданными значениями свойств.
Код `this.name = name;` означает, что значение свойства `name` объекта Client устанавливается равным значению,
переданному в конструктор в качестве аргумента `name`.

Аналогично для свойств `surname`, `address` и `phone`.
Таким образом, при создании нового объекта Client с использованием этого конструктора, переданные в аргументах
значения будут присваиваться соответствующим свойствам объекта.*/
    public Client(String name, String surname, String address, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
    }
    /*Затем определяем методы геттеров и сеттеров для четырех свойств объекта класса.
Каждый метод отвечает за получение или установку значения соответствующего свойства объекта.

- Метод `getId()` возвращает значение свойства `id`, тип которого является `Integer`.
- Метод `setId()` принимает значение типа `Integer` и устанавливает его в свойство `id` объекта.
- Метод `getName()` возвращает значение свойства `name`, тип которого является `String`.
- Метод `setName()` принимает значение типа `String` и устанавливает его в свойство `name` объекта.
- Метод `getSurname()` возвращает значение свойства `surname`, тип которого является `String`.
- Метод `setSurname()` принимает значение типа `String` и устанавливает его в свойство `surname` объекта.
- Метод `getAddress()` возвращает значение свойства `address`, тип которого является `String`.
- Метод `setAddress()` принимает значение типа `String` и устанавливает его в свойство `address` объекта.
- Метод `getPhone()` возвращает значение свойства `phone`, тип которого является `String`.
- Метод `setPhone()` принимает значение типа `String` и устанавливает его в свойство `phone` объекта.

Таким образом, данный код предоставляет механизм доступа к значениям свойств объекта класса Client.
Методы геттеров позволяют получить значения свойств, а методы сеттеров позволяют установить значения свойств объекта.
*/
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /*В данном коде определен метод `equals()`, который переопределяет метод сравнения объектов `equals()` из
класса `Object`. Внутри метода происходит сравнение текущего объекта с переданным объектом `o` на основе значений
свойств.

- Первым условием `if (this == o) return true` проверяется, если объекты `this` и `o` являются одной и той же ссылкой
 на объект, то возвращается значение `true`, что означает, что объекты равны.
- Затем условием `if (o == null || getClass() != o.getClass()) return false` проверяется, если объект `o` равен
`null` или объекты `this` и `o` принадлежат к разным классам, то возвращается значение `false`, что означает,
что объекты не равны.

- Далее внутри блока кода объект `o` приводится к типу `Client` с помощью оператора приведения типа `(Client)`.
Затем происходит сравнение значений свойств текущего объекта и объекта `o` с помощью метода `Objects.equals()`.

Если все значения свойств равны, то возвращается значение `true`, иначе возвращается значение `false`.
Таким образом, данный код реализует сравнение объектов типа `Client` на основе значений их свойств.

Если все значения свойств совпадают, то объекты считаются равными.
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals
                (surname, client.surname) && Objects.equals(address, client.address) && Objects.equals
                (phone, client.phone);
    }
    /*Затем определяем методы `hashCode()` и `toString()`, которые являются стандартными методами
переопределения класса `Object`.

- В методе `hashCode()` вызывается статический метод `hash()` из класса `Objects`, который принимает значения свойств `id`, `name`, `surname`, `address` и `phone`, и вычисляет хэш-код на основе этих значений. Возвращается полученный хэш-код.
- В методе `toString()` объявляется строка, которая представляет формат вывода данных объекта Client. Внутри строки указываются значения свойств `id`, `name`, `surname`, `address` и `phone`. Данные значения выводятся с использованием оператора `+` и метода `toString()` для преобразования значений в строку. Возвращается полученная строка.

Таким образом, метод `hashCode()` используется для вычисления хэш-кода объекта, а метод `toString()` возвращает строковое представление объекта Client.*/
    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, address, phone);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
