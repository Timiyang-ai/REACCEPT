    @Test
    public void moveCenterTest() {
        MapViewPosition mapViewPosition = new MapViewPosition(new FixedTileSizeDisplayModel(256));
        mapViewPosition.moveCenter(
                MercatorProjection.getMapSize((byte) 0, new FixedTileSizeDisplayModel(256).getTileSize()) / -360d, 0);

        MapPosition mapPosition = mapViewPosition.getMapPosition();

        Assert.assertEquals(0, mapPosition.latLong.latitude, 0);
        Assert.assertEquals(1, mapPosition.latLong.longitude, 1.0E-14);
        Assert.assertEquals(0, mapPosition.zoomLevel);
    }