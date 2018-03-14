package fr.nicolascherridi.springvue.controller;

import fr.nicolascherridi.springvue.jpa.Task;
import fr.nicolascherridi.springvue.jpa.User;
import fr.nicolascherridi.springvue.repository.TaskRepository;
import fr.nicolascherridi.springvue.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    public static final String HELLO_TEXT = "Réponse du controller back :)";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(path = "/hello")
    public @ResponseBody String sayHello() {
        LOG.info("Bondour le backend fonctionne bien :)");
        return HELLO_TEXT;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(path = "/task/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Task findTask (@PathVariable("id") long id) throws IOException {
        Task task = taskRepository.findById(id);
        return task;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(path = "/tasks", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Task> findTasks () throws IOException {
        List<Task> tasks = taskRepository.findByAuthor("Nicolas");
        return (List<Task>) taskRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(path = "/addTask", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    String addTask (@RequestBody Task task) throws IOException {
        taskRepository.save(task);
        return ("Done");
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(path = "/editTask/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    String editTask (@PathVariable("id") long id, @RequestBody Task task) throws IOException {
        Task oldTask = taskRepository.findById(id);
        oldTask.setAuthor(task.getDescription());
        oldTask.setDescription(task.getDescription());
        oldTask.setDuration(task.getDuration());
        taskRepository.save(oldTask);
        return ("Tâche n° "+id+" updatée");
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(path = "/deleteTask/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    String deleteTask (@PathVariable("id") long id) throws IOException {
        Task task = taskRepository.findById(id);
        taskRepository.delete(task);
        return ("Tâche n° "+id+" supprimée");
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(path = "/markTask/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    String markTask (@PathVariable("id") long id) throws IOException {
        Task task = taskRepository.findById(id);
        if(task.isDone()==true){
            task.setDone(false);
        } else {
            task.setDone(true);
        }
        taskRepository.save(task);
        return ("Tâche n° "+id+" updatée");
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") long id) {
        return userRepository.findOne(id);
    }

}
