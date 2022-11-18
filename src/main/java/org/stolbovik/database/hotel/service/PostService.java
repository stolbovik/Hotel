package org.stolbovik.database.hotel.service;

import org.stolbovik.database.hotel.models.Post;
import org.stolbovik.database.hotel.repository.PostRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class PostService {

    private final PostRepository postRepository;
    private final Statement statement;

    public PostService(Statement statement) {
        this.postRepository = new PostRepository();
        this.statement = statement;
    }

    public List<Post> getPosts() throws SQLException {
        String query = "select * from Должности";
        Optional<List<Post>> list = postRepository.readPost(statement, query);
        if (list.isPresent()) {
            return list.get();
        }
        throw new SQLException("Таблица \"Должности\" пуста\n");
    }

    public Post getPostByName(String name) throws SQLException {
        String query = "select * from Должности where Должность = " + name;
        Optional<List<Post>> list = postRepository.readPost(statement, query);
        if (list.isPresent()) {
            return list.get().get(0);
        }
        throw new SQLException("Такой должности не существует");
    }
}