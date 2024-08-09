private JobDiffInfo diff(CurrentJobConfig dbJobConfig, JobSettings zkJobConfig, boolean needDetail) {
        String jobName = dbJobConfig.getJobName();

        List<JobDiffInfo.ConfigDiffInfo> configDiffInfos = Lists.newArrayList();

        String jobTypeInDB = dbJobConfig.getJobType();
        // jobType
        diff("jobType", jobTypeInDB, zkJobConfig.getJobType(), configDiffInfos);
        // jobClass
        diff("jobClass", dbJobConfig.getJobClass(), zkJobConfig.getJobClass(), configDiffInfos);
        // shardingTotalCount
        diff("shardingTotalCount", dbJobConfig.getShardingTotalCount(), zkJobConfig.getShardingTotalCount(), configDiffInfos);
        // timeZone
        diff("timeZone", dbJobConfig.getTimeZone(), zkJobConfig.getTimeZone(), configDiffInfos);
        // cron
        diff("cron", dbJobConfig.getCron(), zkJobConfig.getCron(), configDiffInfos);
        // pausePeriodDate
        diff("pausePeriodDate", dbJobConfig.getPausePeriodDate(), zkJobConfig.getPausePeriodDate(), configDiffInfos);
        // pausePeriodTime
        diff("pausePeriodTime", dbJobConfig.getPausePeriodTime(), zkJobConfig.getPausePeriodTime(), configDiffInfos);
        // shardingItemParameters
        diff("shardingItemParameters", dbJobConfig.getShardingItemParameters(), zkJobConfig.getShardingItemParameters(), configDiffInfos);
        // jobParameter
        diff("jobParameter", dbJobConfig.getJobParameter(), zkJobConfig.getJobParameter(), configDiffInfos);
        // processCountIntervalSeconds
        diff("processCountIntervalSeconds", dbJobConfig.getProcessCountIntervalSeconds(), zkJobConfig.getProcessCountIntervalSeconds(), configDiffInfos);
        // timeout4AlarmSeconds
        diff("timeout4AlarmSeconds", dbJobConfig.getTimeout4AlarmSeconds(), zkJobConfig.getTimeout4AlarmSeconds(), configDiffInfos);
        // timeoutSeconds
        diff("timeoutSeconds", dbJobConfig.getTimeoutSeconds(), zkJobConfig.getTimeoutSeconds(), configDiffInfos);
        // loadLevel
        diff("loadLevel", dbJobConfig.getLoadLevel(), zkJobConfig.getLoadLevel(), configDiffInfos);
        // jobDegree
        diff("jobDegree", dbJobConfig.getJobDegree(), zkJobConfig.getJobDegree(), configDiffInfos);
        // enabled
        diff("enabled", dbJobConfig.getEnabled(), zkJobConfig.getEnabled(), configDiffInfos);
        // preferList
        diff("preferList", dbJobConfig.getPreferList(), zkJobConfig.getPreferList(), configDiffInfos);
        // useDispreferList
        diff("useDispreferList", dbJobConfig.getUseDispreferList(), zkJobConfig.getUseDispreferList(), configDiffInfos);
        // useSerial
        diff("useSerial", dbJobConfig.getUseSerial(), zkJobConfig.getUseSerial(), configDiffInfos);
        // queueName
        diff("queueName", dbJobConfig.getQueueName(), zkJobConfig.getQueueName(), configDiffInfos);
        // localMode
        diff("localMode", dbJobConfig.getLocalMode(), zkJobConfig.getLocalMode(), configDiffInfos);
        // dependencies
        diff("dependencies", dbJobConfig.getDependencies(), zkJobConfig.getDependencies(), configDiffInfos);
        // groups
        diff("groups", dbJobConfig.getGroups(), zkJobConfig.getGroups(), configDiffInfos);
        // description
        diff("description", dbJobConfig.getDescription(), zkJobConfig.getDescription(), configDiffInfos);
        // jobMode
        diff("jobMode", dbJobConfig.getJobMode(), zkJobConfig.getJobMode(), configDiffInfos);
        // channelName
        diff("channelName", dbJobConfig.getChannelName(), zkJobConfig.getChannelName(), configDiffInfos);
        // showNormalLog
        diff("showNormalLog", dbJobConfig.getShowNormalLog(), zkJobConfig.getShowNormalLog(), configDiffInfos);
        // enabledReport
        diff("enabledReport", dbJobConfig.getEnabledReport(), zkJobConfig.getEnabledReport(), configDiffInfos);
        // showNormalLog
        diff("showNormalLog", dbJobConfig.getShowNormalLog(), zkJobConfig.getShowNormalLog(), configDiffInfos);

        if (!configDiffInfos.isEmpty()) {
            Set<String> diffProperties = getDifferentProperties(configDiffInfos);
            log.info("find different properties of job [{}], {}", jobName, diffProperties);

            if (needDetail) {
                return new JobDiffInfo(dbJobConfig.getNamespace(), jobName, JobDiffInfo.DiffType.HAS_DIFFERENCE, configDiffInfos);
            }

            return new JobDiffInfo(dbJobConfig.getNamespace(), jobName, JobDiffInfo.DiffType.HAS_DIFFERENCE, Lists.<JobDiffInfo.ConfigDiffInfo>newArrayList());
        }

        return null;
    }