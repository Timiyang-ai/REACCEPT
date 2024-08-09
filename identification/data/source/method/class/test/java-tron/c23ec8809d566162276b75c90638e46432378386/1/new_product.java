public void init(ECKey ecKey) {
        this.ecKey = ecKey;
        address = this.ecKey.getAddress();

        LOGGER.info("wallet address: {}", ByteArray.toHexString(address));
    }