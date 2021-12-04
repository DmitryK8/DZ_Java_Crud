package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issues;
import ru.netology.repo.IssuesRepo;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class IssuesManagerTest {

    private IssuesRepo repo;

    {
        repo = new IssuesRepo();
    }

    private IssuesManager manager;

    private Issues n1;

    {
        manager = new IssuesManager(repo);
        n1 = new Issues(1, "DZ1", true, "Дмитрий", Set.of("Ошибка"), Set.of("Олег"), Set.of("Альфа"), Set.of("Нет"));
    }

    private Issues n2;

    {
        n2 = new Issues(2, "DZ2", false, "Вася Пупкин", Set.of("правка"), Set.of("Дмитрий"), Set.of("Бета"), Set.of("Нет"));
    }

    private Issues n3;

    {
        n3 = new Issues(3, "DZ3", true, "Федя", Set.of("Error"), Set.of("Кравченко"), Set.of("Юта"), Set.of("Нет"));
    }

    private Issues n4;

    {
        n4 = new Issues(4, "DZ4", false, "Дмитрий", Set.of("Спецификация"), Set.of("Вера"), Set.of("Зета"), Set.of("v1"));
    }

    private Issues n5;

    {
        n5 = new Issues(5, "DZ5", true, "Вера", Set.of("Ошибка"), Set.of("Дмитрий"), Set.of("Заря"), Set.of("нет"));
    }


    @BeforeEach
    void setUp() {
    }

    @Test
    void added() {
    }

    @Test
    void searchClose() {
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issues[] expected = new Issues[]{n1, n3, n5};
        Issues[] actual = manager.searchClose();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchOpen() {
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issues[] expected = new Issues[]{n2, n4};
        Issues[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthor() {
        String author;
        author = "Дмитрий";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issues[] expected = new Issues[]{n1, n4};
        Issues[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthor1() {
        String author;
        author = "Вера";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issues[] expected = new Issues[]{n5};
        Issues[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthorNon() {
        String author;
        author = "Олег";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySuccesor() {
        String successor;
        successor = "Дмитрий";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issues[] expected = new Issues[]{n2, n5};
        Issues[] actual = manager.searchBySuccesor(successor);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySuccesor1() {
        String successor;
        successor = "Вера";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issues[] expected = new Issues[]{n4};
        Issues[] actual = manager.searchBySuccesor(successor);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySuccesorNon() {
        String successor;
        successor = "Пупкин";
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchBySuccesor(successor);

        assertArrayEquals(expected, actual);
    }

    @Test
    void closeById() {
        int idToClose = 2;
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        manager.closeById(idToClose);
        Issues[] expected = new Issues[]{n1, n2, n3, n5};
        Issues[] actual = manager.searchClose();

        assertArrayEquals(expected, actual);
    }

    @Test
    void openById() {
        int idToOpen = 3;
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        manager.openById(idToOpen);
        Issues[] expected = new Issues[]{n2, n3, n4};
        Issues[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }
    @Test
    void openByIdAlready() {
        int idToOpen = 5;
        repo.saveAll(List.of(n1, n2, n3, n4, n5));
        manager.openById(idToOpen);

        Issues[] expected = new Issues[]{n2, n4, n5};
        Issues[] actual = manager.searchOpen();
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeById() {
    }

    @Test
    void searchByLabel() {
    }
}