    private void write( PageCursor cursor, TreeState origin )
    {
        TreeState.write( cursor,
                origin.stableGeneration(),
                origin.unstableGeneration(),
                origin.rootId(),
                origin.rootGeneration(),
                origin.lastId(),
                origin.freeListWritePageId(),
                origin.freeListReadPageId(),
                origin.freeListWritePos(),
                origin.freeListReadPos(),
                origin.isClean() );
    }