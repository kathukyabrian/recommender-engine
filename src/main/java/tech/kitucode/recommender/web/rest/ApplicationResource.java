package tech.kitucode.recommender.web.rest;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.kitucode.recommender.domain.Application;
import tech.kitucode.recommender.exceptions.EntityNotFoundException;
import tech.kitucode.recommender.service.ApplicationService;
import tech.kitucode.recommender.service.dto.ApplicationDTO;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
public class ApplicationResource {

    private final ApplicationService applicationService;

    public ApplicationResource(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/applications")
    public List<ApplicationDTO> getAll() {
        log.debug("REST request to find all applications");

        return applicationService.getAll();
    }

    @GetMapping("/applications/{id}")
    public ApplicationDTO findOne(@PathVariable Long id) {
        log.debug("REST request to find application by id : {}", id);

        ApplicationDTO applicationDTO = null;
        try {
            applicationDTO = applicationService.getOne(id);
        } catch (EntityNotFoundException e) {
            // to do error handling here
            e.printStackTrace();
        }

        return applicationDTO;
    }

    @PostMapping("/applications")
    public ApplicationDTO save(@RequestBody Application application) {
        log.debug("REST request to save application");

        return applicationService.save(application);
    }

    @PutMapping("/applications")
    public ApplicationDTO update(@RequestBody Application application) {
        log.debug("REST request to update application");

        return applicationService.update(application);
    }

    @DeleteMapping("/applications/{id}")
    public void deleteOne(@PathVariable Long id) {
        log.debug("REST request to delete application with id : {}", id);

        applicationService.deleteOne(id);
    }
}
