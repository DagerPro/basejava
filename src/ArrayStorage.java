import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    //очистка массива
    void clear() {
        Arrays.fill(storage, null);
    }

    //сохранение резюме в массив
    void save(Resume r) {
        Resume[] temp = new Resume[10000];
        System.arraycopy(storage,0, temp, 1, storage.length - 1);
        temp[0] = r;
        System.arraycopy(temp,0, storage, 0, temp.length);
    }

    //получение резюме из массива
    Resume get(String uuid) {
        Resume requestResume = null;
        try {
            for (Resume r : storage) {
                if (r.uuid.equals(uuid)){
                    requestResume = r;
                    break;
                }
            }
        } catch (Exception e) {
        }
        return requestResume;
    }

    //удаление резюме из массива
    void delete(String uuid) {
        int index = 0;
        //Resume requestResume = null;
        for (Resume r : storage){
            if (r.uuid.equals(uuid)) {
                index = Arrays.asList(storage).indexOf(r);
                storage[index] = null;
                break;
            }
        }
        for (int b = index + 1; b < storage.length; b++){
            storage[b - 1] = storage[b];
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int index = 0;
        for(int i = 0; i < storage.length-1; i++){
            if (storage[i]== null){
                index = i;
                break;
            }
        }
        Resume[] newResume = new Resume[index];
        System.arraycopy(storage, 0, newResume, 0, index);
        return newResume;
    }

    int size() {
        int index = 0;
        for(int i = 0; i < storage.length-1; i++){
            if (storage[i]== null){
                index = i;
                break;
            }
        }
        return index;
    }
}
