package Monsters;

import Heroes.Hero;

import java.util.List;
import java.util.Random;

public class MonsterHunter extends Monster {
    private int potions;
    private final Random random;

    public MonsterHunter(String name) {
        super(name);
        potions = 10;
        random = new Random();
    }

    @Override
    protected void attack(Hero target) {
        target.takeDamage(getPower() + (10 - potions));
    }

    @Override
    public void takeDamage(float damage) {
        setHp(getHp() - damage);
        if (random.nextInt(10) + 1 == 1)
            potions--;
        super.takeDamage(damage);
    }

    private void givePotion(Monster target) {
        potions--;
        target.setHp(getHp() + getPower());
    }

    @Override
    public void move(List<Monster> friends, List<Hero> enemies) {
        System.out.print(name);
        Monster targetOfPotion = friends.get(0);
        float minHealth = targetOfPotion.getHp();
        for (Monster friend : friends) {
            if (friend.getHp() < minHealth) {
                targetOfPotion = friend;
                minHealth = targetOfPotion.getHp();
            }
        }
        if (minHealth < 60 && potions > 0) {
            System.out.println("Исцеляю " + targetOfPotion.name);
            givePotion(targetOfPotion);
        } else {
            if (enemies.isEmpty())
                return;
            System.out.println("Атакую ближнего - " + enemies.get(0).getName());
            attack(enemies.get(0));
        }
        System.out.println("\n");
    }
}
