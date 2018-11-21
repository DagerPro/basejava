import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    //очистка массива
    void clear() {
        Arrays.fill(storage, null);
    }

    //сохранение резюме в массив
    void save(Resume r) {
        for (int i = 0; i < storage.length; i++){
            if (storage[i] == null){
                storage[i] = r;
                size++;
                break;
            }
        }
    }

    //получение резюме из массива
    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid))
                return storage[i];
        }
        return null;
    }

    //удаление резюме из массива
    void delete(String uuid) {
        int index = 0;
        for (int i = 0; i < storage.length; i++){
            if(storage[i].uuid.equals(uuid)){
                storage[i] = null;
                index = i;
                break;
            }
        }
        // сдвиг всех элекментов на один элемент назад
        for (int i = index + 1; i < storage.length; i++){
            storage[i - 1] = storage[i];
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
        return size;
    }
}
