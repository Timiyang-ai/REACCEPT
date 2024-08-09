public void serialize(OutputStream rawOutput) throws IOException {
    DataOutputStream output = new DataOutputStream(rawOutput);

    output.writeInt(VERSION);

    output.writeUTF(key.toString());

    output.writeInt(headers.size());
    for (String header : headers) {
      output.writeUTF(header);
    }

    output.writeInt(hashes.size());
    for (Pair<Integer, HashCode> hash : hashes) {
      output.writeInt(hash.getFirst());
      output.writeUTF(hash.getSecond().toString());
    }

    output.writeInt(entries.size());
    for (Pair<RuleKey, int[]> entry : entries) {
      output.writeInt(entry.getSecond().length);
      for (int hashIndex : entry.getSecond()) {
        output.writeInt(hashIndex);
      }
      output.writeUTF(entry.getFirst().toString());
    }
  }