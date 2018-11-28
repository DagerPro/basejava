package ru.dager.webapp.storage;

import ru.dager.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    //очистка массива
    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("ru.dager.webapp.model.Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;
        }
    }

    //сохранение резюме в массив
    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("ru.dager.webapp.model.Resume " + r.getUuid() + " already exist");
        } else if (size >= storage.length) {
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
            System.out.println("ru.dager.webapp.model.Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    //удаление резюме из массива
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ru.dager.webapp.model.Resume " + uuid + " not exist");
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
        int index = 0;
        for(int i = 0; i < size; i++){
            if (storage[i]== null){
                index = i;
                break;
            }
        }
        Resume[] newResume = new Resume[index];
        System.arraycopy(storage, 0, newResume, 0, index);
        return newResume;
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid){
        for(int i = 0; i < size; i++){
            if (uuid == storage[i].getUuid()){
                return i;
            }
        }
        return -1;
    }
}
