@Override
    public List<GcodeMeta> addCommand(String command) throws GcodeParserException {
        return addCommand(command, ++this.state.commandNumber);
    }