package DynamoDb.demo.controller;

import DynamoDb.demo.dto.ScoreDto;
import DynamoDb.demo.entities.PlayerHistoryEntity;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/players")
public class PlayerController {

    private DynamoDbTemplate dynamoDbTemplate;

    public PlayerController(DynamoDbTemplate dynamoDbTemplate){
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @PostMapping("/{username}/games")
    public ResponseEntity<Void> save(@PathVariable("username") String username, @RequestBody ScoreDto scoreDto){

        var entity = PlayerHistoryEntity.fromScore(username, scoreDto);

        dynamoDbTemplate.save(entity);

        return ResponseEntity.ok().build();
    }
}
