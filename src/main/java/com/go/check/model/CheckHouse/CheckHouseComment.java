package com.go.check.model.CheckHouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import com.go.check.model.Droppable;
import com.go.check.model.User.User;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "checkHouseComment")
@DynamicUpdate
@Getter
public class CheckHouseComment implements Serializable, Droppable {

    @Id
    @Column(name = "checkHouseCommentId", length = 16)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Immutable
    private Long id;

    @Column(length = 16)
    private Long prentId;

    @Column(length = 63353, columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    @JoinColumn(name="checkHouseId")
    private CheckHouse checkHouse;

    @ManyToOne
    @JoinColumn
    private User creator;

    @Column(nullable = false)
    @Immutable
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime created;

    @ManyToOne
    @JoinColumn(name = "dropperId")
    @JsonIgnore
    private User dropper;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime dropped;

    public void drop(User dropper) {
        this.dropper = dropper;
        this.dropped = ZonedDateTime.now();
    }

    public static CheckHouseComment newEntity(String comment, CheckHouse checkHouse, User creator) {
        CheckHouseComment checkHouseComment = new CheckHouseComment();
        checkHouseComment.comment = comment;
        checkHouseComment.checkHouse = checkHouse;
        checkHouseComment.creator = creator;
        checkHouseComment.created = ZonedDateTime.now();
        return checkHouseComment;
    }
}
