package alex.example.gotit.view.adapter;

import java.util.List;

/**
 * Created by SEV_USER on 3/19/2016.
 */
public interface Retrofit<T> {
    void addObject(T o);

    void addAll(List<T> objects);

    void removeObject(T o);

    void removeObjectAtPos(int position);

    void removeAll(List<T> objects);

    void clear();
}
