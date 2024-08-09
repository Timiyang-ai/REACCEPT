TestItem iter() throws IOException {
      final byte[] f = ((TestSession) cs).exec(ServerCmd.RESULTS, id);
      final TestItem ti = new TestItem();
      ti.type = f[0];
      ti.result = Arrays.copyOfRange(f, 1, f.length);
      return ti;
    }