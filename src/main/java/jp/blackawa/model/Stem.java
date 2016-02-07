package jp.blackawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "STEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stem extends AbstractModel {
    @Id
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "stem")
    private Set<Branch> branches = new HashSet<>();
}
