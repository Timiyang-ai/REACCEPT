JobInfo convert(WorkSpec workSpec, int jobId) {
        Constraints constraints = workSpec.constraints;
        // TODO(janclarin): Support newer required network types if unsupported by API version.
        int jobInfoNetworkType = convertNetworkType(constraints.getRequiredNetworkType());
        PersistableBundle extras = new PersistableBundle();
        extras.putString(EXTRA_WORK_SPEC_ID, workSpec.id);
        extras.putBoolean(EXTRA_IS_PERIODIC, workSpec.isPeriodic());
        JobInfo.Builder builder = new JobInfo.Builder(jobId, mWorkServiceComponent)
                .setRequiredNetworkType(jobInfoNetworkType)
                .setRequiresCharging(constraints.requiresCharging())
                .setRequiresDeviceIdle(constraints.requiresDeviceIdle())
                .setExtras(extras);

        if (!constraints.requiresDeviceIdle()) {
            // Device Idle and Backoff Criteria cannot be set together
            int backoffPolicy = workSpec.backoffPolicy == BackoffPolicy.LINEAR
                    ? JobInfo.BACKOFF_POLICY_LINEAR : JobInfo.BACKOFF_POLICY_EXPONENTIAL;
            builder.setBackoffCriteria(workSpec.backoffDelayDuration, backoffPolicy);
        }

        if (workSpec.isPeriodic()) {
            if (Build.VERSION.SDK_INT >= 24) {
                builder.setPeriodic(workSpec.intervalDuration, workSpec.flexDuration);
            } else {
                Log.d(TAG,
                        "Flex duration is currently not supported before API 24. Ignoring.");
                builder.setPeriodic(workSpec.intervalDuration);
            }
        } else {
            // Even if a WorkRequest has no constraints, setMinimumLatency(0) still needs to be
            // called due to an issue in JobInfo.Builder#build and JobInfo with no constraints. See
            // b/67716867.
            builder.setMinimumLatency(workSpec.initialDelay);
        }

        if (Build.VERSION.SDK_INT >= 24 && constraints.hasContentUriTriggers()) {
            for (ContentUriTriggers.Trigger trigger : constraints.getContentUriTriggers()) {
                builder.addTriggerContentUri(convertContentUriTrigger(trigger));
            }
        }

        // We don't want to persist these jobs because we reschedule these jobs on BOOT_COMPLETED.
        // That way ForceStopRunnable correctly reschedules Jobs when necessary.
        builder.setPersisted(false);
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setRequiresBatteryNotLow(constraints.requiresBatteryNotLow());
            builder.setRequiresStorageNotLow(constraints.requiresStorageNotLow());
        }
        return builder.build();
    }