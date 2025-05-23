public String downloadFileFromServer(String url, String fileName, String targetFileDir) throws Exception {

        // 下载的路径
        String localDir = getLocalDownloadDirPath();

        // 设置远程地址
        RemoteUrl remoteUrl = new RemoteUrl(url, hostList);

        // 下载
        return restfulMgr
                .downloadFromServer(remoteUrl, fileName, localDir, targetFileDir,
                        enableLocalDownloadDirInClassPath,
                        retryTime,
                        retrySleepSeconds);

    }