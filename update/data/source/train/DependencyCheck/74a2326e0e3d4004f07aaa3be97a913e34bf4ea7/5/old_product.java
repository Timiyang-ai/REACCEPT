protected String getCurrentReleaseVersion() {
        HttpURLConnection conn = null;
        try {
            final String str = Settings.getString(Settings.KEYS.ENGINE_VERSION_CHECK_URL, "http://jeremylong.github.io/DependencyCheck/current.txt");
            final URL url = new URL(str);
            conn = URLConnectionFactory.createHttpURLConnection(url);
            conn.connect();
            if (conn.getResponseCode() != 200) {
                return null;
            }
            final String releaseVersion = IOUtils.toString(conn.getInputStream(), "UTF-8");
            if (releaseVersion != null) {
                return releaseVersion.trim();
            }
        } catch (MalformedURLException ex) {
            LOGGER.debug("Unable to retrieve current release version of dependency-check - malformed url?");
        } catch (URLConnectionFailureException ex) {
            LOGGER.debug("Unable to retrieve current release version of dependency-check - connection failed");
        } catch (IOException ex) {
            LOGGER.debug("Unable to retrieve current release version of dependency-check - i/o exception");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }