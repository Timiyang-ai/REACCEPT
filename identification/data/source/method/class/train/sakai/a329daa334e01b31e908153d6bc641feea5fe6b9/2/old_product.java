public static String prepareForExport(String descriptor)
    {
        Map<String,Object> tm = null;
        try
        {
                tm = XMLMap.getFullMap(descriptor.trim());
        } 
        catch (Exception e) {
                M_log.warning("BasicLTIUtil exception parsing BasicLTI descriptor"+e.getMessage());
		e.printStackTrace();
		return null;
        }
        if ( tm == null ) {
            M_log.warning("Unable to parse XML in prepareForExport");
            return null;
        }
        XMLMap.removeSubMap(tm,"/basicltiresource/x-secure");
        String retval = XMLMap.getXML(tm, true);
        return retval;
    }