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

        TransactionViewModel cachedTvm = tangle.getCache(TransactionViewModel.class).get(hash);
        if (cachedTvm != null) {
            this.isCacheEntryFresh = false;
        }
        cachePut(tangle, this, hash);
    }