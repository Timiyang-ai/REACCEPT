public List<File> findNRecentSnapshots(int n) throws IOException {
        List<File> files = Util.sortDataDir(snapDir.listFiles(), "snapshot", false);
        int i = 0;
        List<File> list = new ArrayList<File>();
        for (File f: files) {
            if (i==n)
                break;
            i++;
            list.add(f);
        }
        return list;
    }