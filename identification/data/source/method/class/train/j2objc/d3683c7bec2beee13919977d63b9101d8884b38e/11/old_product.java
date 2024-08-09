public static double random() {
        Random rnd = randomNumberGenerator;
        if (rnd == null) rnd = initRNG();
        return rnd.nextDouble();
    }