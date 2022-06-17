package tech.kitucode.recommender.web.rest;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.kitucode.recommender.domain.Profile;
import tech.kitucode.recommender.exceptions.EntityNotFoundException;
import tech.kitucode.recommender.service.ProfileService;
import tech.kitucode.recommender.web.vm.ErrorVM;

@Log4j2
@RequestMapping("/api")
@RestController
public class ProfileResource {

    private final ProfileService profileService;

    public ProfileResource(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        log.info("REST request to get profile for user : {}", id);

        Profile profile = null;
        try {
            profile = profileService.findOneByGlobalUserId(id);
        } catch (EntityNotFoundException e) {
            ErrorVM errorVM = new ErrorVM(404, e.getMessage());
            return ResponseEntity.status(404).body(errorVM);
        }

        return ResponseEntity.ok().body(profile);
    }
}
