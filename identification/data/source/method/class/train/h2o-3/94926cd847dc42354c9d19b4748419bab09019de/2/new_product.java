public Frame asFactor(Frame frame, String columnName) {
    Vec vec = frame.vec(columnName);
    frame.replace(frame.find(columnName), vec.toCategoricalVec());
    vec.remove();
    DKV.put(frame);
    return frame;
  }