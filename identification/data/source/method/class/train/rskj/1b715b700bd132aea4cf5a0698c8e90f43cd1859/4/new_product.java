public Federation buildFederation(Instant creationTime, NetworkParameters btcParams) {
        if (!this.isComplete()) {
            throw new IllegalStateException("PendingFederation is incomplete");
        }

        return new Federation(
                calculateThreshold(),
                publicKeys,
                creationTime,
                btcParams
        );
    }