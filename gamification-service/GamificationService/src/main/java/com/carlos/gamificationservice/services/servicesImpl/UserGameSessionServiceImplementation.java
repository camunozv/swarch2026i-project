package com.carlos.gamificationservice.services.servicesImpl;

import com.carlos.gamificationservice.dtos.UserGameSessionMapper;
import com.carlos.gamificationservice.dtos.dtosImpl.UserGameSessionDTO;
import com.carlos.gamificationservice.models.UserGameSession;
import com.carlos.gamificationservice.repository.UserGameSessionRepository;
import com.carlos.gamificationservice.services.UserGameSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGameSessionServiceImplementation implements UserGameSessionService {

    private final UserGameSessionRepository userGameSessionRepository;
    private final UserGameSessionMapper userGameSessionMapper;

    @Override
    public boolean saveUserGameSession(UserGameSessionDTO newUserGameSession) {

        boolean result;

        try {
            UserGameSession userGameSession = userGameSessionMapper.toUserGameSession(newUserGameSession);
            userGameSessionRepository.save(userGameSession); // Saves new game session data into the database.
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    @Override
    @Transactional
    public boolean deleteAllUserGameSessions(String userName) {
        boolean result;

        try {
            userGameSessionRepository.deleteUserGameSessionsByUserName(userName);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    @Override
    @Transactional
    public boolean deleteAllUserGameSessionsPerDate(String userName, LocalDate intendedDate) {
        boolean result;

        try {
            userGameSessionRepository.deleteByUserNameAndSessionDate(userName, intendedDate);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    @Override
    public List<UserGameSession> getAllUserGameSessions(String userName) {
        return userGameSessionRepository.getAllByUserName(userName);
    }

    @Override
    public List<UserGameSession> getAllUserGameSessionsPerDate(String userName, LocalDate intendedDate) {
        return userGameSessionRepository.getAllByUserNameAndSessionDate(userName, intendedDate);
    }

    @Override
    public List<UserGameSession> getAllUserGameSessionsPerPoints(String userName, Integer intendedPoints) {
        return userGameSessionRepository.getAllByUserNameAndPoints(userName, intendedPoints);
    }
}
