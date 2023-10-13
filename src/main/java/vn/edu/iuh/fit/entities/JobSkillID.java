package vn.edu.iuh.fit.entities;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class JobSkillID implements Serializable {
    private long job;
    private long skill;
}
