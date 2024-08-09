public static Properties signProperties(Properties postProp, String url, String method, 
        String oauth_consumer_key, String oauth_consumer_secret, 
	String org_id, String org_desc, String org_url)
    {
        postProp = BasicLTIUtil.cleanupProperties(postProp);
        postProp.setProperty("lti_version","LTI-1p0");
        postProp.setProperty("lti_message_type","basic-lti-launch-request");
	// Allow caller to internatonalize this for us...
        if ( postProp.getProperty(BASICLTI_SUBMIT) == null ) {
            postProp.setProperty(BASICLTI_SUBMIT, "Launch Endpoint with BasicLTI Data");
        }
        if ( org_id != null ) postProp.setProperty("tool_consumer_instance_guid", org_id);
        if ( org_desc != null ) postProp.setProperty("tool_consumer_instance_description", org_desc);
        if ( org_url != null ) postProp.setProperty("tool_consumer_instance_url", org_desc);

        if ( postProp.getProperty("oauth_callback") == null ) postProp.setProperty("oauth_callback","about:blank");

        if ( oauth_consumer_key == null || oauth_consumer_secret == null ) {
            dPrint("No signature generated in signProperties");
            return postProp;
        }

        OAuthMessage oam = new OAuthMessage(method, url,postProp.entrySet());
        OAuthConsumer cons = new OAuthConsumer("about:blank",
            oauth_consumer_key, oauth_consumer_secret, null);
        OAuthAccessor acc = new OAuthAccessor(cons);
        try {
            oam.addRequiredParameters(acc);
            // System.out.println("Base Message String\n"+OAuthSignatureMethod.getBaseString(oam)+"\n");

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