package fr.nicolascherridi.springvue.jpa;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    // PrimaryKey
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "description")
    private String description;
    @Column(name = "author")
    private String author;
    @Column(name = "duration")
    private long duration;
    @Column(name = "done")
    private boolean done;

    public Task() {
    }

    @Override
    public String toString() {
        return String.format(
                "Task[id=%d, description='%s', author='%s', duration='%d']",
                id, description, author, duration);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}





