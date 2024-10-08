public static Properties signProperties(Properties postProp, String url, String method, 
        String key, String secret, String org_secret, String org_id, String org_desc)
    {
        postProp = BasicLTIUtil.cleanupProperties(postProp);
        postProp.setProperty("lti_version","basiclti-1.0");
	// Allow caller to internatonalize this for us...
        if ( postProp.getProperty(BASICLTI_SUBMIT) == null ) {
            postProp.setProperty(BASICLTI_SUBMIT, "Launch Endpoint with BasicLTI Data");
        }
        if ( org_id != null ) postProp.setProperty("tool_consumer_instance_guid", org_id);
        if ( org_desc != null ) postProp.setProperty("tool_consumer_instance_description", org_desc);

        String oauth_consumer_key = key;
        String oauth_consumer_secret = secret;
        if ( org_secret != null ) {
            oauth_consumer_secret = org_secret;
            oauth_consumer_key = "basiclti-lms:"+org_id;
        }

        if ( postProp.getProperty("oauth_callback") == null ) postProp.setProperty("oauth_callback","about:blank");

        if ( oauth_consumer_key == null || oauth_consumer_secret == null ) {
            dPrint("Error in signProperties - key and secret must be specified");
            return null;
        }

        OAuthMessage oam = new OAuthMessage(method, url,postProp.entrySet());
        OAuthConsumer cons = new OAuthConsumer("about:blank",
            oauth_consumer_key, oauth_consumer_secret, null);
        OAuthAccessor acc = new OAuthAccessor(cons);
        try {
            oam.addRequiredParameters(acc);
            dPrint("Base Message String\n"+OAuthSignatureMethod.getBaseString(oam)+"\n");

            List<Map.Entry<String, String>> params = oam.getParameters();
    
            Properties nextProp = new Properties();
            // Convert to Properties
            for (Map.Entry<String,String> e : params) {
                nextProp.setProperty(e.getKey(), e.getValue());
            }
	    return nextProp;
        } catch (net.oauth.OAuthException e) {
            M_log.warning("BasicLTIUtil.signProperties OAuth Exception "+e.getMessage());
            return null;
        } catch (java.io.IOException e) {
            M_log.warning("BasicLTIUtil.signProperties IO Exception "+e.getMessage());
            return null;
        } catch (java.net.URISyntaxException e) {
            M_log.warning("BasicLTIUtil.signProperties URI Syntax Exception "+e.getMessage());
            return null;
        }
    
    }