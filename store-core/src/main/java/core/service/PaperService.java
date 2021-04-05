package core.service;

import core.domain.Evaluation;
import core.domain.Paper;
import core.repository.EvaluationRepoI;
import core.repository.PaperRepoI;
import core.repository.ProposalRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaperService {

    @Autowired
    private PaperRepoI repo;

    @Autowired
    private EvaluationRepoI evalRepo;

    @Autowired
    private ProposalRepoI proposalRepo;

    public boolean addPaper(Paper paper) {
        Optional<Paper> pap = repo.findById(paper.getId());
        if (pap.isPresent())
            return false;
        repo.save(paper);
        return true;
    }

    public Optional<Paper> findOnePaper(Integer id) {
        return repo.findById(id);
    }


    @Transactional
    public boolean addEvaluation(Evaluation evaluation) {
        //find the number of evaluations of the specific paper, and if it's greater than 4, deny the operation
        Optional<Evaluation> pro = evalRepo.findById(evaluation.getId());
        if(evalRepo.findEvaluationsByPaperId(pro.get().getPaper().getId()).size() >= 4) {
            return false;
        }

        try {
            Paper p = repo.findById(evaluation.getPaper().getId()).get();
            List<Evaluation> evaluations = p.getEvaluations();
            Evaluation ne = Evaluation.builder()
                    .reviewer(evaluation.getReviewer())
                    .paper(p)
                    .result(evaluation.getResult())
                    .build();
            evaluations.add(ne);
            p.setEvaluations(evaluations);
            repo.save(p);
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public List<Paper> getAllPapers() {
        return new ArrayList<>(this.repo.findAll());
    }

    public boolean updateEvaluationResult(Evaluation evaluation) {
        Evaluation e = this.evalRepo.findById(evaluation.getId()).get();
        e.setResult(evaluation.getResult());
        this.evalRepo.save(e);
        return true;
    }

    public List<Paper> getPapersByReviewer(Integer reviewerID) {
        List<Evaluation> evaluations = evalRepo.findAll();
        return evaluations.stream().filter(e -> e.getReviewer().getId() == reviewerID).map(e -> e.getPaper()).collect(Collectors.toList());
    }
}
