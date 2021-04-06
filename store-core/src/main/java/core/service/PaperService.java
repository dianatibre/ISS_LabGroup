package core.service;

import core.domain.Evaluation;
import core.domain.Paper;
import core.domain.Reviewer;
import core.domain.Section;
import core.repository.EvaluationRepoI;
import core.repository.PaperRepoI;
import core.repository.ProposalRepoI;
import core.repository.ReviewerRepoI;
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

    @Autowired
    private ReviewerRepoI reviewerRepo;

    public boolean addPaper(Paper paper) {
        if(paper.getTitle().equals("") || paper.getDocument().equals("") || !proposalRepo.findById(paper.getProposal().getId()).isPresent()){
            return false;
        }
        Optional<Paper> pap = repo.findById(paper.getId());
        if (pap.isPresent()) {
            return false;
        }
        repo.save(paper);
        return true;
    }

    public Optional<Paper> findOnePaper(Integer id) {
        return repo.findById(id);
    }

    public boolean deletePaper(Integer id) {
        Optional<Paper> paper = this.repo.findById(id);
        if (paper.isPresent()) {
            this.repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updatePaper(Paper paper) {
        if(paper.getTitle().equals("") || paper.getDocument().equals("") || !proposalRepo.findById(paper.getProposal().getId()).isPresent()){
            return false;
        }
        this.repo.findById(paper.getId()).ifPresent(s -> {
            s.setTitle(paper.getTitle());
            s.setDocument(paper.getDocument());
            s.setProposal(paper.getProposal());
            s.setSpeaker(paper.getSpeaker());
            s.setEvaluations(paper.getEvaluations());
        });
        return true;
    }


    public boolean addEvaluation(Evaluation evaluation) {
        //find the number of evaluations of the specific paper, and if it's greater than 4, deny the operation
        Optional<Evaluation> pro = evalRepo.findById(evaluation.getId());
        if (evalRepo.findEvaluationsByPaperId(pro.get().getPaper().getId()).size() >= 4) {
            return false;
        }
        if (evaluation.getResult().equals("") || !reviewerRepo.findById(evaluation.getReviewer().getId()).isPresent() || !repo.findById(evaluation.getPaper().getId()).isPresent())
            return false;

        Optional<Evaluation> ev = this.evalRepo.findById(evaluation.getId());
        if (ev.isPresent()) {
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
        if (evaluation.getResult().equals("") || !reviewerRepo.findById(evaluation.getReviewer().getId()).isPresent() || !repo.findById(evaluation.getPaper().getId()).isPresent())

            return false;
        Evaluation e = this.evalRepo.findById(evaluation.getId()).get();
        e.setResult(evaluation.getResult());
        this.evalRepo.save(e);
        return true;
    }

    public boolean deleteEvaluation(Integer id) {
        Optional<Evaluation> evaluation = this.evalRepo.findById(id);

        if (evaluation.isPresent()){
            this.evalRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Paper> getPapersByReviewer(Integer reviewerID) {
        List<Evaluation> evaluations = evalRepo.findAll();
        return evaluations.stream().filter(e -> e.getReviewer().getId() == reviewerID).map(e -> e.getPaper()).collect(Collectors.toList());
    }
}

