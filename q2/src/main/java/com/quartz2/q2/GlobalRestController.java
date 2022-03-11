package com.quartz2.q2;

import java.util.List;

// import com.quartz2.q2.Fruit;
// import com.quartz2.q2.JobData;
// import com.quartz2.q2.GlobalRestService;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class GlobalRestController {
    
    @Autowired
    GlobalRestService globalRestService;

    @PostMapping("/fruits")
    public ResponseEntity<Fruit> saveFruit(@RequestBody Fruit fruit) {
        Fruit savedFruit = globalRestService.saveFruit(fruit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFruit);
    }

    @GetMapping("/fruits")
    public ResponseEntity<List<Fruit>> findAllFruits(){
        List<Fruit> allFruits = globalRestService.findAllFruits();
        return ResponseEntity.status(HttpStatus.OK).body(allFruits);
    }

    @GetMapping("/fruits/count/{name}")
    public ResponseEntity<Long> countByNameFruits(@PathVariable  String name){
        long count = globalRestService.countByFruitName(name);
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

    @DeleteMapping("/fruits/{fruitId}")
    public ResponseEntity<Void> deleteByFruitId( @PathVariable Long fruitId ){
        globalRestService.deleteFruit(fruitId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/go")
    @ResponseBody
    public ResponseEntity<Void> schedule( @RequestBody JobData data ){
        globalRestService.schedule(data);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
