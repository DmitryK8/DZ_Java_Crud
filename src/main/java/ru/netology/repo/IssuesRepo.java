package ru.netology.repo;

import ru.netology.domain.Issues;
import ru.netology.domain.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssuesRepo extends Issues {
    @lombok.Setter
    @lombok.Getter
    private List<Issues> items;

    {
        items = new ArrayList<>();
    }

    public Collection<Issues> getAll() {
        return items;
    }

    public Issues getById(int id) {
        for (Issues item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public boolean add(Issues item) {
        return items.add(item);
    }

    public boolean remove(Issues item) {
        return items.remove(item);
    }

    public boolean saveAll(Collection<? extends Issues> items) {
        return this.items.addAll(items);
    }

    public boolean removeAll(Collection<? extends Issues> items) {
        return this.items.removeAll(items);
    }


    public Issues findById(int id) {
        for (Issues item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        Issues[] tmp;
        tmp = new Issues[items.toArray().length - 1];
        int index = 0;
        for (Issues item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = List.of(tmp);
    }
}






