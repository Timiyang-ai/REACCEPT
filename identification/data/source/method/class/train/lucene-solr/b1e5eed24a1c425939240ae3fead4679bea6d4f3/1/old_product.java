public BytesRef get(int ord) {
    assert bytesStart != null : "bytesStart is null - not initialized";
    assert ord < bytesStart.length: "ord exceeeds byteStart len: " + bytesStart.length;
    return pool.setBytesRef(scratch1, bytesStart[ord]);
  }