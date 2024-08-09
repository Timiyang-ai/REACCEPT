void writeTimer(OutputStream os, FunctionTimer timer, long wallTime) throws IOException {
        os.write(INDEX_LINE);
        writeDocument(os, timer, wallTime, builder -> {
            builder.append(",\"count\":").append(timer.count());
            builder.append(",\"sum\" :").append(timer.totalTime(getBaseTimeUnit()));
            builder.append(",\"mean\":").append(timer.mean(getBaseTimeUnit()));
        });
    }