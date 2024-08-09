public Federation buildFederation(Instant creationTime, NetworkParameters btcParams) {
        if (!this.isComplete()) {
            throw new IllegalStateException("PendingFederation is incomplete");
        }

        return new Federation(
                numberOfSignaturesRequired,
                publicKeys,
                creationTime,
                btcParams
        );
    }