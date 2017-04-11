package com.luxoft.logeek.listener;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Component
public class HibernateEventWiring {
	private final EntityManagerFactory entityManagerFactory;
	private final DeleteListener deleteListener;

	@Autowired
	public HibernateEventWiring(EntityManagerFactory entityManagerFactory, DeleteListener deleteListener) {
		this.entityManagerFactory = entityManagerFactory;
		this.deleteListener = deleteListener;
	}

	@PostConstruct
	public void registerListeners() {
		EventListenerRegistry registry = entityManagerFactory.unwrap(SessionFactoryImpl.class).getServiceRegistry().getService(EventListenerRegistry.class);

		registry.getEventListenerGroup(EventType.DELETE).prependListener(deleteListener);
	}
}