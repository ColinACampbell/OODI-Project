package com.hexagrammers.DamPlay.Services;

import com.hexagrammers.DamPlay.Models.Asset;
import com.hexagrammers.DamPlay.Models.AssetRecipient;
import com.hexagrammers.DamPlay.Repositories.AssetRecipientRepository;
import com.hexagrammers.DamPlay.Repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetManager {

    @Autowired
    private AssetRecipientRepository assetRecipientRepository;

    @Autowired
    private AssetRepository assetRepository;
    public void createAsset(Asset asset)
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
}
