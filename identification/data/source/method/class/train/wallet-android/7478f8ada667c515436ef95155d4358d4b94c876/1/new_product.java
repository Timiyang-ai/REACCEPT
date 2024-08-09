public static byte[] toBytes(String hexString) {
      if(hexString != null) {
         hexString = hexString.replace(" ", "");
      }
      if (hexString == null || hexString.length() % 2 != 0) {
         throw new RuntimeException("Input string must contain an even number of characters");
      }
      char[] hex = hexString.toCharArray();
      int length = hex.length / 2;
      byte[] raw = new byte[length];
      for (int i = 0; i < length; i++) {
         int high = Character.digit(hex[i * 2], 16);
         int low = Character.digit(hex[i * 2 + 1], 16);
         if (high < 0 || low < 0){
            throw new RuntimeException("Invalid hex digit " + hex[i * 2] + hex[i * 2 + 1]);
         }
         int value = (high << 4) | low;
         if (value > 127)
            value -= 256;
         raw[i] = (byte) value;
      }
      return raw;
   }