public static List<ByteBuffer> cloneByteBufferList(List<ByteBuffer> source) {
    List<ByteBuffer> ret = new ArrayList<ByteBuffer>(source.size());
    for (ByteBuffer b : source) {
      ret.add(cloneByteBuffer(b));
    }
    return ret;
  }