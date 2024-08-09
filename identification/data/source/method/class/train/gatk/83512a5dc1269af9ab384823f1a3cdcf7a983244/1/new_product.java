public SexGenotypeDataCollection inferSexGenotypes() {
        final SexGenotypeDataCollection col = new SexGenotypeDataCollection();
        IntStream.range(0, readCounts.columnNames().size())
                .forEach(si -> {
                    final SexGenotypeData sampleSexGenotype = calculateSexGenotypeData(si);
                    logger.info(sampleSexGenotype);
                    col.add(sampleSexGenotype);
                });
        return col;
    }