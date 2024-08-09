public List<PointSegment> addCommand(String command, int line) throws GcodeParserException {
        //String stripped = GcodePreprocessorUtils.removeComment(command);
        List<String> commands = this.preprocessCommand(command);
        List<PointSegment> results = new ArrayList<>();
        for (String c: commands) {
            List<String> args = GcodePreprocessorUtils.splitCommand(c);
            results.addAll(this.addCommand(args, line));
        }
        return results;
    }