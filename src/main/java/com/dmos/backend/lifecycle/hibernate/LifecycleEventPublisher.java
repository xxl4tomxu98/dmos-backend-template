package com.dmos.backend.lifecycle.hibernate;

import com.dmos.backend.lifecycle.ChangedProperty;
import com.dmos.backend.lifecycle.LifecycleEvent;
import com.dmos.backend.lifecycle.LifecycleEventType;
import com.dmos.backend.security.SecurityUtils;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class LifecycleEventPublisher implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {
    private final Logger log = LoggerFactory.getLogger(LifecycleEventPublisher.class);
    private final ApplicationEventPublisher eventPublisher;

    public LifecycleEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {

        log.debug("Received an update event for entity [{}]", event.getPersister().getEntityName());
        Set<ChangedProperty> properties = new HashSet<>();
        for (int i = 0; i < event.getDirtyProperties().length; i++) {
            int dirtyIndex = event.getDirtyProperties()[i];
            String propertyName = event.getPersister().getPropertyNames()[dirtyIndex];
            Object oldValue = event.getOldState()[dirtyIndex];
            Object newValue = event.getState()[dirtyIndex];
            properties.add(new ChangedProperty(propertyName, oldValue, newValue));
            log.debug("Property {} changed from {} to {}", propertyName, oldValue, newValue);
        }

        eventPublisher.publishEvent(new LifecycleEvent(LifecycleEventType.UPDATE, event.getId(), event.getPersister().getEntityName(), getUsername(), properties, copyBean(event.getEntity())));
    }

    @Override
    public void onPostDelete(PostDeleteEvent event) {
        eventPublisher.publishEvent(new LifecycleEvent(LifecycleEventType.DELETE, event.getId(), event.getPersister().getEntityName(), getUsername(), null, copyBean(event.getEntity())));
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        eventPublisher.publishEvent(new LifecycleEvent(LifecycleEventType.CREATE, event.getId(), event.getPersister().getEntityName(), getUsername(), null, copyBean(event.getEntity())));
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }

    private String getUsername(){
        return SecurityUtils.getCurrentUserLogin().orElse(com.dmos.backend.config.Constants.ANONYMOUS_USER);
    }
//TODO SUPER FIX
    private Object copyBean(Object source){
        try {
            return null;//BeanUtils.cloneBean(source);
        } catch (Exception e) {
            log.warn("Unable to clone bean ", e);
            return source;
        }
    }

}
