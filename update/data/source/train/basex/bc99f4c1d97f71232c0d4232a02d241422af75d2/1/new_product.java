TestResult full(final Object[] exp) throws IOException {
      final byte[] result = ((TestSession) cs).exec(ServerCmd.FULL, id);
      final TestResult tr = new TestResult();
      tr.type = result[0];
      final int rl = result.length, b = Token.indexOf(result, 0);
      if(b != -1) {
        // result includes URI
        tr.uri = Arrays.copyOfRange(result, 1, b);
        tr.result = Arrays.copyOfRange(result, b + 1, rl);
      } else {
        tr.result = Arrays.copyOfRange(result, 1, rl);
      }

      final boolean expected = exp.length > 3;
      if(tr.uri == null) {
        if(expected) fail("URI expected for " + TYPES[tr.type][0]);
      } else if(!expected) {
        fail("No URI expected for " + TYPES[tr.type][0] + ": " + Token.string(tr.result));
      }
      return tr;
    }