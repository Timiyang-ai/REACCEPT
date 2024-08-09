    public void testgenerateHtmlFileURL () throws Exception {
        //Tests the JDK issue #6193279
        URL url = AppletSupport.generateHtmlFileURL(source,buildFolder,classesFolder,"tp1");
        String[] parts = url.toExternalForm().split("/");
        assertTrue (parts.length>4);
        assertEquals (parts[parts.length-1],"Applet.html");
        assertEquals (parts[parts.length-2],"build");
        assertEquals (parts[parts.length-3],"proj");
        assertEquals (parts[parts.length-4],"Folder%20With%20Spaces");
        url = AppletSupport.generateHtmlFileURL(source,buildFolder,classesFolder,"tp2");
        parts = url.toExternalForm().split("/");
        assertTrue (parts.length>4);
        assertEquals (parts[parts.length-1],"Applet.html");
        assertEquals (parts[parts.length-2],"build");
        assertEquals (parts[parts.length-3],"proj");
        assertEquals (parts[parts.length-4],"Folder With Spaces");
        url = AppletSupport.generateHtmlFileURL(source,buildFolder,classesFolder,null);
        parts = url.toExternalForm().split("/");
        assertTrue (parts.length>4);
        assertEquals (parts[parts.length-1],"Applet.html");
        assertEquals (parts[parts.length-2],"build");
        assertEquals (parts[parts.length-3],"proj");
        assertEquals (parts[parts.length-4],"Folder%20With%20Spaces");
        url = AppletSupport.generateHtmlFileURL(source,buildFolder,classesFolder,"tp3");
        parts = url.toExternalForm().split("/");
        assertTrue (parts.length>4);
        assertEquals (parts[parts.length-1],"Applet.html");
        assertEquals (parts[parts.length-2],"build");
        assertEquals (parts[parts.length-3],"proj");
        assertEquals (parts[parts.length-4],"Folder With Spaces");
    }