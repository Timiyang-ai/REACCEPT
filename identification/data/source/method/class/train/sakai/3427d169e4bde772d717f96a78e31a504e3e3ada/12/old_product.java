public ItemFacade createImportedItem(Document document)
  {
    ItemFacade item = new ItemFacade();

    try
    {
      // create the item
      ExtractionHelper exHelper = new ExtractionHelper(this.qtiVersion);
      Item itemXml = new Item(document, QTIVersion.VERSION_1_2);
      Map itemMap = exHelper.mapItem(itemXml);
      exHelper.updateItem(item, itemMap);
      ItemService itemService = new ItemService();
      itemService.saveItem(item);
    }
    catch (Exception e)
    {
      log.error(e.getMessage(), e);
    }

    return item;
  }