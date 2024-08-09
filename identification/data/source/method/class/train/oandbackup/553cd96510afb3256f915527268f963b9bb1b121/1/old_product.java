private static CharSequence[] collectItems()
    {
        ArrayList<String> list = new ArrayList<String>();
        for(AppInfo appInfo : appInfoList)
        {
            list.add(appInfo.getPackageName());
        }
        return list.toArray(new CharSequence[list.size()]);
    }