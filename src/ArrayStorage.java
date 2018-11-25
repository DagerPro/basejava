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
        size = 0;
    }

    public void update(Resume r){
        for (int i = 0; i < size; i++){
            if (!storage[i].uuid.equals(r.uuid))
                System.out.println("ERROR: resume is absent");
        }
    }

    //сохранение резюме в массив
    void save(Resume r) {
        if (size == 10000) {
            System.out.println("ERROR: storage is full");
            return;
        }
        for (int i = 0; i <= size; i++){
            if ((storage[i] == null) && (!(storage[i].uuid.equals(r.uuid)))){
                storage[i] = r;
                break;
            }
            else System.out.println("ERROR: resume allready exist");
        }
        size++;
    }

    //получение резюме из массива
    Resume get(String uuid) {
        for (int i = 0; i <= size; i++){
            if (storage[i].uuid.equals(uuid)){
                return storage[i];
            }
            else if (i == size){
                System.out.println("ERROR: resume is absent");
            }
        }
        return null;
    }

    //удаление резюме из массива
    void delete(String uuid){
        for (int i = 0; i <= size; i++){
            if(storage[i].uuid.equals(uuid)){
                storage[i] = storage[size -1];
                break;
            }
            else if (i == size){
                System.out.println("ERROR: resume is absent");
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
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

    int size() {
        return size;
    }
}
