package com.carlos.gamificationservice.services;

import com.carlos.gamificationservice.dtos.dtosImpl.BooleanDTO;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;


public interface LeaderBoardService {

    BooleanDTO incrementScore(String userId, int points);
    List<ZSetOperations.TypedTuple<String>> getTopNUsers(Integer weekYear, int weekNumber);
    Integer getScoreOfUser(String userId, Integer weekYear, Integer weekNumber);

}
