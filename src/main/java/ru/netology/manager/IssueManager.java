package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repo.IssueRepo;

public class IssueManager {
    public IssueRepo repo;

    {
        repo = new IssueRepo();
    }

    public Issue[] items;

    {
        items = new Issue[0];
    }

    public IssueManager(IssueRepo repo) {
        this.repo = repo;
    }

    public Issue[] searchClose() {
        Issue[] result;
        result = new Issue[0];
        for (Issue issue : repo.getAll()) {
            if (issue.isClose()) {
                Issue[] tmp = new Issue[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issue;
                result = tmp;
            }
        }
        return result;
    }

    public Issue[] searchOpen() {
        Issue[] result;
        result = new Issue[0];
        for (Issue issue : repo.getAll()) {
            if (!issue.isClose()) {
                Issue[] tmp = new Issue[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issue;
                result = tmp;
            }
        }
        return result;
    }

    public Issue[] searchByAuthor(String list) {
        Issue[] result;
        result = new Issue[0];
        for (Issue issue : repo.getAll()) {
            if (issue.getAuthor().equals(list)) {
                Issue[] tmp = new Issue[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issue;
                result = tmp;
            }
        }
        return result;
    }

    public Issue[] searchBySuccesor(String list) {
        Issue[] result;
        result = new Issue[0];
        for (Issue issue : repo.getAll()) {
            if (issue.getSuccessor().contains(list)) {
                Issue[] tmp = new Issue[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issue;
                result = tmp;
            }
        }
        return result;
    }

    public void closeById(int id) {
        Issue[] result;
        result = new Issue[0];
        for (Issue issue : repo.getAll()) {

            if (issue.getId() == id && !issue.isClose()) {
                issue.setClose(true);
            }
        }
    }

    public void openById(int id) {
        Issue[] result;
        result = new Issue[0];
        for (Issue issue : repo.getAll()) {
            if (issue.getId() == id && issue.isClose()) {
                issue.setClose(false);
            }
        }
    }

    public Issue[] searchByLabel(String list) {
        Issue[] result;
        result = new Issue[0];
        for (Issue issue : repo.getAll()) {
            if (issue.getLabel().contains(list)) {
                Issue[] tmp = new Issue[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issue;
                result = tmp;
            }
        }
        return result;
    }
}

