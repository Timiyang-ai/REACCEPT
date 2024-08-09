public static byte[][] split(byte[] data, int dataOffset, int len) {
    ArrayList<Integer> offsets = new ArrayList<>();

    for (int i = dataOffset; i < (dataOffset + len); i++) {
      if (data[i] == 0x00) {
        offsets.add(i);
      }
    }

    offsets.add(dataOffset + len);

    byte[][] ret = new byte[offsets.size()][];

    int index = dataOffset;
    for (int i = 0; i < offsets.size(); i++) {
      ret[i] = new byte[offsets.get(i) - index];
      System.arraycopy(data, index, ret[i], 0, ret[i].length);
      index = offsets.get(i) + 1;
    }

    return ret;
  }