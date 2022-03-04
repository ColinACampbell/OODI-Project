package com.hexagrammers.DamPlay.Controllers;

import com.hexagrammers.DamPlay.Models.Asset;
import com.hexagrammers.DamPlay.Models.AssetRecipient;
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

@RestController
@RequestMapping("/api/asset")
public class AssetController {

    @Autowired
    UserManager userManager;

    @Autowired
    AssetManager assetManager;

    @GetMapping("")
    public String getAssets(@RequestBody Asset assetBody)
    {

        return "Asset";
    }

    @PostMapping("")
    public ResponseEntity<Asset> createAsset(@RequestBody HttpAssetBody assetBody, Authentication authentication)
    {

        Asset asset = new Asset(assetBody.getTitle(),assetBody.getDescription(),assetBody.getAssetLink(),assetBody.getReviewedBy());

        // Get the user from the authentication layer
        PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
        asset.setSender(userDetails.getUser());

        assetManager.createAsset(asset);

        for (int userId : assetBody.getAssetRecipients())
        {
            User user = userManager.findById(userId);
            if (user == null)
                continue;

            AssetRecipient assetRecipient = new AssetRecipient();
            assetRecipient.setRecipient(user);
            assetRecipient.setReceivedAsset(asset);

            assetManager.saveRecipient(assetRecipient);
            //asset.addRecipient(assetRecipient);
        }

        //assetManager.createAsset(asset);
        Asset newAsset = assetManager.getAsset(asset.getId());

        return new ResponseEntity<Asset>(newAsset, HttpStatus.CREATED);
    }
}
