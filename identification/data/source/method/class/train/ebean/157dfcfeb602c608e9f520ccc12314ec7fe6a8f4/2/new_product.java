public static Autotune read(InputStream is) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(Autotune.class);
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      return (Autotune) unmarshaller.unmarshal(is);
    } catch (JAXBException e) {
      throw new IllegalStateException(e);
    }
  }