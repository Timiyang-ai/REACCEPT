    @Test
    public void zoomInTest() {
        MapViewPosition mapViewPosition = new MapViewPosition(new DisplayModel());
        Assert.assertEquals(0, mapViewPosition.getZoomLevel());
        mapViewPosition.zoomIn();
        Assert.assertEquals((byte) 1, mapViewPosition.getZoomLevel());

        mapViewPosition.setZoomLevel(Byte.MAX_VALUE);
        Assert.assertEquals(Byte.MAX_VALUE, mapViewPosition.getZoomLevel());
        mapViewPosition.zoomIn();
        Assert.assertEquals(Byte.MAX_VALUE, mapViewPosition.getZoomLevel());
    }