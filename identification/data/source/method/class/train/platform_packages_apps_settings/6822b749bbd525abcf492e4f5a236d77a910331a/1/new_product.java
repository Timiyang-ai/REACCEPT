public static String getWifiIpAddresses(Context context) {
        WifiManager wifiManager = context.getSystemService(WifiManager.class);
        Network currentNetwork = wifiManager.getCurrentNetwork();
        if (currentNetwork != null) {
            ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
            LinkProperties prop = cm.getLinkProperties(currentNetwork);
            return formatIpAddresses(prop);
        }
        return null;
    }