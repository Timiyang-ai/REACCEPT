static String decodeURI( String input ) {
        if ( null == input ) {
            return null;
        }

        String output = input;
        try {
            output = URLDecoder.decode(input, "UTF-8");
        } catch ( UnsupportedEncodingException e ) {
            if ( logger.isLoggable( Level.WARNING ) ) {
                logger.log( Level.WARNING, URI_DECODING_ERROR, e.getLocalizedMessage() );
            }
        }

        return output;
    }