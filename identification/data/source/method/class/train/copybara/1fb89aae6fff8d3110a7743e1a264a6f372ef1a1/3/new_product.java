@Override
    public void process(String output, boolean newPush, GitRepository alternateRepo) {
      try {
        String sha1 = alternateRepo.parseRef("HEAD");
        structuredOutput.getCurrentSummaryLineBuilder()
            .setDestinationRef(sha1)
            .setSummary(String.format("Created revision %s", sha1));
      } catch (RepoException | CannotResolveRevisionException e) {
        logger.warning(String.format("Failed setting summary: %s", e));
      }
    }