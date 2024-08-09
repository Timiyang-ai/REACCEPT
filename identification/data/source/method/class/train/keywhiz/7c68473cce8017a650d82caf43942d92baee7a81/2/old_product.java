public ImmutableList<SecretSeriesAndContent> getSecrets() {
    return dslContext.transactionResult(configuration -> {
      ImmutableList.Builder<SecretSeriesAndContent> secretsBuilder = ImmutableList.builder();

      secretSeriesJooqDao.getSecretSeries()
          .forEach((series) -> secretContentJooqDao.getSecretContentsBySecretId(series.getId())
              .forEach((content) -> secretsBuilder.add(SecretSeriesAndContent.of(series, content))));

      return secretsBuilder.build();
    });
  }