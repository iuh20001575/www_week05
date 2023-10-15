package vn.edu.iuh.fit.backend.ids;

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
