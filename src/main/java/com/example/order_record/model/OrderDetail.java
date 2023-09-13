package com.example.order_record.model;/**/

import java.util.Objects;/**/

/*В данном разделе определен класс OrderDetail с приватными полями id, orderId, menuId и servingCount, которые
представляют различные характеристики деталей заказа.

В классе OrderDetail определены три конструктора:
- Пустой конструктор public OrderDetail() : Конструктор без аргументов, используется для создания объекта
OrderDetail без передачи значений полей.
- Конструктор public OrderDetail(Integer id, Integer orderId, Integer menuId, Integer servingCount) :
Конструктор, принимает значения для всех полей id, orderId, menuId и servingCount в качестве аргументов.
При создании объекта через этот конструктор, значения аргументов присваиваются соответствующим полям объекта
OrderDetail.
- Конструктор public OrderDetail(Integer orderId, Integer menuId, Integer servingCount) : Конструктор, принимает
значения для полей orderId, menuId и servingCount в качестве аргументов. При создании объекта через этот конструктор,
значение аргументов присваивается соответствующим полям объекта OrderDetail. Поле id не инициализируется в данном
конструкторе и будет иметь значение null.

Таким образом, эти конструкторы позволяют создавать объекты класса OrderDetail с различными комбинациями передаваемых
значений полей в зависимости от потребностей приложения.*/

public class OrderDetail {

    private Integer id;
    private Integer orderId;
    private Integer menuId;
    private Integer servingCount;

    public OrderDetail() {
    }
    public OrderDetail(Integer id, Integer orderId, Integer menuId, Integer servingCount) {
        this.id = id;
        this.orderId = orderId;
        this.menuId = menuId;
        this.servingCount = servingCount;
    }
    public OrderDetail(Integer orderId, Integer menuId, Integer servingCount) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.servingCount = servingCount;
    }

    /*Здесь определяем методы доступа (геттеры и сеттеры) для полей класса OrderDetail. Каждый метод позволяет
    получить значение определенного поля или установить новое значение этого поля.

- Методы геттеры, такие как `getId()`, `getOrderId()`, `getMenuId()` и `getServingCount()`, используются для
получения текущих значений соответствующих полей объекта OrderDetail.

- Методы сеттеры, такие как `setId(Integer id)`, `setOrderId(Integer orderId)`, `setMenuId(Integer menuId)` и
`setServingCount(Integer servingCount)`, используются для установки новых значений соответствующих полей объекта
OrderDetail.

Такие методы доступа позволяют получать и изменять значения полей объекта OrderDetail из других классов или
компонентов приложения, соблюдая принцип инкапсуляции. Это обеспечивает контроль доступа к полям класса и позволяет
более удобно работать с данными объекта OrderDetail.
*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getMenuId() {
        return menuId;
    }
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    public Integer getServingCount() {
        return servingCount;
    }
    public void setServingCount(Integer servingCount) {
        this.servingCount = servingCount;
    }

    /*В этом разделе Метод `equals()` переопределяет метод сравнения объектов `equals()` из класса `Object`.
    Он сравнивает текущий объект `this` с переданным объектом `o`, чтобы определить, равны ли они. Внутри метода
    выполняются следующие действия:
- Сначала проверяется, являются ли объекты `this` и `o` одной и той же ссылкой на объект с помощью условия
`if (this == o) return true;`. Если это так, то возвращается значение `true`, что означает, что объекты равны.
- Затем проверяется, является ли объект `o` нулевым или относится к другому классу, чем `this`, с помощью условия
`if (o == null || getClass() != o.getClass()) return false;`. Если одно из условий выполняется, то возвращается
значение `false`, что означает, что объекты не равны.
- Далее объект `o` приводится к типу `OrderDetail` с помощью оператора приведения типа `(OrderDetail)`.
Затем сравниваются значения всех полей объектов `this` и `o` с помощью метода `Objects.equals()`. Если все значения
полей равны, то возвращается значение `true`, в противном случае - `false`.

- Метод `hashCode()` переопределяет метод вычисления хеш-кода объекта `hashCode()` из класса `Object`. Он вычисляет
хеш-код объекта на основе значений его полей. Внутри метода вызывается `Objects.hash()` со значениями всех полей
объекта `this` для вычисления хеш-кода.

- Метод `toString()` переопределяет метод преобразования объекта в строку `toString()` из класса `Object`. Он формирует
строковое представление объекта `OrderDetail`, включая значения его полей. Внутри метода создается строка, в которую
добавляются значения полей с помощью оператора конкатенации `+`. Возвращается полученная строка.

Такие методы `equals()`, `hashCode()` и `toString()` позволяют определить сравнение объектов `OrderDetail`, вычислить
их хеш-коды и получить строковое представление объекта для отладки, логирования или отображения информации о деталях
заказа в удобочитаемом виде.*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId) && Objects.equals(menuId, that.menuId) && Objects.equals(servingCount, that.servingCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, menuId, servingCount);
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", menuId=" + menuId +
                ", servingCount=" + servingCount +
                '}';
    }
}
