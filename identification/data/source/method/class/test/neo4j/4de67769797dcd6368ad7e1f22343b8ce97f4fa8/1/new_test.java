    private long write( PageSwapper swapper, int filePageId, long address ) throws IOException
    {
        return swapper.write( filePageId, address );
    }