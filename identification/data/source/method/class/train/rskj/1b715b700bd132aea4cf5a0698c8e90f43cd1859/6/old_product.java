public Federation buildFederation(Instant creationTime, long blockNumber, NetworkParameters btcParams) {
        if (!this.isComplete()) {
            throw new IllegalStateException("PendingFederation is incomplete");
        }

        return new Federation(
                publicKeys,
                creationTime,
                blockNumber,
                btcParams
        );
    }