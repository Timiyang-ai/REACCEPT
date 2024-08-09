TestResult full() throws IOException {
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

      final boolean uriResult = tr.uri != null, uriExpected = tr.uri != null;
      if(uriResult && !uriExpected) fail("No URI expected for " + TYPES[tr.type][0]);
      if(!uriResult && uriExpected) fail("URI expected for " + TYPES[tr.type][0]);
      return tr;
    }