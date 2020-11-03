package br.com.felipe.luciano.mvc.mudi.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.felipe.luciano.mvc.mudi.model.Pedido;
import br.com.felipe.luciano.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@CacheEvict(value="pedidos", allEntries=true)
	List<Pedido> findByStatus(StatusPedido status,Pageable sort);
	
	@Query("select p from Pedido p join p.user u where u.username = :username")
	List<Pedido> findAllByUsuario(@Param("username")String username);
	
	@Query("select p from Pedido p join p.user u where u.username = :username and p.status = :status")
	List<Pedido> findAllByStatusEUsuario(@Param("status")StatusPedido status,@Param("username")String username);
	
//	@PersistenceContext
//	private EntityManager entityManager;
	
	
//	public List<Pedido> recuperaTodosOsPedidos(){
//		return entityManager.createQuery("Select p from Pedido p",Pedido.class).getResultList();
		
	}


