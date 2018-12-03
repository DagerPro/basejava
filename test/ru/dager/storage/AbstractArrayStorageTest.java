package ru.dager.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.dager.exeption.NotExistStorageException;
import ru.dager.model.Resume;

public abstract class AbstractArrayStorageTest {

    private Storage storage;


    public AbstractArrayStorageTest(Storage storage){
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update(){

    }

    @Test
    public void save() {
        final String UUID_4 = "uuid4";
        storage.save(new Resume(UUID_4));
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void delete() {
        storage.delete("uuid1");
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void getAll() {
        for(Resume r : storage.getAll()){
            Assert.assertNotNull(r);
        }
    }

    @Test
    public void get() {
        Assert.assertSame(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}