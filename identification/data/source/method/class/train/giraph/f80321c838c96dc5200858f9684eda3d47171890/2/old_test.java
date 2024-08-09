@Test
  public void testInstantiateVertex()
      throws InstantiationException, IllegalAccessException,
      IOException, InterruptedException, IllegalArgumentException,
      InvocationTargetException, SecurityException, NoSuchMethodException {
    System.out.println("testInstantiateVertex: java.class.path=" +
        System.getProperty("java.class.path"));
    GiraphJob job = prepareJob(getCallingMethodName(),
        SimpleSuperstepVertex.class,
        SimpleSuperstepVertex.SimpleSuperstepVertexInputFormat.class);
    GraphState<LongWritable, IntWritable, FloatWritable, IntWritable> gs =
        new GraphState<LongWritable, IntWritable,
        FloatWritable, IntWritable>();
    BasicVertex<LongWritable, IntWritable, FloatWritable, IntWritable> vertex =
        BspUtils.createVertex(job.getConfiguration());
    vertex.initialize(null, null, null, null);
    System.out.println("testInstantiateVertex: Got vertex " + vertex +
        ", graphState" + gs);
    VertexInputFormat<LongWritable, IntWritable, FloatWritable, IntWritable>
    inputFormat = BspUtils.createVertexInputFormat(job.getConfiguration());
    /*if[HADOOP_NON_SASL_RPC]
      List<InputSplit> splitArray =
          inputFormat.getSplits(
              new JobContext(new Configuration(), new JobID()), 1);
    else[HADOOP_NON_SASL_RPC]*/
      List<InputSplit> splitArray =
          inputFormat.getSplits(
              new JobContextImpl(new Configuration(), new JobID()), 1);
      /*end[HADOOP_NON_SASL_RPC]*/
    ByteArrayOutputStream byteArrayOutputStream =
        new ByteArrayOutputStream();
    DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);
    ((Writable) splitArray.get(0)).write(outputStream);
    System.out.println("testInstantiateVertex: Example output split = " +
        byteArrayOutputStream.toString());
  }