  @Test
  public void sliceByteBuffer() {
    final int size = 100;
    final ByteBuffer buf = BufferUtils.getIncreasingByteBuffer(size);
    for (int slicePosition : new int[] {0, 1, size / 2, size - 1}) {
      // Slice a ByteBuffer of length 1
      ByteBuffer slicedBuffer = BufferUtils.sliceByteBuffer(buf, slicePosition, 1);
      assertEquals(0, slicedBuffer.position());
      assertEquals(1, slicedBuffer.limit());
      assertTrue(BufferUtils.equalIncreasingByteBuffer(slicePosition, 1, slicedBuffer));

      // Slice a ByteBuffer from the target position to the end
      int slicedBufferLength = size - slicePosition;
      ByteBuffer slicedBuffer1 = BufferUtils.sliceByteBuffer(buf, slicePosition,
          slicedBufferLength);
      ByteBuffer slicedBuffer2 = BufferUtils.sliceByteBuffer(buf, slicePosition);
      assertEquals(0, slicedBuffer1.position());
      assertEquals(0, slicedBuffer2.position());
      assertEquals(slicedBufferLength, slicedBuffer1.limit());
      assertEquals(slicedBufferLength, slicedBuffer2.limit());
      assertTrue(BufferUtils.equalIncreasingByteBuffer(slicePosition, slicedBufferLength,
          slicedBuffer1));
      assertTrue(BufferUtils.equalIncreasingByteBuffer(slicePosition, slicedBufferLength,
          slicedBuffer2));
    }
  }