TestResult iter() throws IOException {
      final byte[] result = ((TestSession) cs).exec(ServerCmd.RESULTS, id);
      final TestResult tr = new TestResult();
      tr.type = result[0];
      tr.result = Arrays.copyOfRange(result, 1, result.length);
      return tr;
    }