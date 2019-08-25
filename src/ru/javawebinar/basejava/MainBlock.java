package ru.javawebinar.basejava;

public class MainBlock {

    final static String lock1 = "lock1";
    final static String lock2 = "lock2";

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("Waiting " + lock1);
            synchronized (lock1) {
                System.out.println("Holding " + lock1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting " + lock2);
                synchronized (lock2) {
                    System.out.println("Holding " + lock2);
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        deadLock(lock1, lock2);
        deadLock(lock2, lock1);
    }

}
