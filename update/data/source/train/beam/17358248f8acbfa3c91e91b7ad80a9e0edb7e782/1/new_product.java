@Override
    public XmlWriter<T> createWriter() throws Exception {
      JAXBContext context;
      Marshaller marshaller;
      context = JAXBContext.newInstance(getSink().spec.getRecordClass());
      marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
      marshaller.setProperty(Marshaller.JAXB_ENCODING, getSink().spec.getCharset());
      return new XmlWriter<>(this, marshaller);
    }