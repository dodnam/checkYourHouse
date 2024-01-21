package com.go.check.model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.go.check.model.Droppable;
import com.go.check.model.auth.Authority;
import com.go.check.model.auth.Password;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@DynamicUpdate
@Getter
@Table(name = "user")
public class User implements Serializable, Droppable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId", nullable = false)
    private Long id;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(length = 16, nullable = false)
    private String nickName;

    private Password password; // 수정 예정

    @Setter
    @Column(length = 16, nullable = false)
    private Authority authority;

    @Column(length = 32)
    private String phoneNumber;

    @Column(length = 63353, columnDefinition = "TEXT")
    private String details;

    @ManyToOne
    @JoinColumn(name = "creatorId")
    @Immutable
    @JsonIgnore
    private User creator;

    @UpdateTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private ZonedDateTime updated;

    @ManyToOne
    @JoinColumn(name = "dropperId")
    @JsonIgnore
    private User dropper;

    private ZonedDateTime dropped;

    public void drop(User dropper) {
        this.dropper = dropper;
        this.dropped = ZonedDateTime.now();
    }

    public static User newEntity(String name, String nickName, Password password, Authority authority,
                                 String phoneNumber, String details, User creator) {
        User user = new User();
        user.name = name;
        user.nickName = nickName;
        user.password = password;
        user.authority = authority;
        user.phoneNumber = phoneNumber;
        user.details = details;
        user.creator = creator;
        return user;
    }
}
