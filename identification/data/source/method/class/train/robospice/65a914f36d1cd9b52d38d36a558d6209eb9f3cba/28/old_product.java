@Override
    public final String loadDataFromNetwork() throws Exception {
        try {
            Ln.d( "Call web service " + url );
            return CharStreams.toString( new InputStreamReader( new URL( url ).openStream(), "UTF-8" ) );
        } catch ( MalformedURLException e ) {
            Ln.e( e, "Unable to create image URL" );
            return null;
        } catch ( IOException e ) {
            Ln.e( e, "Unable to download image" );
            return null;
        }
    }