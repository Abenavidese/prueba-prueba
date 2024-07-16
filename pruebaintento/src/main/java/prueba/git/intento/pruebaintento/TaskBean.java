package prueba.git.intento.pruebaintento;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;


@RequestScoped
@Named("taskBean")
public class TaskBean {

    @PersistenceContext
    private EntityManager em;

    private String description;
    private List<Task> tasks;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }

    public void addTask() {
        Task task = new Task();
        task.setDescription(description);
        task.setCompleted(false);
        em.getTransaction().begin();
        em.persist(task);
        em.getTransaction().commit();
    }

    public void updateTask(Task task) {
        em.getTransaction().begin();
        Task existingTask = em.find(Task.class, task.getId());
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.isCompleted());
        em.getTransaction().commit();
    }

    public void deleteTask(Task task) {
        em.getTransaction().begin();
        Task existingTask = em.find(Task.class, task.getId());
        em.remove(existingTask);
        em.getTransaction().commit();
    }
}