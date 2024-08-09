  private void process(Writer<GitRevision> writer, DummyRevision ref)
      throws ValidationException, RepoException, IOException {
    process(writer, destinationFiles, ref);
  }