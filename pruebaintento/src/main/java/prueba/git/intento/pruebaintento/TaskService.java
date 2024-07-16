package prueba.git.intento.pruebaintento;



import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TaskService {

    @Inject
    private EntityManager em;

    @Transactional
    public Task createTask(Task task) {
        em.persist(task);
        return task;
    }

    public Task findTaskById(Long id) {
        return em.find(Task.class, id);
    }

    public List<Task> getAllTasks() {
        return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }

    @Transactional
    public Task updateTask(Task task) {
        return em.merge(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = em.find(Task.class, id);
        if (task != null) {
            em.remove(task);
        }
    }
}
