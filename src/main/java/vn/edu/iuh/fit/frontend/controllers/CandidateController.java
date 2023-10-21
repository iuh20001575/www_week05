package vn.edu.iuh.fit.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.entities.Address;
import vn.edu.iuh.fit.backend.entities.Candidate;
import vn.edu.iuh.fit.backend.entities.Skill;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.backend.services.CandidateServices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CandidateServices candidateServices;
    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/add")
    public String addController(Model model) {
        Candidate candidate = new Candidate();
        Address address = new Address();
        candidate.setAddress(address);

        model.addAttribute("candidate", candidate);
        model.addAttribute("address", address);
        model.addAttribute("countryCodes", CountryCode.values());

        return "/candidates/addCandidate";
    }

    @GetMapping
    public String manage(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int sizeInteger = size.orElse(10);

        Page<Candidate> candidates = candidateServices.findAll(page.orElse(1), sizeInteger, "id", "asc");
        long count = candidateRepository.count();
        int numberPages = (int) Math.ceil((double) count / sizeInteger);

        model.addAttribute("candidates", candidates);
        model.addAttribute("pages", IntStream.rangeClosed(1, numberPages).boxed().collect(Collectors.toList()));

        return "/candidates/index";
    }

    @Transactional
    @PostMapping("/add")
    public String addController(@ModelAttribute("candidate") Candidate candidate, @ModelAttribute("address") Address address, Model model) {
        try {
            Address addressSave = addressRepository.save(address);
            candidate.setAddress(addressSave);

            candidateRepository.save(candidate);

            return "redirect:/candidates";
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        model.addAttribute("candidate", candidate);
        model.addAttribute("address", address);
        model.addAttribute("countryCodes", CountryCode.values());

        return "/candidates/addCandidate";
    }

    @GetMapping("/suggest-skill")
    public String home(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam("candidate-id") Optional<Long> candidateId, Model model) {
        int sizeI = size.orElse(10);
        List<Candidate> candidates = candidateRepository.findAll();

        if (candidateId.isPresent()) {
            PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, sizeI, Sort.by("id"));

            Page<Skill> skills = skillRepository.suggestForCandidate(candidateId.get(), pageRequest);

            model.addAttribute("skills", skills);
            model.addAttribute("candidateId", candidateId.get());
            model.addAttribute("pages", IntStream.rangeClosed(1, skills.getTotalPages()).boxed().collect(Collectors.toList()));
        }
        model.addAttribute("candidates", candidates);

        return "candidates/suggestSkill";
    }
}
