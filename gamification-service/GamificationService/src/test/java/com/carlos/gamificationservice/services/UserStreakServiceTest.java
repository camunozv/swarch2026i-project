package com.carlos.gamificationservice.services;

import com.carlos.gamificationservice.dtos.UserStreakMapper;
import com.carlos.gamificationservice.models.UserStreak;
import com.carlos.gamificationservice.repository.UserStreakRepository;
import com.carlos.gamificationservice.services.servicesImpl.UserStreakServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// KEY TAKEAWAY
// @ExtendWith(MockitoExtension.class) --> better for unit tests since it processes simple mocking annotations.
// @ExtendWith(SpringExtension.class) --> better for integration tests since it loads the full application context.
@ExtendWith(MockitoExtension.class)
public class UserStreakServiceTest {

    @Mock
    private UserStreakRepository userStreakRepository;

    @Mock
    private UserStreakMapper userStreakMapper;

    @InjectMocks
    private UserStreakServiceImplementation userStreakServiceImplementation;

    // userStreakRepository.getUserStreakByUserName(userName);
    // userStreakRepository.save(newUserActivity);
    // userStreakRepository.save(currentUserStreak);
    // userStreakMapper.toUserStreakDTO(userStreakRepository.getUserStreakByUserName(userName));

    @Test
    public void testRegisterUserActivity() throws Exception {

        LocalDate testDate = LocalDate.of(2024, 3, 11);
        UserStreak currentUserStreak = new UserStreak(1L,"Ninja_Poliglota", 12, 13, testDate);

        when(userStreakRepository.getUserStreakByUserName("Ninja_Poliglota")).thenReturn(currentUserStreak);
        when(userStreakRepository.save(currentUserStreak)).thenReturn(null);
        when(userStreakMapper.toUserStreakDTO(currentUserStreak));

        /*

        LocalDate today = LocalDate.now();

        UserStreak currentUserStreak = userStreakRepository.getUserStreakByUserName(userName);

        if (currentUserStreak == null) {
            UserStreak newUserActivity = new UserStreak();
            newUserActivity.setUserName(userName);
            newUserActivity.setCurrentStreak(1);
            newUserActivity.setLastDateOfActivity(today);
            newUserActivity.setLongestStreak(1);
            userStreakRepository.save(newUserActivity);
        } else {
            long daysBetween = ChronoUnit.DAYS.between(currentUserStreak.getLastDateOfActivity(), today);

            if (daysBetween == 1) {
                int currentStreak = currentUserStreak.getCurrentStreak();
                currentUserStreak.setCurrentStreak(currentStreak + 1);
                currentUserStreak.setLastDateOfActivity(today);
                if ((currentStreak + 1) > currentUserStreak.getLongestStreak()) {
                    currentUserStreak.setLongestStreak(currentStreak + 1);
                }
            } else if (daysBetween > 1) {
                currentUserStreak.setCurrentStreak(1);
                currentUserStreak.setLastDateOfActivity(today);
            }

            userStreakRepository.save(currentUserStreak);
        }

        return userStreakMapper.toUserStreakDTO(userStreakRepository.getUserStreakByUserName(userName));


        * */

    }
}
