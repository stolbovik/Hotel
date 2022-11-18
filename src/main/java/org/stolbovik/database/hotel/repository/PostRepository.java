package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostRepository {

    public Optional<List<Post>> readPost(@NotNull Statement statement,
                                                @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<Post> posts = new ArrayList<>();
        do {
            Post post = new Post();
            post.setId(resultSet.getInt("id"));
            post.setNameOfPost(resultSet.getString("Должность"));
            post.setCountOfRequiredEmployees(resultSet.getInt("Количество требуемых работников"));
            post.setSalary(resultSet.getInt("Зарплата/день"));
            posts.add(post);
        } while (resultSet.next());
        return Optional.of(posts);
    }

    public int updatePost(@NotNull Statement statement,
                                 @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}
