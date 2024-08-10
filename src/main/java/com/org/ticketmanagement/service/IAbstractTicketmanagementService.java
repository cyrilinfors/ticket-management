package com.org.ticketmanagement.service;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.ticketmanagement.exception.TicketmanagementBusinessException;
 

public interface IAbstractTicketmanagementService<Entity, BasicDTO, FullDTO extends BasicDTO, IEntityDAO extends JpaRepository<Entity, Integer>> {

	/**
	 * Create an new entity
	 * 
	 * @param ent : POJO to create
	 * @return
	 * @throws TicketmanagementBusinessException
	 */
	public FullDTO create(FullDTO ent) throws TicketmanagementBusinessException;

	/**
	 * Update an entity
	 * 
	 * @param entToUpdate
	 * @throws TicketmanagementBusinessException,AccessDeniedException
	 */
	public FullDTO update(FullDTO entToUpdate) throws TicketmanagementBusinessException, AccessDeniedException;

	/**
	 * Delete an entity
	 * 
	 * @param id : Entity'Id to delete
	 * @throws TicketmanagementBusinessException
	 * @throws AccessDeniedException
	 */
	public void deleteById(int id) throws TicketmanagementBusinessException, AccessDeniedException;

	/**
	 * Find All Entity
	 * 
	 * @return
	 */
	public List<BasicDTO> findAll();

	/**
	 * Found Entity By Id
	 * 
	 * @param id : Entity's Id to found
	 * @return
	 * @throws TicketmanagementBusinessException
	 */
	public FullDTO findById(int id) throws TicketmanagementBusinessException;

	/**
	 * Teste l'existence d'une Entité par son id
	 * 
	 * @param id
	 * @return
	 * @throws IndyliBusinessException
	 */
	public boolean ifEntityExistById(int id) throws TicketmanagementBusinessException;

	/**
	 * Recupere un DTO par son id
	 */
	public <T extends BasicDTO> T findById(int id, Class<T> type) throws TicketmanagementBusinessException;

	/**
	 * Retourne la reference du DAO en cours
	 * 
	 * @return
	 */
	public IEntityDAO getDAO();

	 

}
