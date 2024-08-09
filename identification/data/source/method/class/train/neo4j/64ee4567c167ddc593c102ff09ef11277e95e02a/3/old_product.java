static void write( PageCursor cursor, long stableGeneration, long unstableGeneration, long rootId, long rootGen,
            long lastId, long freeListWritePageId, long freeListReadPageId, int freeListWritePos,
            int freeListReadPos )
    {
        GenSafePointer.assertGenerationOnWrite( stableGeneration );
        GenSafePointer.assertGenerationOnWrite( unstableGeneration );

        writeStateOnce( cursor, stableGeneration, unstableGeneration, rootId, rootGen, lastId,
                freeListWritePageId, freeListReadPageId, freeListWritePos, freeListReadPos ); // Write state
        writeStateOnce( cursor, stableGeneration, unstableGeneration, rootId, rootGen, lastId,
                freeListWritePageId, freeListReadPageId, freeListWritePos, freeListReadPos ); // Write checksum
    }