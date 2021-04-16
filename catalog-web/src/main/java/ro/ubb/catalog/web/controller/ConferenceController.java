package ro.ubb.catalog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.catalog.core.domain.*;
import ro.ubb.catalog.core.service.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ConferenceController {
    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private PCMemberService pcMemberService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private EmailService emailService;



    @RequestMapping(value = "/conferences", method = RequestMethod.POST)
    ResponseEntity<String> saveConference(@RequestBody Conference conference) {
        boolean result = conferenceService.addConference(conference);
        if (result) {
            return ResponseEntity.ok("Conference saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conference was not saved!");
        }
    }

    @RequestMapping(value = "/conferences", method = RequestMethod.GET)
    List<Conference> getAllConferences() {
        return conferenceService.getConferences();
    }

    @RequestMapping(value = "/emails", method = RequestMethod.POST)
    ResponseEntity<String> saveEmail(@RequestBody Email email) {
        boolean result = emailService.sendInvitation(email);
        if (result) {
            return ResponseEntity.ok("Invitation sent successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The invitation was not sent!");
        }
    }

    @RequestMapping(value = "/emails", method = RequestMethod.GET)
    List<Email> getAllEmails() {
        return emailService.getAllEmails();
    }


    @RequestMapping(value ="/chairs", method = RequestMethod.POST)
    ResponseEntity <String> addChairBasedInvitation(@RequestBody Chair chair) {

            boolean addResult = pcMemberService.addChair(chair);
            if (addResult){
                return ResponseEntity.ok("A new chair was invited and added successfully !");
            }

            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chair was not added successfully!");
            }
    }


   /* @RequestMapping(value ="/chairs", method = RequestMethod.POST)
    ResponseEntity <String> addChairBasedInvitation(@RequestBody Chair chair, @RequestBody Email email) {
        boolean result = emailService.sendInvitation(email);
        if(result) {
        boolean addResult = pcMemberService.addChair(chair);
        if (addResult){
            return ResponseEntity.ok("A new chair was invited and added successfully !");
        }

        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chair was not added successfully!");
        }
        }
        else{
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invitation was not sent!");
        }
    }*/

    @RequestMapping(value ="/chairs", method = RequestMethod.GET)
    List<Chair> getChairs(){ return pcMemberService.getAllChairs();}



    @RequestMapping(value ="/pcmembers", method = RequestMethod.POST)
    ResponseEntity <String> addPcMemberBasedInvitation(@RequestBody PCMember pcMember) {
            boolean addResult = pcMemberService.addPCMember(pcMember);
            if (addResult){
                return ResponseEntity.ok("A new PcMember was invited and added successfully !");
            }

            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PcMember was not added successfully!");
            }
    }


    /*@RequestMapping(value ="/pcmembers", method = RequestMethod.POST)
    ResponseEntity <String> addPcMemberBasedInvitation(@RequestBody PCMember pcMember, @RequestBody Email email) {
        boolean result = emailService.sendInvitation(email);
        if(result) {
            boolean addResult = pcMemberService.addPCMember(pcMember);
            if (addResult) {
                return ResponseEntity.ok("A new PcMember was invited and added successfully !");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PcMember was not added successfully!");
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invitation was not sent!");
        }
    }*/

    @RequestMapping(value ="/pcmembers", method = RequestMethod.GET)
    List<PCMember> getPcMembers(){ return pcMemberService.getAllPCMembers();}


    @RequestMapping(value="/proposals", method = RequestMethod.POST)
    ResponseEntity <String> submitProposal(@RequestBody Proposal proposal){
        boolean result = proposalService.addProposal(proposal);
        if (result) {
            return ResponseEntity.ok("Proposal submitted successfully!");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Proposal was not submitted!");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity <String> addLogin(@RequestBody Login login) {
        boolean result = loginService.addLogin(login);
        if (result) {
            return ResponseEntity.ok("Login added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login was not added!");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    List<Login> getLogin(@RequestBody Login login) {return loginService.getLogins();}

    @RequestMapping(value ="/proposals", method = RequestMethod.GET)
    List<Proposal> getProposals(){ return proposalService.getProposals();}

    @RequestMapping(value="/evaluations",method=RequestMethod.POST)
    ResponseEntity <String> addEvaluation(@RequestBody Evaluation evaluation) {
        boolean result = paperService.addEvaluation(evaluation);
        if (result) {
            return ResponseEntity.ok("Evaluation added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Evaluation was not added!");
        }
    }

    @RequestMapping(value ="/reviewers", method = RequestMethod.GET)
    List<Reviewer> getReviewers(){ return pcMemberService.getAllReviewers();}

    @RequestMapping(value ="/reviews", method = RequestMethod.GET)
    List<Evaluation> getReviews(@RequestBody Proposal proposal){
        return proposalService.findOneProposal(proposal.getId()).map(p->p.getPaper().getEvaluations()).orElse(Collections.emptyList());
    }
    @RequestMapping(value ="/recommendation", method = RequestMethod.POST)
    ResponseEntity<String> addRecommendation(@RequestBody Evaluation evaluation){
        boolean result = paperService.updateEvaluationResult(evaluation);
        if (result) {
            return ResponseEntity.ok("Recommendation added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Recommendation was not added!");
        }
    }

    @RequestMapping(value = "/getEvaluations", method = RequestMethod.GET)
    List<Evaluation> getEvaluations() { return paperService.getAllEvaluations();}

    @RequestMapping(value = "/papers", method = RequestMethod.GET)
    List<Paper> getPapers() { return paperService.getAllPapers();}

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    List<Author> getAuthors() { return participantService.getAuthors();}



    @RequestMapping(value = "/acceptedPapers", method = RequestMethod.GET)
    List<Paper> getAcceptedPapers() {


        List<Evaluation> evalId = getEvaluations().stream().filter(x -> x.getResult().equals("Accepted")).collect(Collectors.toList());
        List<Paper> pap = new ArrayList<>();

        evalId.forEach(x -> { pap.add( paperService.findOnePaper(x.getPaper().getId()).get());});

        return pap;


    }

    @RequestMapping(value = "/authorsOfAllPapers", method = RequestMethod.GET)
    List<Author> getPapersAuthors() { // returns the authors of the papers -- all of them

        List<Paper> pap = getPapers();
        List<Author> aut = new ArrayList<>();

        pap.forEach(x -> {aut.add(participantService.findOneAuthor(x.getProposal().getId()).get());});

        return aut;

    }

    @RequestMapping(value = "/paperOfAuthor", method = RequestMethod.GET)
    Paper getPaperOfAuthor(@RequestBody Author author) { // returns the paper of one specific author

        List<Paper> pap = getPapers();

        return (Paper) pap.stream().filter(x -> x.getProposal().getId() == author.getProposal().getId());

    }


    @RequestMapping(value = "/authorsOfAcceptedPapers", method = RequestMethod.GET)
    List<Author> getAuthorsAcceptedPapers() {// returns the authors of the accepted papers

        List<Paper> pap = getAcceptedPapers();
        List<Author> aut = new ArrayList<>();

        pap.forEach(x -> {aut.add(participantService.findOneAuthor(x.getProposal().getId()).get());});

        return aut;

    }



    @RequestMapping(value = "/updPaperOfAuthor", method = RequestMethod.POST)
    ResponseEntity<String> updateAcceptedPaper(@RequestBody Author author, @RequestBody String document){

        if (getAuthorsAcceptedPapers().contains(author))
        {
            Paper autPaper = getPaperOfAuthor(author);


            autPaper.setDocument(document);

            boolean result = paperService.updatePaper(autPaper);

            if (result){
                return ResponseEntity.ok("Document updated successfully!");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't update document!");
            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Author has no accepted paper!");
        }


    }





}
