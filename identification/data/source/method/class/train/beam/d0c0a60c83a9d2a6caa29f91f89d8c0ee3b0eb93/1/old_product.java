@Override
    public XmlWriter<T> createWriter(PipelineOptions options) throws Exception {
      JAXBContext context;
      Marshaller marshaller;
      context = JAXBContext.newInstance(getSink().classToBind);
      marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
      marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
      return new XmlWriter<>(this, marshaller);
    }