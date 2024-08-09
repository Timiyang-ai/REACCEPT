@Test
    public void testGetBufferFor() throws IOException {
        CompositeByteBuf buf = (CompositeByteBuf) Unpooled.wrappedBuffer(new byte[] { 1, 2, 3, 4, 5 }, new byte[] {4, 5, 6, 7, 8, 9, 26});
    
        //Ensure that a random place will be fine
        assertEquals(buf.getBuffer(2).capacity(), 5);
        
        //Loop through each byte
        
        byte index = 0;
        
        while (index < buf.capacity()) {
            ByteBuf _buf = buf.getBuffer(index++);
            assertNotNull(_buf);
            assertTrue(_buf.capacity() > 0);
            assertNotNull(_buf.getByte(0));
            assertNotNull(_buf.getByte(_buf.readableBytes() - 1));
        }
    }