package com.hexagrammers.DamPlay.Repositories;

import com.hexagrammers.DamPlay.Models.AssetRecipient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface AssetRecipientRepository extends CrudRepository<AssetRecipient,Integer> {
    @Modifying()
    @Transactional()
    @Query(value = "delete from asset_recipients where asset_id = ?1",nativeQuery = true)
    public void deleteAssetRecipientsByAssetId(int assetID);
}
