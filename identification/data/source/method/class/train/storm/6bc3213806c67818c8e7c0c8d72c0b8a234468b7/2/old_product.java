public LoadCompConf scaleThroughput(double v) {
        if (streams != null) {
            List<OutputStream> newStreams = streams.stream().map((s) -> s.scaleThroughput(v)).collect(Collectors.toList());
            return new LoadCompConf(id, parallelism, newStreams, stats);
        } else {
            return this;
        }
    }