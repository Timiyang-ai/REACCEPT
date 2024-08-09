void writeLongTaskTimer(OutputStream os, LongTaskTimer timer, long wallTime) throws IOException {
        os.write(INDEX_LINE);
        writeDocument(os, timer, wallTime, builder -> {
            builder.append(",\"activeTasks\":").append(timer.activeTasks());
            builder.append(",\"duration\":").append(timer.duration(getBaseTimeUnit()));
        });
    }