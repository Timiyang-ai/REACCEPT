@Nullable
    public Script findRedeemScriptFromPubHash(byte[] payToScriptHash) {
        return marriedKeysScripts.get(ByteString.copyFrom(payToScriptHash));
    }