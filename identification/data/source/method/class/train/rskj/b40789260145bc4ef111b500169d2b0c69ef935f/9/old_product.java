public void startPunishment(long expirationTime) {
        this.goodReputation = false;
        this.punishmentTime = expirationTime;
        this.punishmentCounter++;
        this.timeLostGoodReputation = System.currentTimeMillis();
    }