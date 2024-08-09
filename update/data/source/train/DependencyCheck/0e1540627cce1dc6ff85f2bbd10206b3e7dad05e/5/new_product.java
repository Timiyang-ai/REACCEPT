public void fetchFile(URL url, File outputPath, boolean useProxy) throws DownloadFailedException {
        try (HttpResourceConnection conn = new HttpResourceConnection(settings, useProxy);
                OutputStream out = new FileOutputStream(outputPath)) {
            final InputStream in = conn.fetch(url);
            IOUtils.copy(in, out);
        } catch (IOException ex) {
            final String msg = format("Download failed, unable to copy '%s' to '%s'", url.toString(), outputPath.getAbsolutePath());
            throw new DownloadFailedException(msg, ex);
        }
    }