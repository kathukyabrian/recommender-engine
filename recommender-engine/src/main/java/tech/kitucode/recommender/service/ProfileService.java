package tech.kitucode.recommender.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.kitucode.recommender.domain.Profile;
import tech.kitucode.recommender.exceptions.EntityNotFoundException;
import tech.kitucode.recommender.repository.ProfileRepository;
import tech.kitucode.recommender.service.dto.ProfileDTO;
import tech.kitucode.recommender.service.mapper.ProfileMapper;

import java.util.Optional;

@Log4j2
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileService(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    public ProfileDTO findOneByGlobalUserId(Long globalUserId) throws EntityNotFoundException {
        log.info("Request to find profile by global user id : {}", globalUserId);

        Optional<Profile> optionalProfile = profileRepository.findOneByGlobalUserId(globalUserId);

        if (optionalProfile.isEmpty()) {
            throw new EntityNotFoundException("Profile with the specified global user id does not exist");
        }

        Profile profile = optionalProfile.get();

        return profileMapper.toDTO(profile);
    }

    public Profile findOneEntityByGlobalUserId(Long globalUserId) throws EntityNotFoundException {
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
