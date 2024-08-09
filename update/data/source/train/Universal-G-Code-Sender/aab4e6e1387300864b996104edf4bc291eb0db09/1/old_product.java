@Override
    public List<PointSegment> addCommand(String command, int line) throws GcodeParserException {
        List<PointSegment> results = new ArrayList<>();
        // Add command get meta doesn't update the state, so we need to do that
        // manually.
        List<String> processedCommands = this.preprocessCommand(command);
        for (String processedCommand : processedCommands) {
            Collection<GcodeMeta> metaObjects = addCommandGetMeta(processedCommand, line, state);
            if (metaObjects != null) {
                for (GcodeMeta c : metaObjects) {
                    if (c.point != null)
                        results.add(c.point);
                    if (c.points != null)
                        results.addAll(c.points);
                    if (c.state != null)
                        this.state = c.state;
                }
            }
        }

        for (PointSegment ps : results) {
            secondLatest = latest;
            latest = ps;
        }
        return results;
    }