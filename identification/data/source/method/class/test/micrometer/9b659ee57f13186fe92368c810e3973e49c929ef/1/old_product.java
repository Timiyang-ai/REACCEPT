void writeCounter(OutputStream os, Counter counter, long wallTime) throws IOException {
        os.write(INDEX_LINE);
        writeDocument(os, counter, wallTime, builder -> {
            builder.append(",\"count\":").append(counter.count());
        });
    }