package vn.edu.iuh.fit.frontend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.entities.Company;
import vn.edu.iuh.fit.backend.entities.Job;
import vn.edu.iuh.fit.backend.entities.JobSkill;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.backend.services.CompanyServices;
import vn.edu.iuh.fit.backend.services.JobServices;
import vn.edu.iuh.fit.backend.services.JobSkillServices;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("jobs")
public class JobController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CompanyServices companyServices;
    private final JobServices jobServices;
    private final JobSkillServices jobSkillServices;
    private final CompanyRepository companyRepository;

    public JobController(CompanyServices companyServices, JobServices jobServices, JobSkillServices jobSkillServices, CompanyRepository companyRepository) {
        this.companyServices = companyServices;
        this.jobServices = jobServices;
        this.jobSkillServices = jobSkillServices;
        this.companyRepository = companyRepository;
    }

    @GetMapping({"/manage", ""})
    public String manage(Model model, @RequestParam(name = "company-id", required = false, defaultValue = "0") long id) {
        List<Company> companies = companyServices.findAll();
        List<Job> jobs = jobServices.findByCompanyId(id);

        model.addAttribute("companies", companies);
        model.addAttribute("jobs", jobs);
        model.addAttribute("comId", id);

        return "jobs/jobManage";
    }

    @GetMapping("/{job-id}")
    public String detail(Model model, @PathVariable("job-id") long jobId) {
        Optional<Job> jobOptional = jobServices.findById(jobId);

        if (jobOptional.isEmpty())
            return "notFound";

        List<JobSkill> jobSkills = jobSkillServices.findByJobId(jobId);

        model.addAttribute("jobSkills", jobSkills);
        model.addAttribute("job", jobOptional.get());

        return "jobSkills/jobSkillManage";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Company> companies = companyRepository.findAll();

        Job job = new Job();
        Company company = new Company();

        job.setCompany(company);

        model.addAttribute("companies", companies);
        model.addAttribute("company", company);
        model.addAttribute("job", job);

        return "jobs/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("job") Job job, @ModelAttribute Company company) {
        job.setId(0);

        try {
            job.setCompany(company);
            jobServices.save(job);

            return "redirect:/jobs";
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return "jobs/add";
    }
}
