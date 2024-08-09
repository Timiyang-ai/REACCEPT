public Node node(ProgramPoint programPoint, @Nullable ProgramState programState) {
    Node result = new Node(programPoint, programState, this);
    Node cached = nodes.get(result);
    if (cached != null) {
      cached.isNew = false;
      return cached;
    }
    result.isNew = true;
    nodes.put(result, result);
    nodesByProgramPoint.put(programPoint, result);
    return result;
  }