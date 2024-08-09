default Backoff withJitter(double minJitterRate, double maxJitterRate) {
        return withJitter(minJitterRate, maxJitterRate, ThreadLocalRandom::current);
    }