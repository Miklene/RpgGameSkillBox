package Monsters;

import Heroes.Hero;

import java.util.List;

public abstract class Monster {
    protected final float MAX_HP = 150;
    protected final float START_POWER = 10;
    protected String name;
    private float hp;
    private float power;
    private boolean isAlive;

    public Monster(String name) {
        this.name = name;
        hp = MAX_HP;
        power = START_POWER;
        isAlive = true;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = Math.max(hp, 0);
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

    protected abstract void attack(Hero target);

    public void takeDamage(float damage){
        System.out.println("\t" + name + "Получил удар с силой равной = " + Math.round(damage) +
                ". Осталось здоровья - " + Math.round(getHp()));
        if(getHp() <= 0)
            isAlive = false;
    }

    public abstract void move(List<Monster> friends, List<Hero> enemies);

    @Override
    public String toString() {
        return "Имя: " + name + " | " + "HP: " + getHp();
    }
}
