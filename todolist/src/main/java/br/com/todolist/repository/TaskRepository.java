package br.com.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todolist.model.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, Long>{

	List<TaskModel> findByIdUser(Long idUser);
}
