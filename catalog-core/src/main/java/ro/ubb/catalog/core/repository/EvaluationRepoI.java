package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.ubb.catalog.core.domain.Evaluation;

import java.util.List;

public interface EvaluationRepoI extends RepoI<Evaluation, Integer>{
    @Query("SELECT e FROM Evaluation e WHERE e.paper = (:paperID)")
    public List<Evaluation> findEvaluationsByPaperId(@Param("paperID") Integer paperID);
}
