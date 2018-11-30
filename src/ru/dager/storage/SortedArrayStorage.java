package ru.dager.storage;

import ru.dager.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{


    @Override
    public void save(Resume r) {
        int newIndex = this.getIndex(r.getUuid());
        if (newIndex < 0) storage[0] = r;
        else {
            for (int i = size; i < newIndex + 2; i--){
                storage[i] = storage[i - 1];
                storage[newIndex +1] = r;
            }
        }

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}