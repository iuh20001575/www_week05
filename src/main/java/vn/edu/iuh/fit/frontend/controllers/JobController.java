package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.entities.Company;
import vn.edu.iuh.fit.backend.entities.Job;
import vn.edu.iuh.fit.backend.entities.JobSkill;
import vn.edu.iuh.fit.backend.services.JobServices;
import vn.edu.iuh.fit.backend.services.JobSkillServices;
import vn.edu.iuh.fit.backend.services.CompanyServices;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("jobs")
public class JobController {
    @Autowired
    private CompanyServices companyServices;
    @Autowired
    private JobServices jobServices;
    @Autowired
    private JobSkillServices jobSkillServices;

    @GetMapping("/manage")
    public String manage(Model model, @RequestParam(name = "company-id", required = false, defaultValue = "0") long id) {
        List<Company> companies = companyServices.findAll();
        List<Job> jobs = jobServices.findByCompanyId(id);

        model.addAttribute("companies", companies);
        model.addAttribute("jobs", jobs);

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
}
