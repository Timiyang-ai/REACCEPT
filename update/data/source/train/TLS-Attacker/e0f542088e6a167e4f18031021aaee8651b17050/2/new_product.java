public BigInteger attack() {
        BigInteger result = null;
        long currentTime = System.currentTimeMillis();
        switch (server) {
            case NORMAL:
                result = attackNormal();
                break;
            case ORACLE:
                result = attackOracle();
                break;
        }
        LOGGER.info("Time needed for the attack: {} seconds", ((System.currentTimeMillis() - currentTime) / 1000));
        return result;
    }