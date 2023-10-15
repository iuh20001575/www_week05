package vn.edu.iuh.fit.backend.ids;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CandidateSkillID implements Serializable {
    private long skill;
    private long candidate;
}
