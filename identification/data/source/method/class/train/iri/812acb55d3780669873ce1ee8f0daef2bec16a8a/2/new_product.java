public void update(Tangle tangle, Snapshot initialSnapshot, String item) throws Exception {
        getAddressHash();
        getTrunkTransactionHash();
        getBranchTransactionHash();
        getBundleHash();
        getTagValue();
        getObsoleteTagValue();
        setAttachmentData();
        setMetadata();
        if (initialSnapshot.hasSolidEntryPoint(hash)) {
            return;
        }
        tangle.update(transaction, hash, item);
    }