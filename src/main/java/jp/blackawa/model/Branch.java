package jp.blackawa.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "BRANCH")
@Data
@NoArgsConstructor
public class Branch {

    @Id
    private UUID id;
    private String name;
    @Column(name = "STEM_ID")
    private UUID stemId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Stem stem;
}
