public SexGenotypeDataCollection inferSexGenotypes() {
        final SexGenotypeDataCollection col = new SexGenotypeDataCollection();
        IntStream.range(0, processedReadCounts.columnNames().size())
                .forEach(si -> col.add(calculateSexGenotypeData(si)));
        return col;
    }