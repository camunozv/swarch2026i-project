package com.carlos.gamificationservice.services.servicesImpl;

import com.carlos.gamificationservice.dtos.dtosImpl.BooleanDTO;
import com.carlos.gamificationservice.services.LeaderBoardService;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;

public class LeaderBoardServiceImplementation implements LeaderBoardService {
    @Override
    public BooleanDTO incrementScore(String userId, int points) {
        return null;
    }

    @Override
    public List<ZSetOperations.TypedTuple<String>> getTopNUsers(Integer weekYear, int weekNumber) {
        return List.of();
    }

    @Override
    public Integer getScoreOfUser(String userId, Integer weekYear, Integer weekNumber) {
        return 0;
    }
}
