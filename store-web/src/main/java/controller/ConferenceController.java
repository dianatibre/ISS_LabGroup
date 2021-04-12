package controller;

import core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
}
