@Override
    protected final String encode(String unencoded) {
        try {
            return StringUtils.isBlank(unencoded) ? "" : URLEncoder.encode(unencoded, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            java.util.logging.Logger.getLogger(PostRedirectGetWithCookiesFormHelperImpl.class.getName()).log(Level.SEVERE, null, ex);
            return unencoded;
        }
    }