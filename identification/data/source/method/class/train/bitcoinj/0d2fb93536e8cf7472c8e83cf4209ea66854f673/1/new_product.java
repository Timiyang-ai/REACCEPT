@Nullable
    public RedeemData findRedeemDataFromScriptHash(byte[] scriptHash) {
        return marriedKeysRedeemData.get(ByteString.copyFrom(scriptHash));
    }