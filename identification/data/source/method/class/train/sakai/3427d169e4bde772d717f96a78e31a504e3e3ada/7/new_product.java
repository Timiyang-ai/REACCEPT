public AssessmentFacade createImportedAssessment(Document document)
  {
    AssessmentFacade assessment = null;

    try
    {
      // we need to know who we are
      String me = AgentFacade.getAgentString();

      // create the assessment
      ExtractionHelper exHelper = new ExtractionHelper(this.qtiVersion);
      AssessmentService assessmentService = new AssessmentService();
      ItemService itemService = new ItemService();
      Assessment assessmentXml = new Assessment(document);
      Map assessmentMap = exHelper.mapAssessment(assessmentXml);
      String description = (String) assessmentMap.get("description");
      String title = (String) assessmentMap.get("title");
      assessment = assessmentService.createAssessmentWithoutDefaultSection(
        title, description, null, null);

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

      // process each section and each item withiassessmentn each section
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

          ItemFacade item = new ItemFacade();
          exHelper.updateItem(item, itemMap);
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
          section.addItem(item); // many to one
          itemService.saveItem(item);
        } // ... end for each item
        assessmentService.saveOrUpdateSection(section);

      } // ... end for each section

      // and add ip address restriction, if any
      String allowIp = assessment.getAssessmentMetaDataByLabel("ALLOW_IP");
      log.info("allowIp: " + allowIp);

      if (allowIp !=null)
      {
        log.info("NOT NULL: " + allowIp);
        exHelper.makeSecuredIPAddressSet(assessment, allowIp);
      }

      assessmentService.update(assessment);
      assessmentService.saveAssessment(assessment);
    }
    catch (Exception e)
    {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }

    return assessment;
  }