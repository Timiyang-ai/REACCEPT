static String decodeURI( String input ) {
        if ( null == input ) {
            return null;
        }

        String output = input;
        try {
            output = URLDecoder.decode(input, "UTF-8");
        } catch ( UnsupportedEncodingException e ) {
            if ( logger.isLoggable( Level.WARNING ) ) {
                logger.log( Level.WARNING, "Unable to decode URI: {0}.", e.getMessage() );
            }
        }

        return output;
    }