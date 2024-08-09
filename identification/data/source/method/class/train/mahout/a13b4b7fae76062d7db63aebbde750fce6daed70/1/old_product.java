public static void main(String[] args) throws Exception {
    DefaultOptionBuilder obuilder = new DefaultOptionBuilder();
    ArgumentBuilder abuilder = new ArgumentBuilder();
    GroupBuilder gbuilder = new GroupBuilder();
    
    Option inputDirOpt = obuilder.withLongName("input").withRequired(true).withArgument(
      abuilder.withName("input").withMinimum(1).withMaximum(1).create()).withDescription(
      "The Directory on HDFS containing the transaction files").withShortName("i").create();
    
    Option outputOpt = DefaultOptionCreator.outputOption().create();
    
    Option helpOpt = DefaultOptionCreator.helpOption();
    
    // minSupport(3), maxHeapSize(50), numGroups(1000)
    Option minSupportOpt = obuilder.withLongName("minSupport").withArgument(
      abuilder.withName("minSupport").withMinimum(1).withMaximum(1).create()).withDescription(
      "(Optional) Minimum Support. Default Value: 3").withShortName("s").create();
    
    Option maxHeapSizeOpt = obuilder.withLongName("maxHeapSize").withArgument(
      abuilder.withName("maxHeapSize").withMinimum(1).withMaximum(1).create()).withDescription(
      "(Optional) Maximum Heap Size k, to denote the requirement to mine top K items. Default value: 50")
        .withShortName("k").create();
    
    Option numGroupsOpt = obuilder.withLongName("numGroups").withArgument(
      abuilder.withName("numGroups").withMinimum(1).withMaximum(1).create()).withDescription(
      "(Optional) Number of groups the features should be divided in the map-reduce version."
          + " Doesn't work in sequential version Default Value:1000").withShortName("g").create();
    
    Option recordSplitterOpt = obuilder.withLongName("splitterPattern").withArgument(
      abuilder.withName("splitterPattern").withMinimum(1).withMaximum(1).create()).withDescription(
      "Regular Expression pattern used to split given string transaction into itemsets."
          + " Default value splits comma separated itemsets.  Default Value:"
          + " \"[ ,\\t]*[,|\\t][ ,\\t]*\" ").withShortName("regex").create();
    
    Option treeCacheOpt = obuilder.withLongName("numTreeCacheEntries").withArgument(
      abuilder.withName("numTreeCacheEntries").withMinimum(1).withMaximum(1).create()).withDescription(
      "(Optional) Number of entries in the tree cache to prevent duplicate tree building. "
          + "(Warning) a first level conditional FP-Tree might consume a lot of memory, "
          + "so keep this value small, but big enough to prevent duplicate tree building. "
          + "Default Value:5 Recommended Values: [5-10]").withShortName("tc").create();
    
    Option methodOpt = obuilder.withLongName("method").withRequired(true).withArgument(
      abuilder.withName("method").withMinimum(1).withMaximum(1).create()).withDescription(
      "Method of processing: sequential|mapreduce").withShortName("method").create();
    Option encodingOpt = obuilder.withLongName("encoding").withArgument(
      abuilder.withName("encoding").withMinimum(1).withMaximum(1).create()).withDescription(
      "(Optional) The file encoding.  Default value: UTF-8").withShortName("e").create();
    
    Group group = gbuilder.withName("Options").withOption(minSupportOpt).withOption(inputDirOpt).withOption(
      outputOpt).withOption(maxHeapSizeOpt).withOption(numGroupsOpt).withOption(methodOpt).withOption(
      encodingOpt).withOption(helpOpt).withOption(treeCacheOpt).withOption(recordSplitterOpt).create();
    try {
      Parser parser = new Parser();
      parser.setGroup(group);
      CommandLine cmdLine = parser.parse(args);
      
      if (cmdLine.hasOption(helpOpt)) {
        CommandLineUtil.printHelp(group);
        return;
      }
      
      Parameters params = new Parameters();
      
      if (cmdLine.hasOption(minSupportOpt)) {
        String minSupportString = (String) cmdLine.getValue(minSupportOpt);
        params.set("minSupport", minSupportString);
      }
      if (cmdLine.hasOption(maxHeapSizeOpt)) {
        String maxHeapSizeString = (String) cmdLine.getValue(maxHeapSizeOpt);
        params.set("maxHeapSize", maxHeapSizeString);
      }
      if (cmdLine.hasOption(numGroupsOpt)) {
        String numGroupsString = (String) cmdLine.getValue(numGroupsOpt);
        params.set("numGroups", numGroupsString);
      }
      
      if (cmdLine.hasOption(treeCacheOpt)) {
        String numTreeCacheString = (String) cmdLine.getValue(treeCacheOpt);
        params.set("treeCacheSize", numTreeCacheString);
      }
      
      if (cmdLine.hasOption(recordSplitterOpt)) {
        String patternString = (String) cmdLine.getValue(recordSplitterOpt);
        params.set("splitPattern", patternString);
      }
      
      String encoding = "UTF-8";
      if (cmdLine.hasOption(encodingOpt)) {
        encoding = (String) cmdLine.getValue(encodingOpt);
      }
      params.set("encoding", encoding);
      Path inputDir = new Path(cmdLine.getValue(inputDirOpt).toString());
      Path outputDir = new Path(cmdLine.getValue(outputOpt).toString());
      
      params.set("input", inputDir.toString());
      params.set("output", outputDir.toString());
      
      String classificationMethod = (String) cmdLine.getValue(methodOpt);
      if (classificationMethod.equalsIgnoreCase("sequential")) {
        runFPGrowth(params);
      } else if (classificationMethod.equalsIgnoreCase("mapreduce")) {
        HadoopUtil.overwriteOutput(outputDir);
        PFPGrowth.runPFPGrowth(params);
      }
    } catch (OptionException e) {
      CommandLineUtil.printHelp(group);
    }
  }