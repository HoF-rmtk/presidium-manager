package nl.th8.presidium.complaints.controller;

import nl.th8.presidium.complaints.controller.dto.Complaint;
import nl.th8.presidium.complaints.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/klacht")
public class ComplaintController {

    @Autowired
    ComplaintService complaintService;

    @GetMapping
    public String showComplaints(Model model, Principal principal) {
        boolean loggedIn;
        try {
            loggedIn = principal.getName().length() > 0;
        } catch (NullPointerException e) {
            loggedIn = false;
        }

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("complaint", new Complaint());

        return "complaints";
    }

    @PostMapping
    public String submitComplaint(@ModelAttribute Complaint complaint) {
        complaintService.sendComplaint(complaint.getComplaintText());

        return "redirect:/klacht?done";
    }
}
