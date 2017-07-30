package com.luxoft.logeek.listener;

import lombok.RequiredArgsConstructor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Component
@Profile("deleteListenerOn")
@RequiredArgsConstructor
public class HibernateEventWiring {
	private final EntityManagerFactory emf;
	private final DeleteListener deleteListener;

	@PostConstruct
	public void registerListeners() {
		emf.unwrap(SessionFactoryImpl.class)
				.getServiceRegistry()
				.getService(EventListenerRegistry.class)
				.getEventListenerGroup(EventType.DELETE)
				.prependListener(deleteListener);
	}
}