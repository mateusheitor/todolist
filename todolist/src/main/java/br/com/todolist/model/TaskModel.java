package br.com.todolist.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "tb_tasks")
public class TaskModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String description;
	
	@Column(length = 50)
	private String title;
	private LocalDateTime startAt;
	private LocalDateTime endAt;
	private String priority;
	
	private Long idUser;

	@CreationTimestamp
	private  LocalDateTime createdAdt;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws  Exception{
		if (title.length() > 50) {
			throw new Exception("O campo deve ter no maximo 50 caracteres");
		}
		this.title = title;
	}

	public LocalDateTime getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDateTime startAt) {
		this.startAt = startAt;
	}

	public LocalDateTime getEndAt() {
		return endAt;
	}

	public void setEndAt(LocalDateTime entAt) {
		this.endAt = entAt;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public LocalDateTime getCreatedAdt() {
		return createdAdt;
	}

	public void setCreatedAdt(LocalDateTime createdAdt) {
		this.createdAdt = createdAdt;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	
}
