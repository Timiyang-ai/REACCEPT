    @Test
    public void deserialize() throws Exception {
        final ChunkHeaderTBaseDeserializer deserializer = new ChunkHeaderTBaseDeserializer(DEFAULT_PROTOCOL_FACTORY, DEFAULT_TBASE_LOCATOR);

        UnsafeByteArrayOutputStream out = new UnsafeByteArrayOutputStream();
        ChunkHeaderBufferedTBaseSerializer serializer = new ChunkHeaderBufferedTBaseSerializer(out, DEFAULT_PROTOCOL_FACTORY, DEFAULT_TBASE_LOCATOR);
        TSpanChunk chunk = new TSpanMockBuilder().buildChunk(3, 10);
        serializer.add(chunk);

        List<Message<TBase<?, ?>>> list = deserializer.deserialize(serializer.getTransport().getBuffer(), 0, serializer.getTransport().getBufferPosition());
        assertEquals(1, list.size());
        TSpanChunk result = (TSpanChunk) list.get(0).getData();
        assertEquals(3, result.getSpanEventList().size());
    }