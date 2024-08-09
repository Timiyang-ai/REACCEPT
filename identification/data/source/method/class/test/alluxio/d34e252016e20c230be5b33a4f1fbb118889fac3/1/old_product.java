public static String byteArrayToHexString(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      sb.append(String.format("0x%02X", bytes[i]));
      if (i != bytes.length - 1) {
        sb.append(" ");
      }
    }
    return sb.toString();
  }