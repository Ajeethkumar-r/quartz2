package com.quartz2.q2;

import java.util.List;

// import com.quartz2.q2.entities.Fruit;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FruitRepo extends CrudRepository<Fruit, Long>  {
    public long countByFruitName( String fruitName );

    @Query( value="select distinct fruit_name from Fruit", nativeQuery = true )
    public List<String> findUniqueFruitName();
}
