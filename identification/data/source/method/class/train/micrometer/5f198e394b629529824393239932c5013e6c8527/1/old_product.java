private Stream<String> writeMeter(Meter meter, long wallTime) {
        IndexBuilder index = index(meter, wallTime);
        for (Measurement measurement : meter.measure()) {
            index.field(measurement.getStatistic().getTagValueRepresentation(), measurement.getValue());
        }
        return Stream.of(index.build());
    }