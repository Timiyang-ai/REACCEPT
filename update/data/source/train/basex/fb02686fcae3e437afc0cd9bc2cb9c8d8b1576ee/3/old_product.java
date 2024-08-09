TestItem full() throws IOException {
      final byte[] f = ((TestSession) cs).exec(ServerCmd.FULL, id);
      final TestItem ti = new TestItem();
      ti.type = f[0];
      final int fl = f.length;
      if(TYPES[ti.type].length > 3) {
        for(int b = 1; b < fl; b++) {
          if(f[b] == 0) {
            ti.uri = Arrays.copyOfRange(f, 1, b);
            ti.result = Arrays.copyOfRange(f, b + 1, fl);
            break;
          }
        }
        assertNotNull("No extended info: " + TYPES[ti.type][0], ti.uri);
      } else {
        ti.result = Arrays.copyOfRange(f, 1, fl);
      }
      return ti;
    }