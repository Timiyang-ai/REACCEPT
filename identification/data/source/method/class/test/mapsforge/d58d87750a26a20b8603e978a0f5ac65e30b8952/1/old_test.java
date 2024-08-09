    @Test
    public void validateLatitudeTest() {
        LatLongUtils.validateLatitude(LatLongUtils.LATITUDE_MAX);
        LatLongUtils.validateLatitude(LatLongUtils.LATITUDE_MIN);

        verifyInvalidLatitude(Double.NaN);
        verifyInvalidLatitude(Math.nextAfter(LatLongUtils.LATITUDE_MAX, Double.POSITIVE_INFINITY));
        verifyInvalidLatitude(Math.nextAfter(LatLongUtils.LATITUDE_MIN, Double.NEGATIVE_INFINITY));
    }