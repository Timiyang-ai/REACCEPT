public void updateIndexFromWeb() throws MalformedURLException, ParserConfigurationException, SAXException, IOException {
        long timeStamp = updateNeeded();
        if (timeStamp > 0) {
            URL url = new URL(Settings.getString(Settings.KEYS.CPE_URL));
            File outputPath = null;
            try {
                outputPath = File.createTempFile("cpe", ".xml");
                Downloader.fetchFile(url, outputPath, true);
                Importer.importXML(outputPath.toString());
                writeLastUpdatedPropertyFile(timeStamp);
            } catch (DownloadFailedException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (outputPath != null && outputPath.exists()) {
                        outputPath.delete();
                    }
                } finally {
                    if (outputPath != null && outputPath.exists()) {
                        outputPath.deleteOnExit();
                    }
                }
            }
        }
    }