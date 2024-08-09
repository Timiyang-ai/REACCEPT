@Override
    public Serializable getValueFromText(final String xmlMessage) {
      Serializable readObject = null;
      try {
          XStream xstream = new XStream();
          JMeterUtils.setupXStreamSecurityPolicy(xstream);
          readObject = (Serializable) xstream.fromXML(xmlMessage, readObject);
      } catch (Exception e) {
          throw new IllegalStateException("Unable to load object instance from text", e);
      }
      return readObject;
    }