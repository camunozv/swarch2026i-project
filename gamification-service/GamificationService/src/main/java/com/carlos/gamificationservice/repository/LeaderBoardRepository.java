package com.carlos.gamificationservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class LeaderBoardRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private static final long TTL_DAYS = 8;
    private static final long TOP_N = 100;

    // ── Key builder ──────────────────────────────────────────────────────────

    public String buildKey(int weekYear, int weekNumber) {
        return String.format("leaderboard:weekly:%d:%02d", weekYear, weekNumber);
    }

    public String currentWeekKey() {
        LocalDate now = LocalDate.now();
        return buildKey(
                now.get(IsoFields.WEEK_BASED_YEAR),
                now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)
        );
    }

    // ── Write operations ─────────────────────────────────────────────────────

    /**
     * Add XP to a user's score for the current week.
     * Creates the entry automatically if it doesn't exist yet.
     */
    public void incrementScore(String userId, int xp) {
        String key = currentWeekKey();
        redisTemplate.opsForZSet().incrementScore(key, userId, xp);
        // Reset TTL on every write to keep active leaderboards alive
        redisTemplate.expire(key, TTL_DAYS, TimeUnit.DAYS);
    }

    // ── Read operations ──────────────────────────────────────────────────────

    /**
     * Get top N users for a given week, ordered by XP descending.
     * Returns a list of (userId, score) pairs.
     */
    public List<ZSetOperations.TypedTuple<String>> getTopN(int weekYear, int weekNumber) {
        String key = buildKey(weekYear, weekNumber);
        Set<ZSetOperations.TypedTuple<String>> results =
                redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, TOP_N - 1);

        return results != null ? new ArrayList<>(results) : List.of();
    }

    /**
     * Get a user's rank in a given week (1-indexed, higher XP = lower rank number).
     * Returns -1 if the user has no score that week.
     */
    public long getRank(String userId, int weekYear, int weekNumber) {
        String key = buildKey(weekYear, weekNumber);
        Long rank = redisTemplate.opsForZSet().reverseRank(key, userId);
        return rank != null ? rank + 1 : -1; // convert 0-indexed to 1-indexed
    }

    /**
     * Get a specific user's score for a given week.
     * Returns 0 if the user has no score that week.
     */
    public int getScore(String userId, int weekYear, int weekNumber) {
        String key = buildKey(weekYear, weekNumber);
        Double score = redisTemplate.opsForZSet().score(key, userId);
        return score != null ? score.intValue() : 0;
    }
}
