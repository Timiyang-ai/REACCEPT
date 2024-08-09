public static synchronized double random() {
        return NoImagePreloadHolder.INSTANCE.nextDouble();
    }