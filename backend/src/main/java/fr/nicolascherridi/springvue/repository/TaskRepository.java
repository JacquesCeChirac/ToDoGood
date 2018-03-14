package fr.nicolascherridi.springvue.repository;


import fr.nicolascherridi.springvue.jpa.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends  CrudRepository<Task, Long>{

    List<Task> findByDone(@Param("done") boolean done);
    List<Task> findByAuthor(@Param("author") String author);
    Task findById(@Param("id") long id);
    Task save(Task task);
    @Override
    void delete(Task task);
//List<Task> findByAuthor(@Param("author") string author);

}

