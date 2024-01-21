package com.go.check.model.CheckHouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import com.go.check.model.Droppable;
import com.go.check.model.User.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@DynamicUpdate
@Getter
@Table(name = "checkHouse")
public class CheckHouse implements Serializable, Droppable {

    @Id
    @Column(name = "checkHouseId", length = 16)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Immutable
    private Long id;

    @Column(length = 16)
    private String category;

    @Column(length = 16)
    private String rentType; // 전월세 여부

    @Column(length = 256)
    private String address;

    @Column(columnDefinition = "int")
    private int maintenance; // 유지비

    @ElementCollection(fetch = FetchType.EAGER)
    @Setter
    private Set<String> tags; // 옵션 태그

    @Column(length = 64)
    private String title;

    @Column(length = 63353, columnDefinition = "TEXT")
    private String content;

    private String fileName;

    private String contentType;

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

    public static CheckHouse newEntity(String title, String content, String rentType, String address, int maintenance,
                                    String category, String fileName, String contentType) {
        CheckHouse checkHouse = new CheckHouse();
        checkHouse.title = title;
        checkHouse.content = content;
        checkHouse.rentType = rentType;
        checkHouse.address = address;
        checkHouse.maintenance = maintenance;
        checkHouse.category = category;
        checkHouse.fileName = fileName;
        checkHouse.contentType = contentType;
        checkHouse.created = ZonedDateTime.now();
        return checkHouse;
    }
}
