public static Properties signProperties(Properties postProp, String url, String method, 
        String key, String secret, String org_secret, String org_id, String org_desc)
    {
        postProp = BasicLTIUtil.cleanupProperties(postProp);
        postProp.setProperty("lti_version","basiclti-1.0");
        postProp.setProperty("basiclti_submit", "Launch Endpoint with BasicLTI Data");
        if ( org_id != null ) postProp.setProperty("tool_consumer_instance_guid", org_id);
        if ( org_desc != null ) postProp.setProperty("tool_consumer_instance_description", org_desc);

        String oauth_consumer_key = key;
        String oauth_consumer_secret = secret;
        if ( org_secret != null ) {
            oauth_consumer_secret = org_secret;
            oauth_consumer_key = "basiclti-lms:"+org_id;
        }

        if ( postProp.getProperty("oauth_callback") == null ) postProp.setProperty("oauth_callback","about:blank");

        OAuthMessage oam = new OAuthMessage(method, url,postProp.entrySet());
        OAuthConsumer cons = new OAuthConsumer("about:blank",
            oauth_consumer_key, oauth_consumer_secret, null);
        OAuthAccessor acc = new OAuthAccessor(cons);
        // System.out.println("OAM="+oam+"\n");
        try {
            // System.out.println("BM="+OAuthSignatureMethod.getBaseString(oam)+"\n");
            oam.addRequiredParameters(acc);
            System.out.println("Base Message String\n"+OAuthSignatureMethod.getBaseString(oam)+"\n");

            List<Map.Entry<String, String>> params = oam.getParameters();
    
            Properties nextProp = new Properties();
            // Convert to Properties
            for (Map.Entry<String,String> e : params) {
                nextProp.setProperty(e.getKey(), e.getValue());
            }
	    return nextProp;
        } catch (net.oauth.OAuthException e) {
            System.out.println("BasicLTIUtil.signProperties OAuth Exception "+e.getMessage());
            return null;
        } catch (java.io.IOException e) {
            System.out.println("BasicLTIUtil.signProperties IO Exception "+e.getMessage());
            return null;
        } catch (java.net.URISyntaxException e) {
            System.out.println("BasicLTIUtil.signProperties URI Syntax Exception "+e.getMessage());
            return null;
        }
    
    }