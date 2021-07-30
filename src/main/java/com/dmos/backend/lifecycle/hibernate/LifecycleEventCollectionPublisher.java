package com.dmos.backend.lifecycle.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.collection.internal.PersistentSet;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.event.spi.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class LifecycleEventCollectionPublisher implements PostCollectionUpdateEventListener, PostCollectionRecreateEventListener, PostCollectionRemoveEventListener{

    @Override
    public void onPostRecreateCollection(PostCollectionRecreateEvent event) {
        log.debug("Received an onPostRecreateCollection event for entity [{}]", event.getAffectedOwnerEntityName());

    }

    @Override
    public void onPostRemoveCollection(PostCollectionRemoveEvent event) {
        log.debug("Received an onPostRemoveCollection event for entity [{}]", event.getAffectedOwnerEntityName());

    }

    @Override
    public void onPostUpdateCollection(PostCollectionUpdateEvent event) {
        log.debug("Received an onPostUpdateCollection event for entity [{}]", event.getAffectedOwnerEntityName());
        PersistentCollection col = event.getCollection();

        if (col instanceof PersistentSet){
            Set<?> newCollection = new HashSet<>( (Set<?>) col.getValue());
            Set<?> oldCollection = new HashSet<>(((HashMap<?, ?>)col.getStoredSnapshot()).values());
            Set<?> additions = complement(newCollection, oldCollection);
            Set<?> deletions = complement(oldCollection, newCollection);
        }
    }

    // returns set that contains all elements of set one that are not in set two without modifying the input
    private static Set<?> complement(final Set<?> setOne, final Set<?> setTwo) {
        Set<?> result = new HashSet<>(setOne);
        result.removeIf(setTwo::contains);
        return result;
    }
}
