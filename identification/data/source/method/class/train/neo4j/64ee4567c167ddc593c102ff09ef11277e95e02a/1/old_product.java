static void write( PageCursor cursor, long stableGeneration, long unstableGeneration, long rootId,
            long rootGeneration, long lastId, long freeListWritePageId, long freeListReadPageId, int freeListWritePos,
            int freeListReadPos )
    {
        GenerationSafePointer.assertGenerationOnWrite( stableGeneration );
        GenerationSafePointer.assertGenerationOnWrite( unstableGeneration );

        writeStateOnce( cursor, stableGeneration, unstableGeneration, rootId, rootGeneration, lastId,
                freeListWritePageId, freeListReadPageId, freeListWritePos, freeListReadPos ); // Write state
        writeStateOnce( cursor, stableGeneration, unstableGeneration, rootId, rootGeneration, lastId,
                freeListWritePageId, freeListReadPageId, freeListWritePos, freeListReadPos ); // Write checksum
    }