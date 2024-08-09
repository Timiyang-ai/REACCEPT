public static CircuitBreakerClientBuilder builder(
            CircuitBreakerStrategyWithContent<HttpResponse> strategyWithContent) {
        return new CircuitBreakerClientBuilder(strategyWithContent);
    }