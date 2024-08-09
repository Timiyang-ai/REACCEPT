    @Test
    public void validateLongitudeTest() {
        LatLongUtils.validateLongitude(LatLongUtils.LONGITUDE_MAX);
        LatLongUtils.validateLongitude(LatLongUtils.LONGITUDE_MIN);

        verifyInvalidLongitude(Double.NaN);
        verifyInvalidLongitude(Math.nextAfter(LatLongUtils.LONGITUDE_MAX, Double.POSITIVE_INFINITY));
        verifyInvalidLongitude(Math.nextAfter(LatLongUtils.LONGITUDE_MIN, Double.NEGATIVE_INFINITY));
    }