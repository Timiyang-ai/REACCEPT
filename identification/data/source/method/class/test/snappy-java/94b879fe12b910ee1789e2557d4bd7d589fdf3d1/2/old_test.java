    @Test
    public void load()
            throws Exception
    {
        SnappyLoader.loadSnappyApi();
        _logger.debug(Snappy.maxCompressedLength(1024));
    }