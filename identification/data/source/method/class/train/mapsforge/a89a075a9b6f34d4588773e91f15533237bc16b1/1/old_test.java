    @Test
    public void zoomTest() {
        MapViewPosition mapViewPosition = new MapViewPosition(new DisplayModel());
        Assert.assertEquals(0, mapViewPosition.getZoomLevel());

        mapViewPosition.zoom((byte) 1);
        Assert.assertEquals(1, mapViewPosition.getZoomLevel());

        mapViewPosition.zoom((byte) -1);
        Assert.assertEquals(0, mapViewPosition.getZoomLevel());

        mapViewPosition.zoom((byte) 5);
        Assert.assertEquals(5, mapViewPosition.getZoomLevel());

        mapViewPosition.zoom((byte) -2);
        Assert.assertEquals(3, mapViewPosition.getZoomLevel());

        mapViewPosition.zoom(Byte.MAX_VALUE);
        Assert.assertEquals(Byte.MAX_VALUE, mapViewPosition.getZoomLevel());

        mapViewPosition.zoom(Byte.MIN_VALUE);
        Assert.assertEquals(0, mapViewPosition.getZoomLevel());
    }