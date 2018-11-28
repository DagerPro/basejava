package ru.dager.storage;

import ru.dager.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    //очистка массива
    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("ru.dager.model.Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;
        }
    }

    //сохранение резюме в массив
    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    //получение резюме из массива
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index == -1){
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    //удаление резюме из массива
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] result = new Resume[size];
        System.arraycopy(storage, 0, result, 0, size);
        return result;
    }

    public int size() {
        return size;
    }

    protected int getIndex(String uuid){
        for(int i = 0; i < size; i++){
            if (uuid == storage[i].getUuid()){
                return i;
            }
        }
        return -1;
    }
}
