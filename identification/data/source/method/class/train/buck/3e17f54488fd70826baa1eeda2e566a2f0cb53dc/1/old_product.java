public boolean startsWith(ForwardRelativePath path) {
    if (this.segments.length < path.segments.length) {
      return false;
    }

    for (int i = 0; i != this.segments.length; ++i) {
      if (!this.segments[i].equals(path.segments[i])) {
        return false;
      }
    }

    return true;
  }