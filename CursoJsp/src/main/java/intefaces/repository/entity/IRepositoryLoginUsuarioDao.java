package intefaces.repository.entity;

import java.sql.SQLException;

import model.Usuario;

public interface IRepositoryLoginUsuarioDao <T> {

	Usuario validarLogin(T Entidade) throws SQLException;
}
