package jp.blackawa.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "STEM")
@Data
@NoArgsConstructor
public class Stem {
    @Id
    private UUID id;

    private String name;
    @Column(name = "CREATE_USER_ID")
    private UUID createUserId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date createdTime;
    @Column(name = "UPDATE_USER_ID")
    private UUID updateUserId;
    @Column(name = "UPDATE_TIME")
    private Date updatedTime;
}
