  private StarlarkFile parseFile(String... lines) {
    ParserInput input = ParserInput.fromLines(lines);
    StarlarkFile file = StarlarkFile.parse(input);
    Event.replayEventsOn(events.reporter(), file.errors());
    return file;
  }