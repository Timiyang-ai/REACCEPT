default Backoff withJitter(double minJitterRate, double maxJitterRate, Supplier<Random> randomSupplier) {
        return new JitterAddingBackoff(this, minJitterRate, maxJitterRate, randomSupplier);
    }