public static void fetchFile(URL url, File outputPath, boolean unzip) throws DownloadFailedException {
        HttpURLConnection conn = null;
        Proxy proxy = null;
        String proxyUrl = Settings.getString(Settings.KEYS.PROXY_URL);

        try {
            if (proxyUrl != null) {
                int proxyPort = Settings.getInt(Settings.KEYS.PROXY_PORT);
                SocketAddress addr = new InetSocketAddress(proxyUrl, proxyPort);
                proxy = new Proxy(Proxy.Type.HTTP, addr);
                conn = (HttpURLConnection) url.openConnection(proxy);
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }
            if (Settings.getString(Settings.KEYS.CONNECTION_TIMEOUT) != null) {
                int timeout = Settings.getInt(Settings.KEYS.CONNECTION_TIMEOUT);
                conn.setConnectTimeout(timeout);
            }
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.connect();
        } catch (IOException ex) {
            try {
                if (conn != null) {
                    conn.disconnect();
                }
            } finally {
                conn = null;
            }
            throw new DownloadFailedException("Error downloading file.", ex);
        }
        String encoding = conn.getContentEncoding();
        
        BufferedOutputStream writer = null;
        try {
            InputStream reader;
            if (unzip) {
                reader = new GZIPInputStream(conn.getInputStream());
            } else {
                reader = conn.getInputStream();
            }

            writer = new BufferedOutputStream(new FileOutputStream(outputPath));
            byte[] buffer = new byte[4096];
            int bytesRead = 0;
            while ((bytesRead = reader.read(buffer)) > 0) {
                writer.write(buffer, 0, bytesRead);
            }
        } catch (Exception ex) {
            throw new DownloadFailedException("Error saving downloaded file.", ex);
        } finally {
            try {
                writer.close();
                writer = null;
            } catch (Exception ex) {
                Logger.getLogger(Downloader.class.getName()).log(Level.FINEST,
                        "Error closing the writter in Downloader.", ex);
            }
            try {
                conn.disconnect();
            } finally {
                conn = null;
            }
        }
    }