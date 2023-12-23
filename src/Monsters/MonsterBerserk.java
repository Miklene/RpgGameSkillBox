package Monsters;

import Heroes.Hero;

import java.util.List;
import java.util.Random;

public class MonsterBerserk extends Monster {

    private float madness;
    private final  Random random;

    public MonsterBerserk(String name) {
        super(name);
        madness = 1;
        random = new Random();
    }

    @Override
    protected void attack(Hero target) {
        target.takeDamage(getPower() * madness);
        madness += 0.1f;
    }

    @Override
    public void takeDamage(float damage) {
        setHp(getHp() - (damage * (madness / 2f)));
        if ((getHp() < 50))
            madness *= 2;
        super.takeDamage(damage);
    }

    @Override
    public void move(List<Monster> friends, List<Hero> enemies) {
        System.out.print(name + " ");
        madness = Math.min(madness, 4);
        if (enemies.isEmpty())
            return;
        if (madness < 3) {
            System.out.println("Атакую того, кто стоит ближе -" + enemies.get(0).getName());
            attack(enemies.get(0));
        } else {
            Hero target = enemies.get(random.nextInt(enemies.size()));
            System.out.println("BERSERK MODE!!! Уровень безумия - " + madness + " Случайно атакую -" + target.getName());
            System.out.println();
            attack(target);
        }
        System.out.println("\n");
    }
}

