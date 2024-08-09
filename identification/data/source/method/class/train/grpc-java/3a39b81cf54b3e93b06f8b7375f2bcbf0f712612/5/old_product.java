@ExperimentalApi("https://github.com/grpc/grpc-java/issues/2861")
  public CallOptions withStreamTracerFactory(ClientStreamTracer.Factory factory) {
    CallOptions newOptions = new CallOptions(this);
    ArrayList<ClientStreamTracer.Factory> newList =
        new ArrayList<ClientStreamTracer.Factory>(streamTracerFactories.size() + 1);
    newList.addAll(streamTracerFactories);
    newList.add(factory);
    newOptions.streamTracerFactories = Collections.unmodifiableList(newList);
    return newOptions;
  }