  public ItemFacade createImportedItem(Document document)
  {
    log.debug(
        document==null?
        "DOCUMENT IS NULL IN createImportedItem(Document)":
        "createImportedItem(Document)");
    ItemFacade item = new ItemFacade();

    try
    {
      // create the item
      ExtractionHelper exHelper = new ExtractionHelper(QTIVersion.VERSION_1_2);
      exHelper.setOverridePath(xslPath);
      log.info("XSLT Path: " + exHelper.getTransformPath());
      Item itemXml = new Item(document, QTIVersion.VERSION_1_2);
      Map itemMap = exHelper.mapItem(itemXml);
//      log.debug("ITEM MAP=" + itemMap);
      exHelper.updateItem(item, itemXml, itemMap);
      //ItemService itemService = new ItemService();
      log.info("updating item");
//      itemService.saveItem(item);
    }
    catch(Exception e)
    {
      log.error(e.getMessage(), e);
    }

    return item;
  }