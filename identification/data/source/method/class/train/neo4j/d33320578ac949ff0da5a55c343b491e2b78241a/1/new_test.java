    private static short checksumOf( GSP gsp )
    {
        return GenerationSafePointer.checksumOf( gsp.generation, gsp.pointer );
    }