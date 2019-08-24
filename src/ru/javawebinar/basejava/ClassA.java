package ru.javawebinar.basejava;

public class ClassA {

    synchronized void processA(ClassB classB) {

        String name = Thread.currentThread().getName();
        System.out.println(name + " вошел в метод processA");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Класс ClassA прерван");
        }
        classB.crewB();
    }

    synchronized void crewA() {
        System.out.println("crewA is realized");
    }

}
