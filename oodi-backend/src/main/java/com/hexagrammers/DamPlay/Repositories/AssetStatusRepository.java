package com.hexagrammers.DamPlay.Repositories;

import com.hexagrammers.DamPlay.Models.AssetStatus;
import com.hexagrammers.DamPlay.Models.AssetStatusHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetStatusRepository extends CrudRepository<AssetStatusHistory,Integer> {

}
