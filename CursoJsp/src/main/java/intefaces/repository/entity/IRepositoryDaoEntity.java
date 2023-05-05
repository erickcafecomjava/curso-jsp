package intefaces.repository.entity;

import java.sql.SQLException;
import java.util.List;

import model.Livro;
import model.Usuario;

public interface IRepositoryDaoEntity<T> {

	T salvar(T entidade, Long idAdmin) throws SQLException;

	T deletar(T entidadeId) throws SQLException;

	List<T> pesquisar(String nome, Long idAdmin) throws SQLException;

	T pesquisarPorIdentificador(Integer id) throws SQLException;

}
