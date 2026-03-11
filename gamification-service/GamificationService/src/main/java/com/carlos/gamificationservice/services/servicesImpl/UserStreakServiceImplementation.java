package com.carlos.gamificationservice.services.servicesImpl;

import com.carlos.gamificationservice.dtos.dtosImpl.UserStreakDTO;
import com.carlos.gamificationservice.models.UserStreak;
import com.carlos.gamificationservice.repository.UserStreakRepository;
import com.carlos.gamificationservice.services.UserStreakService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class UserStreakServiceImplementation implements UserStreakService {

    UserStreakRepository userStreakRepository;

    public UserStreakServiceImplementation() {
    }

    @Override
    public UserStreakDTO registerUserActivity(UserStreak newUserActivity) {

        LocalDate today = LocalDate.now();

        UserStreak currentUserStreak = userStreakRepository.getUserStreakByUserName(newUserActivity.getUserName());

        if (currentUserStreak == null) {
            userStreakRepository.save(newUserActivity);
        } else {
            long daysBetween = ChronoUnit.DAYS.between(today, currentUserStreak.getLastDateOfActivity());

            if (daysBetween == 1) {
               currentUserStreak.setCurrentStreak(currentUserStreak.getCurrentStreak() + 1);
            }
        }

        return new UserStreakDTO();
    }

    /*

    /*

    def register_activity(user):
        if user.last_practice_date is None:
            user.current_streak = 1
        else:
            delta = today - user.last_practice_date
            if delta == timedelta(days=1):
                user.current_streak = (user.current_streak or 0) + 1
            elif delta == timedelta(days=0):
                pass
            else:
                user.current_streak = 1

        user.last_practice_date = today

        if (user.longest_streak or 0) < user.current_streak:
            user.longest_streak = user.current_streak

        user.save(update_fields=["current_streak", "longest_streak", "last_practice_date"])

        # get or create daily stat
        daily, created = DailyStatistic.objects.get_or_create(
            user=user,
            date=today,
        )

        daily.streak_maintained = user.current_streak > 1
        daily.save(update_fields=["streak_maintained"])

        try:
            AchievementService.check_streak_achievements(user)
        except Exception:
            pass

        return {
            "streak": user.current_streak,
            "best_streak": user.longest_streak
        }


    * */
}
