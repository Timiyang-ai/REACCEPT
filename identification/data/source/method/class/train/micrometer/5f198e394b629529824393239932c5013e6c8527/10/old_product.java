void writeMeter(OutputStream os, Meter meter, long wallTime) throws IOException {
        os.write(INDEX_LINE);
        writeDocument(os, meter, wallTime, builder -> {
            for (Measurement measurement : meter.measure()) {
                builder.append(",\"").append(measurement.getStatistic().getTagValueRepresentation()).append("\":\"").append(measurement.getValue()).append("\"");
            }
        });
    }