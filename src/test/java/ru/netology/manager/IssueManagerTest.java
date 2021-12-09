package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.NotFoundException;
import ru.netology.repo.IssueRepo;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    private IssueRepo repo;

    {
        repo = new IssueRepo();
    }

    private IssuesManager manager = new IssuesManager(repo);

    private Issue n1;

    {
        manager = new IssuesManager(repo);
        n1 = new Issue(1, "DZ1", true, "Дмитрий", Set.of("Ошибка"), Set.of("Олег"), Set.of("Альфа"), Set.of("Нет"));
    }

    private Issue n2;

    {
        n2 = new Issue(2, "DZ2", false, "Вася Пупкин", Set.of("правка"), Set.of("Дмитрий"), Set.of("Бета"), Set.of("Нет"));
    }

    private Issue n3;

    {
        n3 = new Issue(3, "DZ3", true, "Федя", Set.of("Error"), Set.of("Кравченко"), Set.of("Юта"), Set.of("Нет"));
    }

    private Issue n4;

    {
        n4 = new Issue(4, "DZ4", false, "Дмитрий", Set.of("Спецификация"), Set.of("Вера"), Set.of("Зета"), Set.of("v1"));
    }

    private Issue n5;

    {
        n5 = new Issue(5, "DZ5", true, "Вера", Set.of("Ошибка"), Set.of("Дмитрий"), Set.of("Заря"), Set.of("нет"));
    }


    @BeforeEach
    void setUp() {
    }


    @Test
    void getAll() {
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issue[] expected = new Issue[]{n1, n2, n3, n4, n5};
        Issue[] actual = repo.getAll().toArray(new Issue[0]);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchClose() {
        assertTrue(repo.saveAll(List.of(n1, n2, n3, n4, n5)));
        Issue[] expected = new Issue[]{n1, n3, n5};
        Issue[] actual = manager.searchClose();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchOpen() {
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issue[] expected = new Issue[]{n2, n4};
        Issue[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthor() {
        String author;
        author = "Дмитрий";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issue[] expected = new Issue[]{n1, n4};
        Issue[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthor1() {
        String author;
        author = "Вера";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issue[] expected = new Issue[]{n5};
        Issue[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthorNon() {
        String author;
        author = "Олег";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySuccesor() {
        String successor;
        successor = "Дмитрий";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issue[] expected = new Issue[]{n2, n5};
        Issue[] actual = manager.searchBySuccesor(successor);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySuccesor1() {
        String successor;
        successor = "Вера";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issue[] expected = new Issue[]{n4};
        Issue[] actual = manager.searchBySuccesor(successor);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySuccesorNon() {
        String successor;
        successor = "Пупкин";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchBySuccesor(successor);

        assertArrayEquals(expected, actual);
    }

    @Test
    void closeById() {
        int idToClose = 2;
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        manager.closeById(idToClose);
        Issue[] expected = new Issue[]{n1, n2, n3, n5};
        Issue[] actual = manager.searchClose();

        assertArrayEquals(expected, actual);
    }

    @Test
    void openById3() {
        int idToOpen = 3;
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        manager.openById(idToOpen);
        Issue[] expected = new Issue[]{n2, n3, n4};
        Issue[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }

    @Test
    void openById5() {
        int idToOpen = 5;
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        manager.openById(idToOpen);
        Issue[] expected = new Issue[]{n2, n4, n5};
        Issue[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }

    @Test
    void remove() {
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        repo.remove(n3);
        Issue[] expected = new Issue[]{n1, n2, n4, n5};
        Issue[] actual = repo.getAll().toArray(new Issue[0]);

        assertArrayEquals(expected, actual);
    }


    @Test
    void searchByLabel() {
        String labelIssue = "Error";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issue[] expected = new Issue[]{n3};
        Issue[] actual = manager.searchByLabel(labelIssue);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByIdNot() {
        IssueRepo expected = null;
        assertThrows(NotFoundException.class, () -> repo.removeById(100));
    }

    @Test
    void shouldRemoveById() {
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issue expected = n5;
        Issue actual = repo.findById(5);

        assertEquals(expected, actual);
    }
}