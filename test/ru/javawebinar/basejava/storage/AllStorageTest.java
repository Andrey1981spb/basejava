package ru.javawebinar.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        MapStorageTest.class,
        ListStorageTest.class,
        MapResumeStorageTest.class,
        ObjectStreamStorageTest.class
})
public class AllStorageTest {

}

