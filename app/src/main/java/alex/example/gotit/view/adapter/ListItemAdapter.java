package alex.example.gotit.view.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class ListItemAdapter<T> extends BaseAdapter implements Retrofit<T> {
    protected LayoutInflater layoutInflater;
    protected List<T> items = new ArrayList<>();

    public ListItemAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewAt(position, convertView, parent);
    }

    protected abstract View getViewAt(int position, View convertView, ViewGroup parent);

    @Override
    public void addObject(T o) {
        items.add(o);

        notifyDataSetChanged();
    }

    @Override
    public void addAll(List<T> objects) {
        for (T o : objects) {
            items.add(o);
        }

        notifyDataSetChanged();
    }

    @Override
    public void removeObject(T o) {
        items.remove(o);

        notifyDataSetChanged();
    }

    @Override
    public void removeObjectAtPos(int position) {
        items.remove(position);

        notifyDataSetChanged();
    }

    @Override
    public void removeAll(List<T> objects) {
        for (T o : objects) {
            items.remove(o);
        }

        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        items.clear();

        notifyDataSetChanged();
    }
}
