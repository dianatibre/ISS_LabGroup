# ISS_LabGroup
- admin creates the conference (addConference, getConferences)   Andrada
-	admin adds email addresses of the chairs of the conference(email: firstName.lastName@yahoo.com + token) -sendInvitations    Andrada
-	chairs can add more chairs/PC member based in email addresses -sendInvitations + getEmails
-	authors submit a new proposal with metadata for paper
-	PC members bids the proposals (addBidding + getBiddings)
-	Chair assigns reviewers for proposal (addReviewerForProposal, getProposals, getReviewers, getPCMembers)
-	PC members give evaluations (addEvaluation, getProposals, getReviewers, getPCmembers) -evaluation entity- add recommendations 	Razvan
-	Resolve contradictory reviews(chairs)    Andrada
-	After a reviewer uploads the review, they’ll be able to see other reviews (getReviews for a proposal)		Razvan
-	Chairs send the results (getAcceptedPapers, getAuthors/EmailsForAccPapers)  Timotei
-	Authors improve their papers (if it was accepted)  Timotei
-	Chairs create the sections, papers to sections (getSections)
-	Chairs designate PCmembers to be sessionChairs 
-	Speakers upload the presentations
-	Listeners choose the section

