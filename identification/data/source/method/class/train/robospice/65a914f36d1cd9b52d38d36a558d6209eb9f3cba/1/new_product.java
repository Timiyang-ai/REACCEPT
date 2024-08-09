@Override
        public final String loadDataFromNetwork() throws Exception {
                try {
                        Ln.d("Call web service " + url);
                        return IOUtils.toString(new InputStreamReader(new URL(
                                        url).openStream(), CharEncoding.UTF_8));
                } catch (final MalformedURLException e) {
                        Ln.e(e, "Unable to create URL");
                        return null;
                } catch (final IOException e) {
                        Ln.e(e, "Unable to download content");
                        return null;
                }
        }