public AssessmentFacade createImportedAssessment(Document document, String unzipLocation, String templateId)
  {
	AssessmentFacade assessment = null;

    AssessmentService assessmentService = new AssessmentService();
    try
    {
      // we need to know who we are
      String me = AgentFacade.getAgentString();

      // create the assessment
      ExtractionHelper exHelper = new ExtractionHelper(this.qtiVersion);
      exHelper.setUnzipLocation(unzipLocation);
      ItemService itemService = new ItemService();
      Assessment assessmentXml = new Assessment(document);
      Map assessmentMap = exHelper.mapAssessment(assessmentXml);
      String description = XmlUtil.processFormattedText(log, (String) assessmentMap.get("description"));
      String title = XmlUtil.processFormattedText(log, (String) assessmentMap.get("title"));
      assessment = assessmentService.createAssessmentWithoutDefaultSection(
        title, exHelper.makeFCKAttachment(description), null, templateId);

      // now make sure we have a unique name for the assessment
      String baseId = assessment.getAssessmentBaseId().toString();
      boolean notUnique =
        !assessmentService.assessmentTitleIsUnique(baseId , title, false);

      if (notUnique)
      {
        synchronized (title)
        {
          log.debug("Assessment "+ title + " is not unique.");
          int count = 0; // alternate exit condition

          while (notUnique)
          {
            title = exHelper.renameDuplicate(title);
            log.debug("renameDuplicate(title): " + title);
            assessment.setTitle(title);
            notUnique =
               !assessmentService.assessmentTitleIsUnique(baseId , title, false);
            if (count++ > 99) break;// exit condition in case bug is introduced
          }
        }
      }

      // update the remaining assessment properties
      exHelper.updateAssessment(assessment, assessmentMap);

      // make sure required fields are set
      assessment.setCreatedBy(me);
      assessment.setCreatedDate(assessment.getCreatedDate());
      assessment.setLastModifiedBy(me);
      assessment.setLastModifiedDate(assessment.getCreatedDate());
      assessment.setTypeId(TypeIfc.QUIZ);
      assessment.setStatus(new Integer(1));
      // set comments
      String comments = (String) assessmentMap.get("comments");
      assessment.setComments(comments);


      // process each section and each item within each section
      List sectionList = exHelper.getSectionXmlList(assessmentXml);
      int sectionListSize = sectionList.size();
      log.debug("sections=" + sectionListSize);

      for (int sec = 0; sec < sectionListSize; sec++) // for each section...
      {
        Section sectionXml = (Section) sectionList.get(sec);
        Map sectionMap = exHelper.mapSection(sectionXml);
        // create the assessment section
        SectionFacade section =
          assessmentService.addSection("" + assessment.getAssessmentId());
        exHelper.updateSection(section, sectionMap);
        // make sure we are the creator
        section.setCreatedBy(me);
        section.setCreatedDate(assessment.getCreatedDate());
        section.setLastModifiedBy(me);
        section.setLastModifiedDate(assessment.getCreatedDate());
        section.setTypeId(TypeIfc.DEFAULT_SECTION);
        section.setStatus(new Integer(1));
        section.setSequence(new Integer(sec + 1));
        
        List itemList = exHelper.getItemXmlList(sectionXml);
        for (int itm = 0; itm < itemList.size(); itm++) // for each item
        {
          log.debug("items=" + itemList.size());
          Item itemXml = (Item) itemList.get(itm);
          Map itemMap = exHelper.mapItem(itemXml);

          // lydia debugging
          if (itemMap!=null && itemMap.keySet()!=null){
              Iterator iter = itemMap.keySet().iterator();

              while (iter.hasNext()) {
                 
                String label = (String) iter.next();
                String value="";
                if (itemMap.get(label)!=null){
                  value = (String) itemMap.get(label).toString();
                  log.debug("get Label: " + label + ", Value: " + value);
                }
                
              }
          }
          // end lydia

          ItemFacade item = new ItemFacade();
          if (itemMap != null) {
        	  exHelper.updateItem(item, itemMap);
          }
          // make sure required fields are set
          item.setCreatedBy(me);
          item.setCreatedDate(assessment.getCreatedDate());
          item.setLastModifiedBy(me);
          item.setLastModifiedDate(assessment.getCreatedDate());
          item.setStatus(ItemDataIfc.ACTIVE_STATUS);
          // assign the next sequence number
          item.setSequence(new Integer(itm + 1));
          // add item to section
          item.setSection(section); // one to many
          // update metadata with PARTID
          item.addItemMetaData(ItemMetaData.PARTID, section.getSectionId().toString());
           
          // Item Attachment
          exHelper.makeItemAttachmentSet(item);
          
          section.addItem(item); // many to one
          itemService.saveItem(item);
        } // ... end for each item
        
        // Section Attachment
      	exHelper.makeSectionAttachmentSet(section, sectionMap);

        assessmentService.saveOrUpdateSection(section);
      } // ... end for each section

      // and add ip address restriction, if any
      String allowIp = assessment.getAssessmentMetaDataByLabel("ALLOW_IP");
      //log.info("allowIp: " + allowIp);

      if (allowIp !=null && !allowIp.trim().equalsIgnoreCase("null"))
      {
        //log.info("NOT NULL: " + allowIp);
        exHelper.makeSecuredIPAddressSet(assessment, allowIp);
      }
      
      // Assessment Attachment
      exHelper.makeAssessmentAttachmentSet(assessment);

      assessmentService.saveAssessment(assessment);
      return assessment;
    }
    catch (Exception e)
    {
      log.error(e.getMessage(), e);
      assessmentService.removeAssessment(assessment.getAssessmentId().toString());
      throw new RuntimeException(e);
    }
  }