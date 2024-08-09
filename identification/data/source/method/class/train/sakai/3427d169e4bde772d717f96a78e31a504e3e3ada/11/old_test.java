  public AssessmentFacade createImportedAssessment(Document document) {
//    if(log.isDebugEnabled())
//    {
    log.info(
//      log.debug(
        document==null?
        "DOCUMENT IS NULL IN createPublishedAssessment(  Document)":
        "createPublishedAssessment(Document)");
//    }
//    AssessmentFacade assessment = null;
    AssessmentFacade assessment = new AssessmentFacade();

    try {
      // create the assessment, later we'll add tests of 2.0
      ExtractionHelper exHelper = new ExtractionHelper(QTIVersion.VERSION_1_2);
      exHelper.setOverridePath(xslPath);
      // we need to know who we are
      String me = "admin";//AgentFacade.getAgentString();
//      AssessmentService assessmentService = new AssessmentService();
//      ItemService itemService = new ItemService();
      Assessment assessmentXml = new Assessment(document);
      Map assessmentMap = exHelper.mapAssessment(assessmentXml);
      assessment = new AssessmentFacade();//exHelper.createAssessment(assessmentMap);

      // update the remaining assessment properties
      exHelper.updateAssessment(assessment, assessmentMap);

      // make sure required fields are set
      assessment.setCreatedBy(me);
      assessment.setCreatedDate(assessment.getCreatedDate());
      assessment.setLastModifiedBy(me);
      assessment.setLastModifiedDate(assessment.getCreatedDate());
      assessment.setTypeId(TypeIfc.QUIZ);
      assessment.setStatus(Integer.valueOf(1));

      // process each section and each item within each section
      List sectionList = exHelper.getSectionXmlList(assessmentXml);
//      log.debug("found: " + sectionList.size() + "sections");
      log.debug("sections=" + sectionList.size());

      for (int sec = 0; sec < sectionList.size(); sec++)// for each section...
      {
        Section sectionXml =(Section) sectionList.get(sec);
        Map sectionMap = exHelper.mapSection(sectionXml);
        log.debug("SECTION MAP=" + sectionMap);
        // create the assessment section
        SectionFacade section =
            new SectionFacade();
//            assessmentService.addSection("" + assessment.getAssessmentId());
        exHelper.updateSection(section, sectionMap);
        // make sure we are the creator
        log.debug("section " + section.getTitle() +
          "created by '" + me+ "'.");
        section.setCreatedBy(me);
        section.setCreatedDate(assessment.getCreatedDate());
        section.setLastModifiedBy(me);
        section.setLastModifiedDate(assessment.getCreatedDate());
        section.setTypeId(TypeIfc.DEFAULT_SECTION);
        section.setStatus(Integer.valueOf(1));
        // set the sequence
        section.setSequence(Integer.valueOf(sec + 1));
//        // add the section to the assessment
//        section.setAssessmentId(assessment.getAssessmentId());//many to one
//        section.setAssessment(assessment);
//        assessment.getSectionArray().add(section);// one to many

        List itemList = exHelper.getItemXmlList(sectionXml);
        for (int itm = 0; itm < itemList.size(); itm++)// for each item
        {
          log.debug("items=" + itemList.size());
          Item itemXml = (Item) itemList.get(itm);
          Map itemMap = exHelper.mapItem(itemXml);
          log.debug("ITEM MAP=" + itemMap);

          ItemFacade item = new ItemFacade();
          exHelper.updateItem(item, itemXml, itemMap);
          // make sure required fields are set
          item.setCreatedBy(me);
          item.setCreatedDate(assessment.getCreatedDate());
          item.setLastModifiedBy(me);
          item.setLastModifiedDate(assessment.getCreatedDate());
          log.debug("ITEM TYPE IS: " +item.getTypeId());
          item.setStatus(ItemDataIfc.ACTIVE_STATUS);
          // assign the next sequence number
          item.setSequence(Integer.valueOf(itm + 1));
          // add item to section
          item.setSection(section);// one to many
          section.addItem(item);// many to one
//          itemService.saveItem(item);
          // debugging
//          Set metaSet = item.getItemMetaDataSet();
//          Iterator iter = metaSet.iterator();
//          if (log.isDebugEnabled())
//          {
//            while (iter.hasNext())
//            {
//              ItemMetaData meta = (ItemMetaData) iter.next();
//              log.debug("ITEM DEBUG meta " + meta.getLabel() +
//                "=" + meta.getEntry());
//            }
//          }
          log.debug("ITEM:  ans key" + item.getAnswerKey() );
          log.debug("ITEM:  correct feed" + item.getCorrectItemFeedback() );
          log.debug("ITEM:  incorrect feed " + item.getInCorrectItemFeedback() );
          log.debug("ITEM:  by " + item.getCreatedBy() );
          log.debug("ITEM:  date" + item.getCreatedDate() );
          log.debug("ITEM:  desc " + item.getDescription() );
          log.debug("ITEM:  duration" + item.getDuration() );
          log.debug("ITEM:  general feed " + item.getGeneralItemFeedback() );
          log.debug("ITEM:  incorrect " + item.getInCorrectItemFeedback() );
          log.debug("ITEM:  is true " + item.getIsTrue() );
          log.debug("ITEM DEBUG item text" + item.getText() );
          log.debug("ITEM:  item text" + item.getText() );
        }// ... end for each item
      }// ... end for each section

      log.debug("assessment created by '" + assessment.getCreatedBy() + "'.");
//      assessmentService.update(assessment);
      // debugging
      log.debug("ASSESSMENT:  meta " + assessment.getAssessmentMetaDataMap());
      log.debug("ASSESSMENT:  feed " + assessment.getAssessmentFeedback());
      log.debug("ASSESSMENT:  comments  " + assessment.getComments());
      log.debug("ASSESSMENT:  by " + assessment.getCreatedBy());
      log.debug("ASSESSMENT:  by date " + assessment.getCreatedDate());
      log.debug("ASSESSMENT:  desc" + assessment.getDescription());
      log.debug("ASSESSMENT:  disp " + assessment.getDisplayName());
      log.debug("ASSESSMENT:  last by " + assessment.getLastModifiedBy());
      log.debug("ASSESSMENT:  last date" + assessment.getLastModifiedDate());
      log.debug("ASSESSMENT:  mult " + assessment.getMultipartAllowed());
      log.debug("ASSESSMENT:  title " + assessment.getTitle());
      log.debug("ASSESSMENT DEBUG title " + assessment.getTitle());
//      assessmentService.saveAssessment(assessment);
    }
    catch(RuntimeException e)
    {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }

    return assessment;
  }