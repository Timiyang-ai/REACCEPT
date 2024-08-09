    @Test
    public void zoomOutTest() {
        MapViewPosition mapViewPosition = new MapViewPosition(new DisplayModel());
        mapViewPosition.setZoomLevel((byte) 1);
        Assert.assertEquals(1, mapViewPosition.getZoomLevel());
        mapViewPosition.zoomOut();
        Assert.assertEquals(0, mapViewPosition.getZoomLevel());

        mapViewPosition.setZoomLevel((byte) 0);
        Assert.assertEquals(0, mapViewPosition.getZoomLevel());
        mapViewPosition.zoomOut();
        Assert.assertEquals(0, mapViewPosition.getZoomLevel());
    }