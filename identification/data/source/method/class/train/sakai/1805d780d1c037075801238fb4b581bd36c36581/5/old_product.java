public static Properties signProperties(Properties postProp, String method, String url, 
        String call_back_url, String oauth_consumer_key, String oauth_consumer_secret)
    {
        postProp = BasicLTIUtil.cleanupProperties(postProp);
        postProp.setProperty("lti_version","basiclti-1.0");
        postProp.setProperty("basiclti_submit","Continue");

        OAuthMessage oam = new OAuthMessage(method, url,postProp.entrySet());
        OAuthConsumer cons = new OAuthConsumer(call_back_url, 
            oauth_consumer_key, oauth_consumer_secret, null);
        OAuthAccessor acc = new OAuthAccessor(cons);
        System.out.println("OAM="+oam+"\n");
        try {
            // System.out.println("BM="+OAuthSignatureMethod.getBaseString(oam)+"\n");
            oam.addRequiredParameters(acc);
            System.out.println("BM2="+OAuthSignatureMethod.getBaseString(oam)+"\n");
            // System.out.println("OAM="+oam+"\n");

            List<Map.Entry<String, String>> params = oam.getParameters();
    
            Properties nextProp = new Properties();
            // Convert to Properties
            for (Map.Entry<String,String> e : params) {
                // System.out.println("value= " + e);
                nextProp.setProperty(e.getKey(), e.getValue());
            }
	    return nextProp;
        } catch (Exception e) {
            System.out.println("Exception "+e.getMessage());
            return null;
        }
    
    }