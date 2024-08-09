static XjcRdeRegistrar convertRegistrar(Registrar model) {
    XjcRdeRegistrar bean = new XjcRdeRegistrar();

    // o  An <id> element that contains the Registry-unique identifier of
    //    the registrar object.  This <id> has a superordinate relationship
    //    to a subordinate <clID>, <crRr> or <upRr> of domain, contact and
    //    host objects.
    bean.setId(model.getClientId());

    // o  An <name> element that contains the name of the registrar.
    bean.setName(model.getRegistrarName());

    // o  An OPTIONAL <gurid> element that contains the ID assigned by
    //    ICANN.
    Long ianaId = model.getIanaIdentifier();
    if (ianaId != null) {
      bean.setGurid(BigInteger.valueOf(ianaId));
    }

    // o  A <status> element that contains the operational status of the
    //    registrar.  Possible values are: ok, readonly and terminated.
    checkState(
        REGISTRAR_STATUS_CONVERSIONS.containsKey(model.getState()),
        "Unknown registrar state: %s",
        model.getState());
    bean.setStatus(REGISTRAR_STATUS_CONVERSIONS.get(model.getState()));

    // o  One or two <postalInfo> elements that contain postal- address
    //    information.  Two elements are provided so that address
    //    information can be provided in both internationalized and
    //    localized forms; a "type" attribute is used to identify the two
    //    forms.  If an internationalized form (type="int") is provided,
    //    element content MUST be represented in a subset of UTF-8 that can
    //    be represented in the 7-bit US-ASCII character set.  If a
    //    localized form (type="loc") is provided, element content MAY be
    //    represented in unrestricted UTF-8.
    RegistrarAddress localizedAddress = model.getLocalizedAddress();
    if (localizedAddress != null) {
      bean.getPostalInfos().add(convertPostalInfo(false, localizedAddress));
    }
    RegistrarAddress internationalizedAddress = model.getInternationalizedAddress();
    if (internationalizedAddress != null) {
      bean.getPostalInfos().add(convertPostalInfo(true, internationalizedAddress));
    }

    // o  An OPTIONAL <voice> element that contains the registrar's voice
    //    telephone number.
    // XXX: Make Registrar use PhoneNumber.
    if (model.getPhoneNumber() != null) {
      XjcContactE164Type phone = new XjcContactE164Type();
      phone.setValue(model.getPhoneNumber());
      bean.setVoice(phone);
    }

    // o  An OPTIONAL <fax> element that contains the registrar's facsimile
    //    telephone number.
    if (model.getFaxNumber() != null) {
      XjcContactE164Type fax = new XjcContactE164Type();
      fax.setValue(model.getFaxNumber());
      bean.setFax(fax);
    }

    // o  An <email> element that contains the registrar's email address.
    bean.setEmail(firstNonNull(model.getEmailAddress(), UNKNOWN_EMAIL));

    // o  An OPTIONAL <url> element that contains the registrar's URL.
    bean.setUrl(model.getUrl());

    // o  An OPTIONAL <whoisInfo> elements that contains whois information.
    //    The <whoisInfo> element contains the following child elements:
    //
    //    *  An OPTIONAL <name> element that contains the name of the
    //       registrar WHOIS server listening on TCP port 43 as specified in
    //       [RFC3912].
    //
    //    *  An OPTIONAL <url> element that contains the name of the
    //       registrar WHOIS server listening on TCP port 80/443.
    if (model.getWhoisServer() != null) {
      XjcRdeRegistrarWhoisInfoType whoisInfo = new XjcRdeRegistrarWhoisInfoType();
      whoisInfo.setName(model.getWhoisServer());
      bean.setWhoisInfo(whoisInfo);
    }

    // o  A <crDate> element that contains the date and time of registrar-
    //    object creation.
    bean.setCrDate(model.getCreationTime());

    // o  An OPTIONAL <upDate> element that contains the date and time of
    //    the most recent RDE registrar-object modification.  This element
    //    MUST NOT be present if the rdeRegistrar object has never been
    //    modified.
    bean.setUpDate(model.getLastUpdateTime());

    return bean;
  }