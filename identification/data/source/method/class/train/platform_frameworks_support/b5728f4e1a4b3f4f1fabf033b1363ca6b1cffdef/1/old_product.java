JobInfo convert(WorkSpec workSpec) {
        Constraints constraints = workSpec.getConstraints();
        int jobId = mIdGenerator.nextJobSchedulerId();
        // TODO(janclarin): Support newer required network types if unsupported by API version.
        int jobInfoNetworkType = convertNetworkType(constraints.getRequiredNetworkType());
        PersistableBundle extras = new PersistableBundle();
        extras.putString(EXTRA_WORK_SPEC_ID, workSpec.getId());
        extras.putBoolean(EXTRA_IS_PERIODIC, workSpec.isPeriodic());
        JobInfo.Builder builder = new JobInfo.Builder(jobId, mWorkServiceComponent)
                .setRequiredNetworkType(jobInfoNetworkType)
                .setRequiresCharging(constraints.requiresCharging())
                .setRequiresDeviceIdle(constraints.requiresDeviceIdle())
                .setExtras(extras);

        if (!constraints.requiresDeviceIdle()) {
            // Device Idle and Backoff Criteria cannot be set together
            int backoffPolicy = workSpec.getBackoffPolicy() == BackoffPolicy.LINEAR
                    ? JobInfo.BACKOFF_POLICY_LINEAR : JobInfo.BACKOFF_POLICY_EXPONENTIAL;
            builder.setBackoffCriteria(workSpec.getBackoffDelayDuration(), backoffPolicy);
        }

        if (workSpec.isPeriodic()) {
            if (Build.VERSION.SDK_INT >= 24) {
                builder.setPeriodic(workSpec.getIntervalDuration(), workSpec.getFlexDuration());
            } else {
                Logger.debug(TAG,
                        "Flex duration is currently not supported before API 24. Ignoring.");
                builder.setPeriodic(workSpec.getIntervalDuration());
            }
        } else {
            // Even if a Work has no constraints, setMinimumLatency(0) still needs to be called due
            // to an issue in JobInfo.Builder#build and JobInfo with no constraints. See b/67716867.
            builder.setMinimumLatency(workSpec.getInitialDelay());
        }

        if (Build.VERSION.SDK_INT >= 24 && constraints.hasContentUriTriggers()) {
            for (ContentUriTriggers.Trigger trigger : constraints.getContentUriTriggers()) {
                builder.addTriggerContentUri(convertContentUriTrigger(trigger));
            }
        } else {
            // Jobs with Content Uri Triggers cannot be persisted
            builder.setPersisted(true);
        }

        // TODO(janclarin): Support requires[Battery|Storage]NotLow for versions older than 26.
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setRequiresBatteryNotLow(constraints.requiresBatteryNotLow());
            builder.setRequiresStorageNotLow(constraints.requiresStorageNotLow());
        }
        return builder.build();
    }