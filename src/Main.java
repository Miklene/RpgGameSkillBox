
import Heroes.Attacker;
import Heroes.Healer;
import Heroes.Hero;
import Heroes.Tank;
import Monsters.Monster;
import Monsters.MonsterBerserk;
import Monsters.MonsterHunter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        int countOfWins = 0;
        for (int year = 1; year < 21; year++)
            countOfWins = oneYearOfWar();
        System.out.println("Из 20 раз команда героев одержала " + countOfWins + " побед");
        if (countOfWins < 10)
            System.out.println("Героям нужна другая тактика, попробуйте ещё!");
        else
            System.out.println("Герои готовы к реальному сражению, задание выполнено!");
    }

    public static int oneYearOfWar() {
        //Ниже приведен пример составления команды
        // Вы можете изменять состав команды, НО размер команды не должен быть более 5.
        List<Hero> goodTeam = new ArrayList<>();
        goodTeam.add(new Tank("Танк Пётр"));
        goodTeam.add(new Attacker("Убийца Ольга"));
        goodTeam.add(new Attacker("Убийца Траур"));
        goodTeam.add(new Healer("Монах Игнат"));
        goodTeam.add(new Healer("Монах Ирэна"));

        // Код ниже изменять нельзя!

        // Функция запускает симуляцию одного года сражений.
        // В цикле запускается 365 итераций (1 итерация = 1 день)
        // Каждый день каждый герой и монстр выбирают и совершают ОДНО действие.
        // Если монстры умирают - они пропадают из списка
        // Если умирают герои - цикл завершается - битва считается проигранной (возвращается 0)
        // Если герои выживают - битва считается выигранной (возвращается 1)

        //Утиная типизация питона позволяет запихнуть одного монстра в тиму.
        // В Java без общего родителя так не сделать. Так что данный функционал вырезан
        //if sum([isinstance(hero, (MonsterHunter, MonsterBerserk)) for hero in good_team]) > 1:
        //    print("В команде героев может быть только 1 монстр!")
        //    return 0

        List<String> evilNames = new ArrayList<>();
        evilNames.add("Абвыргл");
        evilNames.add("Мефисто");
        evilNames.add("Драник");
        evilNames.add("Диабло");
        evilNames.add("Пусечка");
        evilNames.add("Стаут");

        List<Monster> evilTeam = new ArrayList<>();
        evilTeam.add(new MonsterBerserk("Берсерк " + evilNames.get(random.nextInt(evilNames.size()))));
        evilTeam.add(new MonsterHunter("Рейнджер " + evilNames.get(random.nextInt(evilNames.size()))));

        for (int day = 1; day < 366; day++) {
            System.out.println("=".repeat(50) + "\nНачало дня №" + day + "\n" + "=".repeat(50));
            // В циклах у героев и монстров вызывается метод make_a_move, который должен выбирать и совершать одно действие
            // Для наглядности вы можете добавлять в каждое действие принты с подробностями (чтобы знать кто когда и что совершает)
            // При помощи этой информации вы сможете искать проблемы и ошибки в вашем коде и в конечном итоге это поможет вам улучшить стратегию
            System.out.println("\nКоманда добра:\n" + "-".repeat(50));
            for (Hero hero : goodTeam){
                hero.move(goodTeam, evilTeam);
            }

            System.out.println("\nКоманда зла:\n" + "-".repeat(50));
            for (Monster mob : evilTeam){
                mob.move(evilTeam, goodTeam);
            }

            System.out.println("Итоги дня сражений №" + day);

            // В итогах дня у каждого героя и каждого монстра вызывается метод __str__ который должен описывать их текущее состояние
            System.out.println("\nКоманда добра:\n" + "-".repeat(50));
            for (Hero hero : goodTeam){
                System.out.println(hero.toString());
            }

            System.out.println("\nКоманда зла:\n" + "-".repeat(50));
            for (Monster mob : evilTeam){
                System.out.println(mob.toString());
            }
            
            // Мёртвые монстры удаляются из списка
            for(Iterator<Monster> iter = evilTeam.listIterator(); iter.hasNext();){
                if(!iter.next().isAlive())
                    iter.remove();
            }
            // Новые монстры в чётные дни добавляются в список (но их не может быть больше 4)
            if((day % 2 == 0) && (evilTeam.size() < 4)) {
                List<Monster> newbornEvils = new ArrayList<>();
                newbornEvils.add(new MonsterBerserk("Берсерк" + evilNames.get(random.nextInt(evilNames.size()))));
                newbornEvils.add(new MonsterHunter("Рейнджер" + evilNames.get(random.nextInt(evilNames.size()))));
                evilTeam.add(newbornEvils.get(random.nextInt(newbornEvils.size())));
            }
            for (Hero hero : goodTeam) {
                if(!hero.isAlive()) {
                    System.out.println("Вы проиграли!");
                    return 0;
                }
            }
            System.out.println("Сражение продолжается!");
        }
        System.out.println("Вы одержали победу!");
        return 1;
    }

}