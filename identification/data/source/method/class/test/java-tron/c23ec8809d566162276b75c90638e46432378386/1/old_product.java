public void init(ECKey ecKey) {
        this.ecKey = ecKey;
        address = this.ecKey.getAddress();

        logger.info("wallet address: {}", ByteArray.toHexString(address));
    }