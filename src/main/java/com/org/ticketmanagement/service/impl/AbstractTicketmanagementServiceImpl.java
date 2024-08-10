package com.org.ticketmanagement.service.impl;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import com.org.ticketmanagement.dto.IDTO;
import com.org.ticketmanagement.entity.IEntity;
import com.org.ticketmanagement.exception.TicketmanagementBusinessException;
import com.org.ticketmanagement.service.IAbstractTicketmanagementService;

import jakarta.annotation.Resource;

public abstract class AbstractTicketmanagementServiceImpl<Entity extends IEntity, BasicDTO extends IDTO, FullDTO extends BasicDTO, IEntityDAO extends JpaRepository<Entity, Integer>>
		implements IAbstractTicketmanagementService<Entity, BasicDTO, FullDTO, IEntityDAO> {
	/*
	 * private ModelMapper mapper; public abstract IEntityDAO getDAO();
	 * 
	 * public ModelMapper getModelMapper() { return this.mapper; }
	 */
	private Class<Entity> entityClass;
	private Class<BasicDTO> viewClass;
	private Class<FullDTO> fullDTOClass;
	// Resource destinée à la copie DTO <--> Entity
	@Resource(name = "Ticketmanagement-modelmapper")
	private ModelMapper mapper;

	public AbstractTicketmanagementServiceImpl(Class<Entity> myEntityClass, Class<BasicDTO> viewClass,
			Class<FullDTO> fullViewClass) {
		this.entityClass = myEntityClass;
		this.viewClass = viewClass;
		this.fullDTOClass = fullViewClass;
	}

	/**
	 * Create an new entity
	 * 
	 * @param ent : POJO to create
	 * @return
	 * @throws TicketmanagementBusinessException
	 */
	public FullDTO create(FullDTO view) throws TicketmanagementBusinessException {
		Entity entity = this.getModelMapper().map(view, this.entityClass);
		entity = this.getDAO().save(entity);
		view.setId(entity.getId());
		return view;
	}

	/**
	 * Update an entity
	 * 
	 * @param entToUpdate
	 * @throws TicketmanagementBusinessException,AccessDeniedException
	 */
	public FullDTO update(FullDTO viewToUpdate) throws TicketmanagementBusinessException, AccessDeniedException {
		// On va chercher l'entité pour l'attacher au contexte de persistence
		Entity entity = this.getDAO().findById(viewToUpdate.getId()).orElse(null);
		if (entity != null) {
			BeanUtils.copyProperties(viewToUpdate, entity);
			entity = this.getDAO().saveAndFlush(entity);
		} else {
			throw new TicketmanagementBusinessException("L'objet A modifier N'existe pas en Base...");
		}
		return viewToUpdate;
	}

	/**
	 * Delete an entity
	 * 
	 * @param id : Entity'Id to delete
	 * @throws TicketmanagementBusinessException
	 * @throws AccessDeniedException
	 */
	public void deleteById(int id) throws TicketmanagementBusinessException, AccessDeniedException {
		this.getDAO().deleteById(id);
	}

	/**
	 * Find All Entity
	 * 
	 * @return
	 */
	public List<BasicDTO> findAll() {
		List<Entity> list = this.getDAO().findAll();
		List<BasicDTO> viewList = new ArrayList<BasicDTO>();
		for (Entity ent : list) {
			BasicDTO view = this.getModelMapper().map(ent, this.viewClass);
			viewList.add(view);
		}
		return viewList;
	}

	/**
	 * Found Entity By Id
	 * 
	 * @param id : Entity's Id to found
	 * @return
	 * @throws TicketmanagementBusinessException
	 */
	public FullDTO findById(int id) throws TicketmanagementBusinessException {
		Entity ent = this.getDAO().getOne(id);
		FullDTO view = this.getModelMapper().map(ent, this.fullDTOClass);
		return view;
	}

	/**
	 * Teste l'existence d'une Entité par son id
	 * 
	 * @param id
	 * @return
	 * @throws TicketmanagementBusinessException
	 */
	public boolean ifEntityExistById(int id) throws TicketmanagementBusinessException {
		return this.getDAO().existsById(id);
	}

	/**
	 * Recupere un DTO par son id
	 */
	public <T extends BasicDTO> T findById(int id, Class<T> type) {
		Entity ent = this.getDAO().getOne(id);
		T view = this.getModelMapper().map(ent, type);
		return view;
	}

	/**
	 * Retourne la reference du DAO en cours
	 * 
	 * @return
	 */
	public abstract IEntityDAO getDAO();

	public ModelMapper getModelMapper() {
		return this.mapper;
	}

}
