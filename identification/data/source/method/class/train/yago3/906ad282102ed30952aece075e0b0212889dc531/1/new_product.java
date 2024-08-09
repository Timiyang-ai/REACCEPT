public static String getOriginalImageUrl(String wikiUrl) throws NoSuchAlgorithmException {
	  MessageDigest md = MessageDigest.getInstance("MD5");
	  
	  String imageName = wikiUrl.substring(wikiUrl.indexOf("/wiki/Special:FilePath/") + "/wiki/Special:FilePath/".length());
	  
	  StringBuffer hashedName = new StringBuffer();
	  md.update(imageName.getBytes(StandardCharsets.UTF_8));
	  byte byteData[] = md.digest();
	  
	  for (int i = 0; i < byteData.length; i++) {
	    hashedName.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	  }
    return (IMAGE_ORIGINALURL_TEMPLATE + hashedName.charAt(0) + "/" + hashedName.charAt(0) + hashedName.charAt(1) + "/" + imageName);
	}