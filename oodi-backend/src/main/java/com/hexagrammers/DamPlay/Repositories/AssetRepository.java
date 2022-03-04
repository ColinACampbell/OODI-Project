package com.hexagrammers.DamPlay.Repositories;

import com.hexagrammers.DamPlay.Models.Asset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetRepository extends CrudRepository<Asset,Integer> {

}
