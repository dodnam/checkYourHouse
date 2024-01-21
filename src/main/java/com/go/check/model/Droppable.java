package com.go.check.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Transient;
import java.time.ZonedDateTime;

public interface Droppable {

    ZonedDateTime getDropped();

    @Transient
    @JsonIgnore
    default boolean isDropped() {
        return this.getDropped() != null;
    }
}
