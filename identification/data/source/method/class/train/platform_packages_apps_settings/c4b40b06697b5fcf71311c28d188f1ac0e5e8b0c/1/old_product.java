public static boolean hasEthernet(Context context) {
        if (DataUsageUtils.TEST_RADIOS) {
            return SystemProperties.get(DataUsageUtils.TEST_RADIOS_PROP).contains(ETHERNET);
        }

        final ConnectivityManager conn = ConnectivityManager.from(context);
        if (!conn.isNetworkSupported(ConnectivityManager.TYPE_ETHERNET)) {
            return false;
        }

        if (FeatureFlagUtils.isEnabled(context, FeatureFlags.DATA_USAGE_V2)) {
            final TelephonyManager telephonyManager = TelephonyManager.from(context);;
            final NetworkStatsManager networkStatsManager =
                context.getSystemService(NetworkStatsManager.class);
            boolean hasEthernetUsage = false;
            try {
                final Bucket bucket = networkStatsManager.querySummaryForUser(
                    ConnectivityManager.TYPE_ETHERNET, telephonyManager.getSubscriberId(),
                    0L /* startTime */, System.currentTimeMillis() /* endTime */);
                if (bucket != null) {
                    hasEthernetUsage = bucket.getRxBytes() > 0 || bucket.getTxBytes() > 0;
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Exception querying network detail.", e);
            }
            return hasEthernetUsage;
        } else {
            final long ethernetBytes;
            try {
                INetworkStatsService statsService = INetworkStatsService.Stub.asInterface(
                    ServiceManager.getService(Context.NETWORK_STATS_SERVICE));

                INetworkStatsSession statsSession = statsService.openSession();
                if (statsSession != null) {
                    ethernetBytes = statsSession.getSummaryForNetwork(
                        NetworkTemplate.buildTemplateEthernet(), Long.MIN_VALUE, Long.MAX_VALUE)
                        .getTotalBytes();
                    TrafficStats.closeQuietly(statsSession);
                } else {
                    ethernetBytes = 0;
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

            // only show ethernet when both hardware present and traffic has occurred
            return ethernetBytes > 0;
        }
    }