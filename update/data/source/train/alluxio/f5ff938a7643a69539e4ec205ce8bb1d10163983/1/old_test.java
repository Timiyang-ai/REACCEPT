@Test
  public void cloneByteBufferListTest() {
    final int bufferSize = 10;
    final int listLength = 10;
    ArrayList<ByteBuffer> bufList = new ArrayList<ByteBuffer>(listLength);
    for (int k = 0; k < listLength; k++) {
      ByteBuffer buf = ByteBuffer.allocate(bufferSize);
      for (byte i = 0; i < bufferSize; i++) {
        buf.put((byte) (i + k));
      }
      bufList.add(buf);
    }
    List<ByteBuffer> bufListClone = BufferUtils.cloneByteBufferList(bufList);
    Assert.assertEquals(listLength, bufListClone.size());
    for (int k = 0; k < listLength; k++) {
      Assert.assertEquals(bufList.get(k), bufListClone.get(k));
    }
  }