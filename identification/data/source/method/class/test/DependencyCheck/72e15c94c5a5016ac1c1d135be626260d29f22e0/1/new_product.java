public static void fetchFile(URL url, File outputPath) throws DownloadFailedException {
        HttpURLConnection conn = null;
        try {
            conn = URLConnectionFactory.createHttpURLConnection(url);
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
        final String encoding = conn.getContentEncoding();

        BufferedOutputStream writer = null;
        InputStream reader = null;
        try {
            if (encoding != null && "gzip".equalsIgnoreCase(encoding)) {
                reader = new GZIPInputStream(conn.getInputStream());
            } else if (encoding != null && "deflate".equalsIgnoreCase(encoding)) {
                reader = new InflaterInputStream(conn.getInputStream());
            } else {
                reader = conn.getInputStream();
            }

            writer = new BufferedOutputStream(new FileOutputStream(outputPath));
            final byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) > 0) {
                writer.write(buffer, 0, bytesRead);
            }
        } catch (Exception ex) {
            throw new DownloadFailedException("Error saving downloaded file.", ex);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception ex) {
                    Logger.getLogger(Downloader.class.getName()).log(Level.FINEST,
                            "Error closing the writer in Downloader.", ex);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception ex) {
                    Logger.getLogger(Downloader.class.getName()).log(Level.FINEST,
                            "Error closing the reader in Downloader.", ex);
                }
            }
            try {
                conn.disconnect();
            } finally {
                conn = null;
            }
        }
    }