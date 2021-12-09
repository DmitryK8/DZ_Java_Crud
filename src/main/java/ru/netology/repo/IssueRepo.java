package ru.netology.repo;

import ru.netology.domain.Issue;
import ru.netology.domain.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueRepo extends Issue {
    @lombok.Setter
    @lombok.Getter
    private List<Issue> items;

    {
        items = new ArrayList<>();
    }

    public Collection<Issue> getAll() {
        return items;
    }

    public Issue getById(int id) {
        for (Issue item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public boolean add(Issue item) {
        return items.add(item);
    }

    public boolean remove(Issue item) {
        return items.remove(item);
    }

    public boolean saveAll(Collection<? extends Issue> items) {
        return this.items.addAll(items);
    }

    public boolean removeAll(Collection<? extends Issue> items) {
        return this.items.removeAll(items);
    }


    public Issue findById(int id) {
        for (Issue item : items) {
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
        Issue[] tmp;
        tmp = new Issue[items.toArray().length - 1];
        int index = 0;
        for (Issue item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = List.of(tmp);
    }
}






