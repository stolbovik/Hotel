package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Post;
import org.stolbovik.database.hotel.repository.PostRepository;
import org.stolbovik.database.hotel.utils.Constatns;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class PostService {

    private final PostRepository postRepository;
    private final Statement statement;

    public PostService() {
        this.postRepository = new PostRepository();
        this.statement = Constatns.STATEMENT;
    }

    public List<Post> getAllPosts() throws SQLException {
        String query = "select * from Должности";
        Optional<List<Post>> list = postRepository.readPost(statement, query);
        if (list.isPresent()) {
            return list.get();
        }
        throw new SQLException("В отеле нет должностей");
    }

    public Post getPostByName(@NotNull String name) throws SQLException {
        String query = "select * from Должности where Должность = '" + name + "'";
        Optional<List<Post>> list = postRepository.readPost(statement, query);
        if (list.isPresent()) {
            return list.get().get(0);
        }
        throw new SQLException("Такой должности не существует");
    }

    public void decrementCountOfRequiredEmployees(@NotNull Post post) throws SQLException {
        String query = "update Должности set [Количество требуемых работников] = " +
                (post.getCountOfRequiredEmployees() - 1) + " where ID = " + post.getId();
        int res = postRepository.updatePost(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось уменьшить количество требуемых работников");
        }
    }

    public void incrementCountOfRequiredEmployees(@NotNull Post post) throws SQLException {
        String query = "update Должности set [Количество требуемых работников] = " +
                (post.getCountOfRequiredEmployees() + 1) + " where ID = " + post.getId();
        int res = postRepository.updatePost(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось увеличить количество требуемых работников");
        }
    }

    public Post getPostById(int id) throws SQLException {
        String query = "select * from Должности where ID = " + id;
        Optional<List<Post>> list = postRepository.readPost(statement, query);
        if (list.isPresent()) {
            return list.get().get(0);
        }
        throw new SQLException("Должности с данным ID не существует");
    }
}
