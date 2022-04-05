package com.hexagrammers.DamPlay.Models.Http;

import com.hexagrammers.DamPlay.Models.Asset;

import java.util.List;

public class AssetResponseBody {

    public List<Asset> sent;
    public List<Asset> received;

    public AssetResponseBody(List<Asset> sent, List<Asset> received)
    {
        this.sent = sent;
        this.received = received;
    }

    public List<Asset> getReceived() {
        return received;
    }

    public List<Asset> getSent() {
        return sent;
    }
}
