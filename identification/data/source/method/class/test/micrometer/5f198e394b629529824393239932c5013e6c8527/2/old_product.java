Optional<String> writeMeter(Meter meter) {
        return Optional.of(writeDocument(meter, builder -> {
            for (Measurement measurement : meter.measure()) {
                builder.append(",\"").append(measurement.getStatistic().getTagValueRepresentation()).append("\":\"").append(measurement.getValue()).append("\"");
            }
        }));
    }