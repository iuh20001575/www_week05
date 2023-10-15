package vn.edu.iuh.fit.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.backend.entities.Address;
import vn.edu.iuh.fit.backend.entities.Candidate;

import java.util.Arrays;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
    @GetMapping("/add")
    public String addController(Model model) {
        Candidate candidate = new Candidate();
        Address address = new Address();
        candidate.setAddress(address);

        model.addAttribute("candidate", candidate);
        model.addAttribute("address", address);
        model.addAttribute("countryCodes", CountryCode.values());

        System.out.println(CountryCode.values()[0].getName());

        return "/candidates/addCandidate";
    }

    @GetMapping
    public String manage() {


        return "/candidates/index";
    }

    @PostMapping("/add")
    public String addController(@ModelAttribute("candidate") Candidate candidate, @ModelAttribute("address") Address address) {
        System.out.println(candidate);
        System.out.println(address);

        return "/candidates/addCandidate";
    }
}
