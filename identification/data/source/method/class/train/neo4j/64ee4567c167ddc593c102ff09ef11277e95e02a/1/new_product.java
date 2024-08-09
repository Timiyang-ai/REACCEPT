static void write( PageCursor cursor, long stableGeneration, long unstableGeneration, long rootId,
            long rootGeneration, long lastId, long freeListWritePageId, long freeListReadPageId, int freeListWritePos,
            int freeListReadPos, boolean clean )
    {
        GenerationSafePointer.assertGenerationOnWrite( stableGeneration );
        GenerationSafePointer.assertGenerationOnWrite( unstableGeneration );

        writeStateOnce( cursor, stableGeneration, unstableGeneration, rootId, rootGeneration, lastId,
                freeListWritePageId, freeListReadPageId, freeListWritePos, freeListReadPos, clean ); // Write state
        writeStateOnce( cursor, stableGeneration, unstableGeneration, rootId, rootGeneration, lastId,
                freeListWritePageId, freeListReadPageId, freeListWritePos, freeListReadPos, clean ); // Write checksum
    }