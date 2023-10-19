package br.com.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todolist.model.UserModel;

public interface UserRespository extends JpaRepository<UserModel, Long>{

	UserModel findByUsername(String username);
}
