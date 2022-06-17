package tech.kitucode.recommender.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.kitucode.recommender.domain.Profile;
import tech.kitucode.recommender.exceptions.EntityNotFoundException;
import tech.kitucode.recommender.repository.ProfileRepository;

import java.util.Optional;

@Log4j2
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile findOneByGlobalUserId(Long globalUserId) throws EntityNotFoundException {
        log.info("Request to find profile by global user id : {}", globalUserId);

        Optional<Profile> optionalProfile = profileRepository.findOneByGlobalUserId(globalUserId);

        if (optionalProfile.isEmpty()) {
            throw new EntityNotFoundException("Profile with the specified global user id does not exist");
        }

        return optionalProfile.get();
    }

    public Profile save(Profile profile) {
        log.info("Request to save profile : {}", profile);

        return profileRepository.save(profile);
    }
}
