package com.hexagrammers.DamPlay.Services;

import com.hexagrammers.DamPlay.AuthorizationException;
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

    @Autowired
    private FeedbackManager feedbackManager;

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

    public void deleteAsset(int assetID,int userID) throws AuthorizationException
    {
        Asset asset = assetRepository.findById(assetID).get();




        if (asset.getSender().getId() != userID)
        {
            throw new AuthorizationException("");
        } else
        {

            for (Feedback feedback : asset.getFeedbacks())
            {
                for (FeedbackReply feedbackReply : feedback.getReplies())
                {
                    feedbackManager.deleteFeedbackReply(feedbackReply.getId());
                }
                feedbackManager.deleteFeedback(feedback.getId());
            }

            assetRecipientRepository.deleteAssetRecipientsByAssetId(assetID);
            assetStatusRepository.deleteAssetHistriesByAssetId(assetID);
            assetRepository.deleteById(assetID);
        }
    }

}
