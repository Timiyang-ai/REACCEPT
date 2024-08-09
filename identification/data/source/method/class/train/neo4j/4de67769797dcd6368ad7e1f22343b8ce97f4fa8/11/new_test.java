    private long read( PageSwapper swapper, int filePageId, int bufferSize, long address ) throws IOException
    {
        return swapper.read( filePageId, address, bufferSize );
    }