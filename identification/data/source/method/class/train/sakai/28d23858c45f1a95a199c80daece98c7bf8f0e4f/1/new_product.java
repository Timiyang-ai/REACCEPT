public static boolean parseDescriptor(Properties launch_info,
      Properties postProp, String descriptor) {
    // this is an ugly copy/paste of the non-@deprecated method
    // could not convert data types as they variables get mutated (ugh)
    Map<String, Object> tm = null;
    try {
      tm = XMLMap.getFullMap(descriptor.trim());
    } catch (Exception e) {
      M_log.warning("BasicLTIUtil exception parsing BasicLTI descriptor: "
          + e.getMessage());
      return false;
    }
    if (tm == null) {
      M_log.warning("Unable to parse XML in parseDescriptor");
      return false;
    }

    String launch_url = toNull(XMLMap.getString(tm,
        "/basic_lti_link/launch_url"));
    String secure_launch_url = toNull(XMLMap.getString(tm,
        "/basic_lti_link/secure_launch_url"));
    if (launch_url == null && secure_launch_url == null)
      return false;

    setProperty(launch_info, "launch_url", launch_url);
    setProperty(launch_info, "secure_launch_url", secure_launch_url);

    // Extensions for hand-authored placements - The export process should scrub
    // these
    setProperty(launch_info, "key", toNull(XMLMap.getString(tm,
        "/basic_lti_link/x-secure/launch_key")));
    setProperty(launch_info, "secret", toNull(XMLMap.getString(tm,
        "/basic_lti_link/x-secure/launch_secret")));

    List<Map<String, Object>> theList = XMLMap.getList(tm,
        "/basic_lti_link/custom/parameter");
    for (Map<String, Object> setting : theList) {
      dPrint("Setting=" + setting);
      String key = XMLMap.getString(setting, "/!key"); // Get the key attribute
      String value = XMLMap.getString(setting, "/"); // Get the value
      if (key == null || value == null)
        continue;
      key = "custom_" + mapKeyName(key);
      dPrint("key=" + key + " val=" + value);
      postProp.setProperty(key, value);
    }
    return true;
  }