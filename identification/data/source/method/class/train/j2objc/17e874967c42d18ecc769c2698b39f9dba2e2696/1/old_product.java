public static synchronized double random() {
        if (random == null) {
            random = new Random();
        }
        return random.nextDouble();
    }