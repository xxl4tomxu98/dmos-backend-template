package com.dmos.backend.lifecycle;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class LifecycleEvent implements Serializable {
    @NonNull
    private LifecycleEventType eventType;
    @NonNull
    private Object id;
    @NonNull
    private String entityType;
    private String modifier;
    private Set<ChangedProperty> properties;
    private Object entity;

}
