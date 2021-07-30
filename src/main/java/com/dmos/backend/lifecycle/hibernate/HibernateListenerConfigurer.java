//package com.dmos.backend.lifecycle.hibernate;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.hibernate.event.service.spi.EventListenerRegistry;
//import org.hibernate.event.spi.EventType;
//import org.hibernate.internal.SessionFactoryImpl;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManagerFactory;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class HibernateListenerConfigurer {
//
//    private final EntityManagerFactory emf;
//    private final LifecycleEventPublisher lifecycleEventPublisher;
//    private final LifecycleEventCollectionPublisher collectionPublisher;
//
//
//    @PostConstruct
//    protected void init() {
//        log.info("Registering lifecycle event listener with session factory");
//        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
//        EventListenerRegistry registry =
//                sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
//        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(lifecycleEventPublisher);
//        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(lifecycleEventPublisher);
//        registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(lifecycleEventPublisher);
//        registry.getEventListenerGroup(EventType.POST_COLLECTION_RECREATE).appendListener(collectionPublisher);
//        registry.getEventListenerGroup(EventType.POST_COLLECTION_UPDATE).appendListener(collectionPublisher);
//        registry.getEventListenerGroup(EventType.POST_COLLECTION_REMOVE).appendListener(collectionPublisher);
//
//    }
//}
