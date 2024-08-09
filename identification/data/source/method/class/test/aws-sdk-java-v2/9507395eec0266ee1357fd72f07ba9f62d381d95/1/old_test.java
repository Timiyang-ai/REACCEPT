    @Test
    @Ignore // Does not pass anymore because the builder will create a duplicate of the BB
    public void toAttributeValue_ByteBuffer() {
        ByteBuffer bbFrom = ByteBuffer.allocate(10);
        AttributeValue av = InternalUtils.toAttributeValue(bbFrom);
        ByteBuffer bbTo = av.b().asByteBuffer();
        assertSame(bbFrom, bbTo);
    }