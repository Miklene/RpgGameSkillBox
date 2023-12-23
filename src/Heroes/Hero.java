package Heroes;

import Monsters.Monster;

import java.util.List;

public abstract class Hero {
    // Базовый класс, который не подлежит изменению
    // У каждого наследника будут атрибуты:
    // - Имя
    // - Здоровье
    // - Сила
    // - Жив ли объект
    // Каждый наследник будет уметь:
    // - Атаковать
    // - Получать урон
    // - Выбирать действие для выполнения
    // - Описывать своё состояние
    protected final int MAX_HP = 150;
    protected final int START_POWER = 10;

    protected String name;
    private float hp;
    private float power;
    private boolean isAlive;

    public Hero(String name) {
        this.name = name;
        hp = MAX_HP;
        power = START_POWER;
        isAlive = true;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String getName() {
        return name;
    }

    // Все наследники должны будут переопределять каждый метод базового класса (кроме геттеров/сеттеров)
    // Переопределенные методы должны вызывать методы базового класса (при помощи super).
    //Методы attack и __str__ базового класса можно не вызывать (т.к. в них нету кода).
    //Они нужны исключительно для наглядности.
    // Метод make_a_move базового класса могут вызывать только герои, не монстры.

    // Каждый наследник будет наносить урон согласно правилам своего класса
    protected abstract void attack(Monster target);

    public void takeDamage(float damage){
        // Каждый наследник будет получать урон согласно правилам своего класса
        // При этом у всех наследников есть общая логика, которая определяет жив ли объект.
        System.out.println("\t" + name +
                "Получил удар с силой равной = " + Math.round(damage) +
                ". Осталось здоровья - " + Math.round(getHp()));
        // Дополнительные принты помогут вам внимательнее следить за боем и изменять стратегию, чтобы улучшить выживаемость героев
        if (hp <= 0)
            isAlive = false;
    }

    public void move(List<Hero> friends, List<Monster> enemies){
        // С каждым днём герои становятся всё сильнее.
        setPower(getPower() + 0.1f);
    }

    //Каждый наследник должен выводить информацию о своём состоянии, чтобы вы могли отслеживать ход сражения
    @Override
    public abstract String toString();
}
