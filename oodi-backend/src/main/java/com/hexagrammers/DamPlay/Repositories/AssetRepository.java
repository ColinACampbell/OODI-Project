package com.hexagrammers.DamPlay.Repositories;

import com.hexagrammers.DamPlay.Models.Asset;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssetRepository extends CrudRepository<Asset,Integer> {
    @Query(value = "SELECT * FROM assets WHERE sender_id = ?1",nativeQuery = true)
    List<Asset> findBySender(int userID);

    @Query(value = "select * from assets a \n" +
            "inner join asset_recipients ar ON a.id = ar.asset_id \n" +
            "where ar.user_id = ?1",nativeQuery = true)
    List<Asset> findByRecipient(int userID);
}
