    private double getRandomLatitude() {
        return tileSystem.getRandomLatitude(random.nextDouble(), tileSystem.getMinLatitude());
    }