@Override
    public List<PointSegment> addCommand(String command) throws GcodeParserException {
        return addCommand(command, ++this.state.commandNumber);
    }