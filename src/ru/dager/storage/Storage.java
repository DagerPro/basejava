package ru.dager.storage;

import ru.dager.model.Resume;

public interface Storage {

    public void clear();

    public void update(Resume r);

    //сохранение резюме в массив
    public void save(Resume r);

    //получение резюме из массива
    public Resume get(String uuid);

    //удаление резюме из массива
    public void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll();

    public int size();

}
