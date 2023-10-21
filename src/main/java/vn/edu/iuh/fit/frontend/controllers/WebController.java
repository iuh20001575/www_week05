package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.entities.Candidate;
import vn.edu.iuh.fit.backend.entities.Job;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class WebController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/")
    public String home(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam("candidate-id") Optional<Long> candidateId, Model model) {
        int sizeI = size.orElse(10);
        List<Candidate> candidates = candidateRepository.findAll();

        if (candidateId.isPresent()) {
            PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, sizeI, Sort.by("id"));

            Page<Job> jobs = jobRepository.suggestJobsByCandidate(candidateId.get(), pageRequest);

            model.addAttribute("jobs", jobs);
            model.addAttribute("canId", candidateId.get());
            model.addAttribute("pages", IntStream.rangeClosed(1, jobs.getTotalPages()).boxed().collect(Collectors.toList()));
        }
        model.addAttribute("candidates", candidates);

        return "index";
    }
}
