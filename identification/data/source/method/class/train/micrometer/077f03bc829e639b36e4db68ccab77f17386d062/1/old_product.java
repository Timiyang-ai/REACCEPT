void writeGauge(OutputStream os, Gauge gauge, long wallTime) throws IOException {
        Double value = gauge.value();
        if (!value.isNaN()) {
            os.write(INDEX_LINE);
            writeDocument(os, gauge, wallTime, builder -> {
                builder.append(",\"value\":").append(value);
            });
        }
    }