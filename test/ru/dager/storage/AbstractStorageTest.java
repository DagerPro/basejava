package ru.dager.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.dager.exeption.ExistStorageException;
import ru.dager.exeption.NotExistStorageException;
import ru.dager.model.Resume;

public abstract class AbstractStorageTest {

    protected Storage storage;


    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1, "name1" ));
        storage.save(new Resume(UUID_2, "name2" ));
        storage.save(new Resume(UUID_3, "name3" ));
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
    public void update() {
        Resume newResume = new Resume(UUID_1, "name1" );
        storage.update(newResume);
        Assert.assertTrue(newResume == storage.get(UUID_1));
    }

    @Test
    public void save() {
        final String UUID_4 = "uuid4";
        storage.save(new Resume(UUID_4, "name4" ));
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(new Resume(UUID_4, "name4"), storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_1, "name1" ));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid1");
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        for (Resume r : storage.getAll()) {
            Assert.assertNotNull(r);
        }

        Resume[] array = storage.getAll();
        Assert.assertEquals(3, array.length);
        Assert.assertEquals(new Resume(UUID_1, "name1"), array[0]);
        Assert.assertEquals(new Resume(UUID_2, "name2"), array[1]);
        Assert.assertEquals(new Resume(UUID_3, "name3"), array[2]);
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_1, "name1" ), storage.get(UUID_1));
        Assert.assertEquals(new Resume(UUID_2, "name2"), storage.get(UUID_2));
        Assert.assertEquals(new Resume(UUID_3, "name3"), storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}