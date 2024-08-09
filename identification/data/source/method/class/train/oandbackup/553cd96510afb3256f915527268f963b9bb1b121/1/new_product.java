static CharSequence[] collectItems()
    {
        ArrayList<String> list = new ArrayList<String>();
        appInfoList.ifPresent(appInfos -> {
            for(AppInfo appInfo : appInfos) {
                list.add(appInfo.getPackageName());
            }
        });
        return list.toArray(new CharSequence[list.size()]);
    }