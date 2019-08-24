package ru.javawebinar.basejava;

public class ClassB {

    synchronized void processB(ClassA classA) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " вошел в метод processB");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Класс ClassB прерван");
        }
        classA.crewA();
    }

    synchronized void crewB() {
        System.out.println("crewB is realized");
    }

}
