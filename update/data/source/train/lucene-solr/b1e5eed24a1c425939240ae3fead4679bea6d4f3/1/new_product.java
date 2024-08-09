public BytesRef get(int ord, BytesRef ref) {
    assert bytesStart != null : "bytesStart is null - not initialized";
    assert ord < bytesStart.length: "ord exceeeds byteStart len: " + bytesStart.length;
    return pool.setBytesRef(ref, bytesStart[ord]);
  }