package com.hexagrammers.DamPlay.Repositories;

import com.hexagrammers.DamPlay.Models.AssetStatus;
import com.hexagrammers.DamPlay.Models.AssetStatusHistory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AssetStatusRepository extends CrudRepository<AssetStatusHistory,Integer> {
    @Modifying()
    @Transactional()
    @Query(value = "delete from asset_status_history where asset_id = ?1",nativeQuery = true)
    public void deleteAssetHistriesByAssetId(int assetID);
}
