package com.hexagrammers.DamPlay.Services;

import com.hexagrammers.DamPlay.Models.Asset;
import com.hexagrammers.DamPlay.Models.AssetRecipient;
import com.hexagrammers.DamPlay.Repositories.AssetRecipientRepository;
import com.hexagrammers.DamPlay.Repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetManager {

    @Autowired
    private AssetRecipientRepository assetRecipientRepository;

    @Autowired
    private AssetRepository assetRepository;
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

}
