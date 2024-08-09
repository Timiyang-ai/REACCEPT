public String toStringEntries() {
    StringBuilder sb = new StringBuilder();
    RocksIterator inodeIter = mDb.newIterator(mInodesColumn);
    inodeIter.seekToFirst();
    while (inodeIter.isValid()) {
      MutableInode<?> inode;
      try {
        inode = MutableInode.fromProto(InodeMeta.Inode.parseFrom(inodeIter.value()));
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      sb.append("Inode " + Longs.fromByteArray(inodeIter.key()) + ": " + inode + "\n");
      inodeIter.next();
    }
    inodeIter.close();
    RocksIterator edgeIter = mDb.newIterator(mEdgesColumn);
    edgeIter.seekToFirst();
    while (edgeIter.isValid()) {
      byte[] key = edgeIter.key();
      byte[] id = new byte[Longs.BYTES];
      byte[] name = new byte[key.length - Longs.BYTES];
      System.arraycopy(key, 0, id, 0, Longs.BYTES);
      System.arraycopy(key, Longs.BYTES, name, 0, key.length - Longs.BYTES);
      sb.append(String.format("<%s,%s>->%s%n", Longs.fromByteArray(id), new String(name),
          Longs.fromByteArray(edgeIter.value())));
      edgeIter.next();
    }
    return sb.toString();
  }