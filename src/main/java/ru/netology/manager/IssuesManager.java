package ru.netology.manager;

import ru.netology.domain.Issues;
import ru.netology.domain.NotFoundException;
import ru.netology.repo.IssuesRepo;

public class IssuesManager {
    public IssuesRepo repo;

    {
        repo = new IssuesRepo();
    }

    public Issues[] items;

    {
        items = new Issues[0];
    }

    public IssuesManager(IssuesRepo repo) {
        this.repo = repo;
    }

    public Issues[] searchClose() {
        Issues[] result;
        result = new Issues[0];
        for (Issues issues : repo.getAll()) {
            if (issues.isClose() == true) {
                Issues[] tmp = new Issues[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }

    public Issues[] searchOpen() {
        Issues[] result;
        result = new Issues[0];
        for (Issues issues : repo.getAll()) {
            if (issues.isClose() == false) {
                Issues[] tmp = new Issues[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }

    public Issues[] searchByAuthor(String author) {
        Issues[] result;
        result = new Issues[0];
        for (Issues issues : repo.getAll()) {
            if (issues.getAuthor() == author) {
                Issues[] tmp = new Issues[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }

    public Issues[] searchBySuccesor(String successor) {
        Issues[] result;
        result = new Issues[0];
        for (Issues issues : repo.getAll()) {
            if (issues.getSuccessor().contains(successor)) {
                Issues[] tmp = new Issues[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }

    public void closeById(int id) {
        Issues[] result;
        result = new Issues[0];
        for (Issues issues : repo.getAll()) {

            if (issues.getId() == id && !issues.isClose()) {
                issues.setClose(true);
            }
        }
    }

    public void openById(int id) {
        Issues[] result;
        result = new Issues[0];
        for (Issues issues : repo.getAll()) {
            if (issues.getId() == id && issues.isClose()) {
                issues.setClose(false);
            }
        }
    }

    public Issues[] searchByLabel(String labelIssue) {
        Issues[] result;
        result = new Issues[0];
        for (Issues issues : repo.getAll()) {
            if (issues.getLabel().contains(labelIssue)) {
                Issues[] tmp = new Issues[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }
}

