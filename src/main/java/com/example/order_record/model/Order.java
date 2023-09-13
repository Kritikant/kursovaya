package com.example.order_record.model;/**/

import java.time.LocalDateTime;/**/
import java.util.Objects;/**/

/*В данном коде определяем класс Order с приватными полями id, clientId, deliveryManId, orderDate, whenDeliver и note,
которые представляют различные характеристики заказа.

Класс Order также содержит два конструктора:
- Пустой конструктор `public Order() {}` : Конструктор не принимает аргументов и используется для создания объекта Order без передачи значений полей.
- Конструктор `public Order(Integer id, Integer clientId, Integer deliveryManId, LocalDateTime orderDate, LocalDateTime whenDeliver, String note)`: Конструктор принимает значения для всех полей id, clientId, deliveryManId, orderDate, whenDeliver и note в качестве аргументов. При создании объекта через этот конструктор, значения аргументов присваиваются соответствующим полям объекта Order.*/
public class Order {

    /**/
    private Integer id;
    private Integer clientId;
    private Integer deliveryManId;
    private LocalDateTime orderDate;
    private LocalDateTime whenDeliver;
    private String note;

    public Order() {
    }

    public Order(Integer id, Integer clientId, Integer deliveryManId, LocalDateTime orderDate,
                 LocalDateTime whenDeliver,
                 String note) {
        this.id = id;
        this.clientId = clientId;
        this.deliveryManId = deliveryManId;
        this.orderDate = orderDate;
        this.whenDeliver = whenDeliver;
        this.note = note;
    }
    /*В данном конструктор класса Order, который принимает значения для полей clientId, deliveryManId, orderDate,
whenDeliver и note в качестве аргументов. Конструктор используется для создания объекта Order с заданными значениями
полей.

Каждый параметр передается в конструктор и присваивается соответствующему полю объекта Order.

Таким образом, данный конструктор позволяет создавать объекты класса Order с заданными значениями полей clientId,
deliveryManId, orderDate, whenDeliver и note при их создании.*/
    public Order(Integer clientId, Integer deliveryManId, LocalDateTime orderDate, LocalDateTime whenDeliver,
                 String note) {
        this.clientId = clientId;
        this.deliveryManId = deliveryManId;
        this.orderDate = orderDate;
        this.whenDeliver = whenDeliver;
        this.note = note;
    }

    /*В этом разделе определяем методы доступа (геттеры и сеттеры) для полей класса Order. Каждый метод позволяет
получить значение определенного поля или установить новое значение этого поля.

- Методы геттеры, такие как `getId()`, `getClientId()`, `getDeliveryManId()`, `getOrderDate()`, `getWhenDeliver()` и
`getNote()`, используются для получения текущих значений соответствующих полей объекта Order.
- Методы сеттеры, такие как `setId(Integer id)`, `setClientId(Integer clientId)`,
`setDeliveryManId(Integer deliveryManId)`, `setOrderDate(LocalDateTime orderDate)`,
\`setWhenDeliver(LocalDateTime whenDeliver)` и `setNote(String note)`, используются для установки новых значений
соответствующих полей объекта Order.

Такие методы доступа позволяют получать и изменять значения полей объекта Order из других классов или компонентов
приложения, соблюдая принцип инкапсуляции. Это обеспечивает контроль доступа к полям класса и позволяет более
удобно работать с данными объекта Order.*/

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getClientId() {
        return clientId;
    }
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
    public Integer getDeliveryManId() {
        return deliveryManId;
    }
    public void setDeliveryManId(Integer deliveryManId) {
        this.deliveryManId = deliveryManId;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public LocalDateTime getWhenDeliver() {
        return whenDeliver;
    }
    public void setWhenDeliver(LocalDateTime whenDeliver) {
        this.whenDeliver = whenDeliver;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    /*В этом разделе определяем методы `equals()`, `hashCode()` и `toString()` для класса `Order`.


- Метод `equals()` переопределяет метод сравнения объектов `equals()` из класса `Object`. Он сравнивает текущий объект
`this` с переданным объектом `o`, чтобы определить, равны ли они. Внутри метода выполняются следующие действия:
- Сначала проверяется, являются ли объекты `this` и `o` одной и той же ссылкой на объект с помощью условия `if
(this == o) return true;`. Если это так, то возвращается значение `true`, что означает, что объекты равны.
- Затем проверяется, является ли объект `o` нулевым или относится к другому классу, чем `this`, с помощью условия
`if (o == null || getClass() != o.getClass()) return false;`. Если одно из условий выполняется, то возвращается
значение `false`, что означает, что объекты не равны.
- Далее объект `o` приводится к типу `Order` с помощью оператора приведения типа `(Order)`. Затем сравниваются значения
всех полей объектов `this` и `o` с помощью метода `Objects.equals()`. Если все значения полей равны, то возвращается
значение `true`, в противном случае - `false`.

- Метод `hashCode()` переопределяет метод вычисления хеш-кода объекта `hashCode()` из класса `Object`. Он вычисляет
хеш-код объекта на основе значений его полей. Внутри метода вызывается `Objects.hash()` со значениями всех полей объекта
 `this` для вычисления хеш-кода.

- Метод `toString()` переопределяет метод преобразования объекта в строку `toString()` из класса `Object`. Он формирует
строковое представление объекта `Order`, включая значения его полей. Внутри метода создается строка, к которой
добавляются значения полей с помощью оператора конкатенации `+`. Возвращается полученная строка.

Такие методы `equals()`, `hashCode()` и `toString()` позволяют определить сравнение объектов `Order`, вычислить их
хеш-коды и получить строковое представление объекта для отладки, логирования или отображения информации о заказе в
 удобочитаемом виде.
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(clientId, order.clientId) && Objects.equals(deliveryManId, order.deliveryManId) && Objects.equals(orderDate, order.orderDate) && Objects.equals(whenDeliver, order.whenDeliver) && Objects.equals(note, order.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, deliveryManId, orderDate, whenDeliver, note);
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", deliveryManId=" + deliveryManId +
                ", orderDate=" + orderDate +
                ", whenDeliver=" + whenDeliver +
                ", note='" + note + '\'' +
                '}';
    }
}
