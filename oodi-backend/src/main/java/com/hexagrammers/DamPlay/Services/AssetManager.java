package com.hexagrammers.DamPlay.Services;

import com.hexagrammers.DamPlay.Models.*;
import com.hexagrammers.DamPlay.Repositories.AssetRecipientRepository;
import com.hexagrammers.DamPlay.Repositories.AssetRepository;
import com.hexagrammers.DamPlay.Repositories.AssetStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AssetManager {

    @Autowired
    private AssetRecipientRepository assetRecipientRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private AssetStatusRepository assetStatusRepository;

    public void updateAsset(Asset asset)
    {
        assetRepository.save(asset);
    }


    public void saveRecipient(AssetRecipient recipient)
    {
        assetRecipientRepository.save(recipient);
    }

    public Asset getAsset(int id)
    {
        return  assetRepository.findById(id).get();
    }

    public List<Asset> getSentAssets(int userID)
    {
        return (List<Asset>) assetRepository.findBySender(userID);
    }

    public List<Asset> getReceivedAssets(int userID)
    {
        return (List<Asset>) assetRepository.findByRecipient(userID);
    }

    public void addAssetStatusHistory(AssetStatus status, Asset asset, User user)
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AssetStatusHistory assetStatusHistory = new AssetStatusHistory(asset,status,timestamp.getTime(),user);
        assetStatusRepository.save(assetStatusHistory);
        asset.addHistory(assetStatusHistory);
        assetRepository.save(asset);
    }

}
