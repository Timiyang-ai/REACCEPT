@CliCommand(value = CliStrings.EXPORT_STACKTRACE, help = CliStrings.EXPORT_STACKTRACE__HELP)
  @CliMetaData(shellOnly = false, relatedTopic = {CliStrings.TOPIC_GEODE_DEBUG_UTIL})
  @ResourceOperation(resource = Resource.CLUSTER, operation = Operation.READ)
  public Result exportStackTrace(@CliOption(key = CliStrings.EXPORT_STACKTRACE__MEMBER,
      optionContext = ConverterHint.ALL_MEMBER_IDNAME,
      help = CliStrings.EXPORT_STACKTRACE__HELP) String memberNameOrId,

      @CliOption(key = CliStrings.EXPORT_STACKTRACE__GROUP,
          optionContext = ConverterHint.ALL_MEMBER_IDNAME,
          help = CliStrings.EXPORT_STACKTRACE__GROUP) String group,

      @CliOption(key = CliStrings.EXPORT_STACKTRACE__FILE,
          help = CliStrings.EXPORT_STACKTRACE__FILE__HELP) String fileName,

      @CliOption(key = CliStrings.EXPORT_STACKTRACE__FAIL__IF__FILE__PRESENT,
          unspecifiedDefaultValue = "false",
          help = CliStrings.EXPORT_STACKTRACE__FAIL__IF__FILE__PRESENT__HELP) boolean failIfFilePresent) {

    Result result = null;
    StringBuffer filePrefix = new StringBuffer("stacktrace");
    try {
      if (fileName == null) {
        fileName = filePrefix.append("_").append(System.currentTimeMillis()).toString();
      }
      final File outFile = new File(fileName);
      if (outFile.exists() && failIfFilePresent) {
        return ResultBuilder.createShellClientErrorResult(CliStrings.format(
            CliStrings.EXPORT_STACKTRACE__ERROR__FILE__PRESENT, outFile.getCanonicalPath()));
      }

      Cache cache = CacheFactory.getAnyInstance();
      GemFireCacheImpl gfeCacheImpl = (GemFireCacheImpl) cache;
      InternalDistributedSystem ads = gfeCacheImpl.getSystem();

      InfoResultData resultData = ResultBuilder.createInfoResultData();

      Map<String, byte[]> dumps = new HashMap<String, byte[]>();
      Set<DistributedMember> targetMembers = null;

      if ((group == null || group.isEmpty())
          && (memberNameOrId == null || memberNameOrId.isEmpty())) {
        targetMembers = CliUtil.getAllMembers(cache);
      } else {
        targetMembers = CliUtil.findAllMatchingMembers(group, memberNameOrId);
      }

      ResultCollector<?, ?> rc =
          CliUtil.executeFunction(getStackTracesFunction, null, targetMembers);
      ArrayList<Object> resultList = (ArrayList<Object>) rc.getResult();

      for (Object resultObj : resultList) {
        if (resultObj instanceof StackTracesPerMember) {
          StackTracesPerMember stackTracePerMember = (StackTracesPerMember) resultObj;
          dumps.put(stackTracePerMember.getMemberNameOrId(), stackTracePerMember.getStackTraces());
        }
      }

      String filePath = writeStacksToFile(dumps, fileName);
      resultData.addLine(CliStrings.format(CliStrings.EXPORT_STACKTRACE__SUCCESS, filePath));
      resultData.addLine(CliStrings.EXPORT_STACKTRACE__HOST + ads.getDistributedMember().getHost());

      result = ResultBuilder.buildResult(resultData);
    } catch (CommandResultException crex) {
      return crex.getResult();
    } catch (Exception ex) {
      result = ResultBuilder
          .createGemFireErrorResult(CliStrings.EXPORT_STACKTRACE__ERROR + ex.getMessage());
    }
    return result;
  }