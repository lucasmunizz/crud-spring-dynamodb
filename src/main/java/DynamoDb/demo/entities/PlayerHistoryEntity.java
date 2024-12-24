package DynamoDb.demo.entities;

import DynamoDb.demo.dto.ScoreDto;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.time.Instant;
import java.util.UUID;

@DynamoDbBean
public class PlayerHistoryEntity {

    private String username;

    private UUID gameId;

    private Double score;

    private Instant created_at;

    public static PlayerHistoryEntity fromScore(String username, ScoreDto scoreDto){

        var entity = new PlayerHistoryEntity();
        entity.setUsername(username);
        entity.setGameId(UUID.randomUUID());
        entity.setScore(scoreDto.score());
        entity.setCreated_at(Instant.now());

        return entity;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("username")
    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("game_id")
    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    @DynamoDbAttribute("score")
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @DynamoDbAttribute("created_at")
    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }
}
