public ItemFacade createImportedItem(Document document)
  {
    log.debug(
      document == null ?
      "DOCUMENT IS NULL IN createImportedItem(Document)" :
      "createImportedItem(Document)");
    ItemFacade item = new ItemFacade();

    try
    {
      // create the item
      ExtractionHelper exHelper = new ExtractionHelper(this.qtiVersion);
      log.debug("XSLT Path: " + exHelper.getTransformPath());
      Item itemXml = new Item(document, QTIVersion.VERSION_1_2);
      Map itemMap = exHelper.mapItem(itemXml);
      log.debug("ITEM MAP=" + itemMap);
      log.debug("updating item");
      exHelper.updateItem(item, itemMap);
      ItemService itemService = new ItemService();
      log.debug("Saving item");
      itemService.saveItem(item);
    }
    catch (Exception e)
    {
      log.error(e.getMessage(), e);
    }

    return item;
  }