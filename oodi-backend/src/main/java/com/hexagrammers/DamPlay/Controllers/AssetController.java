package com.hexagrammers.DamPlay.Controllers;

import com.hexagrammers.DamPlay.Models.Asset;
import com.hexagrammers.DamPlay.Models.AssetRecipient;
import com.hexagrammers.DamPlay.Models.Http.AssetResponseBody;
import com.hexagrammers.DamPlay.Models.Http.HttpAssetBody;
import com.hexagrammers.DamPlay.Models.PrincipalUserDetails;
import com.hexagrammers.DamPlay.Models.User;
import com.hexagrammers.DamPlay.Services.AssetManager;
import com.hexagrammers.DamPlay.Services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asset")
public class AssetController {

    @Autowired
    UserManager userManager;

    @Autowired
    AssetManager assetManager;

    @GetMapping("")
    public AssetResponseBody getAssets(Authentication authentication)
    {
        PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
        List<Asset> sentAssets = assetManager.getSentAssets(userDetails.getUser().getId());
        List<Asset> receivedAssets = assetManager.getReceivedAssets(userDetails.getUser().getId());

        return new AssetResponseBody(sentAssets,receivedAssets);
    }

    @GetMapping("{id}")
    public Asset getAsset(@PathVariable("id") int assetId)
    {
        return assetManager.getAsset(assetId);
    }

    @PutMapping("{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable("id") int assetId, @RequestBody() Asset assetBody)
    {
        Asset asset = assetManager.getAsset(assetId);

        if (asset == null) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST); // better response
        }

        asset.setTitle(assetBody.getTitle());
        asset.setDescription(assetBody.getDescription());
        asset.setReviewedBy(assetBody.getReviewedBy());

        System.out.println(assetBody.getStatus());
        asset.setStatus(assetBody.getStatus());

        assetManager.updateAsset(asset);

        return new ResponseEntity<>(asset,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Asset> createAsset(@RequestBody HttpAssetBody assetBody, Authentication authentication)
    {

        Asset asset = new Asset(assetBody.getTitle(),assetBody.getDescription(),assetBody.getAssetLink(),assetBody.getReviewedBy(), assetBody.getStatus());

        // Get the user from the authentication layer
        PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
        asset.setSender(userDetails.getUser());

        assetManager.updateAsset(asset);

        for (int userId : assetBody.getAssetRecipients())
        {
            User user = userManager.findById(userId);
            if (user == null)
                continue;

            AssetRecipient assetRecipient = new AssetRecipient();
            assetRecipient.setRecipient(user);
            assetRecipient.setReceivedAsset(asset);

            assetManager.saveRecipient(assetRecipient);
            asset.addRecipient(assetRecipient);
        }

        assetManager.updateAsset(asset);
        Asset newAsset = assetManager.getAsset(asset.getId()); // TODO: Reduce this transaction

        return new ResponseEntity<Asset>(newAsset, HttpStatus.CREATED);
    }
}
