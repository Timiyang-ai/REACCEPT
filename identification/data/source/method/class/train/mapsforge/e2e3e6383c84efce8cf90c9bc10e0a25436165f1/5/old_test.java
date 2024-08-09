    @Test
    public void getCenterPointTest() {
        BoundingBox boundingBox = new BoundingBox(MIN_LATITUDE, MIN_LONGITUDE, MAX_LATITUDE, MAX_LONGITUDE);
        LatLong centerPoint = boundingBox.getCenterPoint();
        Assert.assertEquals((MIN_LATITUDE + MAX_LATITUDE) / 2, centerPoint.latitude, 0);
        Assert.assertEquals((MIN_LONGITUDE + MAX_LONGITUDE) / 2, centerPoint.longitude, 0);
    }