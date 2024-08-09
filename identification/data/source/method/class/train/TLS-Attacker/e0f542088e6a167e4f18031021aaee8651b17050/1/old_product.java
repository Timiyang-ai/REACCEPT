public void attack() {
        long currentTime = System.currentTimeMillis();
        switch (server) {
            case NORMAL:
                attackNormal();
                break;
            case ORACLE:
                attackOracle();
                break;
        }
        LOGGER.info("Time needed for the attack: {} seconds", ((System.currentTimeMillis() - currentTime) / 1000));
    }