void writeSummary(OutputStream os, DistributionSummary summary, long wallTime) throws IOException {
        summary.takeSnapshot();
        os.write(INDEX_LINE);
        writeDocument(os, summary, wallTime, builder -> {
            builder.append(",\"count\":").append(summary.count());
            builder.append(",\"sum\":").append(summary.totalAmount());
            builder.append(",\"mean\":").append(summary.mean());
            builder.append(",\"max\":").append(summary.max());
        });
    }