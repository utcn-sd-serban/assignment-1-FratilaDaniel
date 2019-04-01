package ro.utcn.daneeee.assignment1.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="questiontags")
public class QuestionTagAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer questionId;
    private Integer tagId;

    public QuestionTagAssociation(int qId, int tId){
        this.questionId = qId;
        this.tagId = tId;
    }
}
