package ru.javawebinar.basejava;

public class MainBlock implements Runnable {

    static ClassA classA = new ClassA();
    static ClassB classB = new ClassB();

    MainBlock() {
        Thread.currentThread().setName("Главный поток");
        Thread thread1 = new Thread(this, "Второй поток");
        thread1.start();
        classA.processA(classB);
    }

    public static void main(String[] args) {
        new MainBlock();
    }

    @Override
    public void run() {
        classB.processB(classA);
    }
}
