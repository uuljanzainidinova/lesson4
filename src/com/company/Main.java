package com.company;

import java.util.Random;

public class Main {

    public static int roundNumber = 1;
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static String[] heroesAttackType = {
            "Physical", "Magical", "Kinetic", "Medic", "Lucky", "Berserk", "Thor", "Golem"};
    public static int[] heroesHealth = {260, 270, 250, 240, 200, 170, 180, 120};
    public static int[] heroesDamage = {15, 20, 25, 0, 30, 14, 45, 23};

    public static Random random = new Random();


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {

        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefence);
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
                && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
   /*     boolean isHealingNeeded = true;
        for (int i = 0; i < 2;) {




            return true;

        }*/
        return allHeroesDead;

    }

    public static void round() {
        System.out.println("ROUND: " + roundNumber);
        chooseBossDefence();
        bossHits();
        Lucky();
        medic();
        heroesHit();
        Thor();
        Berserk();
        Golem();
        printStatistics();
        roundNumber++;
    }

    public static void medic() {
        int healPoint = random.nextInt(30) + 10;
        int randomHero = random.nextInt(heroesAttackType.length);

        if (randomHero == 3) {
            medic();
        } else if (heroesHealth[randomHero] < 100 && heroesHealth[randomHero] > 0) {

            heroesHealth[randomHero] = heroesHealth[randomHero] + healPoint; // 50 = 50 + healPoint( 10 - 40) -> max= 90
            System.out.println(heroesAttackType[randomHero] + " healed for: " + healPoint);

        }


    }

    public static void Lucky() {
        Random random = new Random();
        boolean trueOrFalse = random.nextBoolean();
        if (heroesHealth[4] > 0) {
            if (!(trueOrFalse)) {
                heroesHealth[4] = heroesHealth[4] + bossDamage;
                if (heroesHealth[4] > 200) {
                    heroesHealth[4] = heroesHealth[4] - bossDamage;

                }
            } else if (trueOrFalse) {
                heroesHealth[4] = heroesHealth[4];
            }
        }
    }

    public static void Berserk() {
        Random randoms = new Random();
        int someDamage = 21;
        int some = random.nextInt(3) + 1;
        switch (some) {
            case 1:
                if (heroesHealth[5] > 0 && bossHealth > 0) {
                    heroesDamage[5] = (heroesDamage[5] + bossDamage) - someDamage;
                    System.out.println("Berserk used his superpower");
                }
            case 2:
                bossDamage = 50;

            case 3:
                bossDamage = 50;
        }


    }

    public static void Thor() {
        Random random = new Random();
        boolean trueFalse = random.nextBoolean();
        if (heroesHealth[6] > 0) {
            if (!(trueFalse)) {
                bossDamage = 0;
                System.out.println("Thor used his superpower!");
            } else if (trueFalse) {
                bossDamage = 50;

            }

        }
    }

    public static void Golem() {
        int someNum = new Random().nextInt(3) + 1;
        switch (someNum) {
            case 1:
                if (heroesHealth[7] > 0) {
                    for (int i = 0; i < heroesHealth.length; i++) {
                        heroesHealth[i] = heroesHealth[i] + 12;

                    }
                    heroesHealth[7] = heroesHealth[7] - 96;
                    System.out.println("Galem used his superpower");
                }
                break;
            case 2:
                for (int i = 0; i < heroesHealth.length; i++) {
                    heroesHealth[i] = heroesHealth[i] + 0;
                }
                break;
            case 3:
                for (int i = 0; i < heroesHealth.length; i++) {
                    heroesHealth[i] = heroesHealth[i];

                }
                break;
        }
    }

        public static void heroesHit () {
            for (int i = 0; i < heroesDamage.length; i++) {
                if (heroesHealth[i] > 0 && bossHealth > 0) {
                    if (heroesAttackType[i] == bossDefence) {
                        Random random = new Random();
                        int coeff = random.nextInt(11); // 0,1,2,3,4,5,6,7,8
                        if (bossHealth - heroesDamage[i] * coeff < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i] * coeff;
                        }
                        System.out.println("Critical Damage "
                                + heroesDamage[i] * coeff);
                    } else {
                        if (bossHealth - heroesDamage[i] < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i];
                        }
                    }
                }
            }
        }

        public static void bossHits () {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0) {
                    if (heroesHealth[i] - bossDamage < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                    }
                }
            }
        }

        public static void printStatistics () {
            System.out.println("________________");
            System.out.println("Boss Health: " + bossHealth +
                    "; Boss Damage: " + bossDamage);
            for (int i = 0; i < heroesHealth.length; i++) {
                System.out.println(
                        heroesAttackType[i] + " Health: "
                                + heroesHealth[i] +
                                "; " + heroesAttackType[i] + " Damage: "
                                + heroesDamage[i]);
            }
            System.out.println("________________");
        }
    }
