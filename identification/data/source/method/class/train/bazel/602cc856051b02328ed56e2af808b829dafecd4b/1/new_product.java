public boolean put(ActionInput input, FileArtifactValue metadata) {
    Preconditions.checkNotNull(input);
    if (size >= keys.length) {
      resize();
    }
    String path = input.getExecPathString();
    int hashCode = path.hashCode();
    int index = hashCode & (table.length - 1);
    int nextIndex = table[index];
    if (nextIndex == -1) {
      table[index] = size;
    } else {
      do {
        index = nextIndex;
        if (hashCode == paths[index].hashCode() && Objects.equal(path, paths[index])) {
          return false;
        }
        nextIndex = next[index];
      } while (nextIndex != -1);
      next[index] = size;
    }
    next[size] = -1;
    keys[size] = input;
    paths[size] = input.getExecPathString();
    values[size] = metadata;
    size++;
    return true;
  }