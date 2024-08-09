boolean parse() {
    int lineno;
    int charno;

    // JSTypes are represented as Rhino AST nodes, and then resolved later.
    JSTypeExpression type;

    state = State.SEARCHING_ANNOTATION;
    skipEOLs();

    JsDocToken token = next();

    List<ExtendedTypeInfo> extendedTypes = Lists.newArrayList();

    // Always record that we have a comment.
    if (jsdocBuilder.shouldParseDocumentation()) {
      ExtractionInfo blockInfo = extractBlockComment(token);
      token = blockInfo.token;
      if (!blockInfo.string.isEmpty()) {
        jsdocBuilder.recordBlockDescription(blockInfo.string);
      }
    } else {
      if (token != JsDocToken.ANNOTATION &&
          token != JsDocToken.EOC) {
        // Mark that there was a description, but don't bother marking
        // what it was.
        jsdocBuilder.recordBlockDescription("");
      }
    }

    // Parse the actual JsDoc.
    retry: for (;;) {
      switch (token) {
        case ANNOTATION:
          if (state == State.SEARCHING_ANNOTATION) {
            state = State.SEARCHING_NEWLINE;
            lineno = stream.getLineno();
            charno = stream.getCharno();

            String annotationName = stream.getString();
            Annotation annotation = annotationNames.get(annotationName);
            if (annotation == null) {
              parser.addParserWarning("msg.bad.jsdoc.tag", annotationName,
                  stream.getLineno(), stream.getCharno());
            } else {
              // Mark the beginning of the annotation.
              jsdocBuilder.markAnnotation(annotationName, lineno, charno);

              switch (annotation) {
                case AUTHOR:
                  if (jsdocBuilder.shouldParseDocumentation()) {
                    ExtractionInfo authorInfo = extractSingleLineBlock();
                    String author = authorInfo.string;

                    if (author.length() == 0) {
                      parser.addParserWarning("msg.jsdoc.authormissing",
                          stream.getLineno(), stream.getCharno());
                    } else {
                      jsdocBuilder.addAuthor(author);
                    }
                    token = authorInfo.token;
                  } else {
                    token = eatTokensUntilEOL(token);
                  }
                  continue retry;

                case CONSISTENTIDGENERATOR:
                  if (!jsdocBuilder.recordConsistentIdGenerator()) {
                    parser.addParserWarning("msg.jsdoc.consistidgen",
                      stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case CONSTANT:
                  if (!jsdocBuilder.recordConstancy()) {
                    parser.addParserWarning("msg.jsdoc.const",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case CONSTRUCTOR:
                  if (!jsdocBuilder.recordConstructor()) {
                    if (jsdocBuilder.isInterfaceRecorded()) {
                      parser.addTypeWarning("msg.jsdoc.interface.constructor",
                          stream.getLineno(), stream.getCharno());
                    } else {
                      parser.addTypeWarning("msg.jsdoc.incompat.type",
                          stream.getLineno(), stream.getCharno());
                    }
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case DEPRECATED:
                  if (!jsdocBuilder.recordDeprecated()) {
                    parser.addParserWarning("msg.jsdoc.deprecated",
                        stream.getLineno(), stream.getCharno());
                  }

                  // Find the reason/description, if any.
                  ExtractionInfo reasonInfo =
                      extractMultilineTextualBlock(token);

                  String reason = reasonInfo.string;

                  if (reason.length() > 0) {
                    jsdocBuilder.recordDeprecationReason(reason);
                  }

                  token = reasonInfo.token;
                  continue retry;

                case INTERFACE:
                  if (!jsdocBuilder.recordInterface()) {
                    if (jsdocBuilder.isConstructorRecorded()) {
                      parser.addTypeWarning("msg.jsdoc.interface.constructor",
                          stream.getLineno(), stream.getCharno());
                    } else {
                      parser.addTypeWarning("msg.jsdoc.incompat.type",
                          stream.getLineno(), stream.getCharno());
                    }
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case DESC:
                  if (jsdocBuilder.isDescriptionRecorded()) {
                    parser.addParserWarning("msg.jsdoc.desc.extra",
                        stream.getLineno(), stream.getCharno());
                    token = eatTokensUntilEOL();
                    continue retry;
                  } else {
                    ExtractionInfo descriptionInfo =
                        extractMultilineTextualBlock(token);

                    String description = descriptionInfo.string;

                    jsdocBuilder.recordDescription(description);
                    token = descriptionInfo.token;
                    continue retry;
                  }

                case FILE_OVERVIEW:
                  String fileOverview = "";
                  if (jsdocBuilder.shouldParseDocumentation()) {
                    ExtractionInfo fileOverviewInfo =
                        extractMultilineTextualBlock(token,
                            WhitespaceOption.TRIM);

                    fileOverview = fileOverviewInfo.string;

                    token = fileOverviewInfo.token;
                  } else {
                    token = eatTokensUntilEOL(token);
                  }

                  if (!jsdocBuilder.recordFileOverview(fileOverview)) {
                    parser.addParserWarning("msg.jsdoc.fileoverview.extra",
                        stream.getLineno(), stream.getCharno());
                  }
                  continue retry;

                case LICENSE:
                case PRESERVE:
                  ExtractionInfo preserveInfo =
                      extractMultilineTextualBlock(token,
                                                   WhitespaceOption.PRESERVE);

                  String preserve = preserveInfo.string;

                  if (preserve.length() > 0) {
                    if (fileLevelJsDocBuilder != null) {
                      fileLevelJsDocBuilder.append(preserve);
                    }
                  }

                  token = preserveInfo.token;
                  continue retry;

                case ENUM:
                  token = next();
                  lineno = stream.getLineno();
                  charno = stream.getCharno();

                  type = null;
                  if (token != JsDocToken.EOL && token != JsDocToken.EOC) {
                    type = createJSTypeExpression(
                        parseAndRecordTypeNode(token));
                  }

                  if (type == null) {
                    type = createJSTypeExpression(newStringNode("number"));
                  }
                  if (!jsdocBuilder.recordEnumParameterType(type)) {
                    parser.addTypeWarning(
                        "msg.jsdoc.incompat.type", lineno, charno);
                  }
                  token = eatTokensUntilEOL(token);
                  continue retry;

                case EXPORT:
                  if (!jsdocBuilder.recordExport()) {
                    parser.addParserWarning("msg.jsdoc.export",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case EXPOSE:
                  if (!jsdocBuilder.recordExpose()) {
                    parser.addParserWarning("msg.jsdoc.expose",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case EXTERNS:
                  if (!jsdocBuilder.recordExterns()) {
                    parser.addParserWarning("msg.jsdoc.externs",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case JAVA_DISPATCH:
                  if (!jsdocBuilder.recordJavaDispatch()) {
                    parser.addParserWarning("msg.jsdoc.javadispatch",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case EXTENDS:
                case IMPLEMENTS:
                  skipEOLs();
                  token = next();
                  lineno = stream.getLineno();
                  charno = stream.getCharno();
                  boolean matchingRc = false;

                  if (token == JsDocToken.LC) {
                    token = next();
                    matchingRc = true;
                  }

                  if (token == JsDocToken.STRING) {
                    Node typeNode = parseAndRecordTypeNameNode(
                        token, lineno, charno, matchingRc);

                    lineno = stream.getLineno();
                    charno = stream.getCharno();

                    typeNode = wrapNode(Token.BANG, typeNode);
                    if (typeNode != null && !matchingRc) {
                      typeNode.putBooleanProp(Node.BRACELESS_TYPE, true);
                    }
                    type = createJSTypeExpression(typeNode);

                    if (annotation == Annotation.EXTENDS) {
                      // record the extended type, check later
                      extendedTypes.add(new ExtendedTypeInfo(
                          type, stream.getLineno(), stream.getCharno()));
                    } else {
                      Preconditions.checkState(
                          annotation == Annotation.IMPLEMENTS);
                      if (!jsdocBuilder.recordImplementedInterface(type)) {
                        parser.addTypeWarning("msg.jsdoc.implements.duplicate",
                            lineno, charno);
                      }
                    }
                    token = next();
                    if (matchingRc) {
                      if (token != JsDocToken.RC) {
                        parser.addTypeWarning("msg.jsdoc.missing.rc",
                            stream.getLineno(), stream.getCharno());
                      }
                    } else if (token != JsDocToken.EOL &&
                        token != JsDocToken.EOF && token != JsDocToken.EOC) {
                      parser.addTypeWarning("msg.end.annotation.expected",
                          stream.getLineno(), stream.getCharno());
                    }
                  } else {
                    parser.addTypeWarning("msg.no.type.name", lineno, charno);
                  }
                  token = eatTokensUntilEOL(token);
                  continue retry;

                case HIDDEN:
                  if (!jsdocBuilder.recordHiddenness()) {
                    parser.addParserWarning("msg.jsdoc.hidden",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case LENDS:
                  skipEOLs();

                  matchingRc = false;
                  if (match(JsDocToken.LC)) {
                    token = next();
                    matchingRc = true;
                  }

                  if (match(JsDocToken.STRING)) {
                    token = next();
                    if (!jsdocBuilder.recordLends(stream.getString())) {
                      parser.addTypeWarning("msg.jsdoc.lends.incompatible",
                          stream.getLineno(), stream.getCharno());
                    }
                  } else {
                    parser.addTypeWarning("msg.jsdoc.lends.missing",
                        stream.getLineno(), stream.getCharno());
                  }

                  if (matchingRc && !match(JsDocToken.RC)) {
                    parser.addTypeWarning("msg.jsdoc.missing.rc",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case MEANING:
                  ExtractionInfo meaningInfo =
                      extractMultilineTextualBlock(token);
                  String meaning = meaningInfo.string;
                  token = meaningInfo.token;
                  if (!jsdocBuilder.recordMeaning(meaning)) {
                    parser.addParserWarning("msg.jsdoc.meaning.extra",
                        stream.getLineno(), stream.getCharno());
                  }
                  continue retry;

                case NO_ALIAS:
                  if (!jsdocBuilder.recordNoAlias()) {
                    parser.addParserWarning("msg.jsdoc.noalias",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case NO_COMPILE:
                  if (!jsdocBuilder.recordNoCompile()) {
                    parser.addParserWarning("msg.jsdoc.nocompile",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case NO_TYPE_CHECK:
                  if (!jsdocBuilder.recordNoTypeCheck()) {
                    parser.addParserWarning("msg.jsdoc.nocheck",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case NOT_IMPLEMENTED:
                  token = eatTokensUntilEOL();
                  continue retry;

                case INHERIT_DOC:
                case OVERRIDE:
                  if (!jsdocBuilder.recordOverride()) {
                    parser.addTypeWarning("msg.jsdoc.override",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case THROWS:
                  skipEOLs();
                  token = next();
                  lineno = stream.getLineno();
                  charno = stream.getCharno();
                  type = null;

                  if (token == JsDocToken.LC) {
                    type = createJSTypeExpression(
                        parseAndRecordTypeNode(token));

                    if (type == null) {
                      // parsing error reported during recursive descent
                      // recovering parsing
                      token = eatTokensUntilEOL();
                      continue retry;
                    }
                  }

                  // *Update* the token to that after the type annotation.
                  token = current();

                  // Save the throw type.
                  jsdocBuilder.recordThrowType(type);

                  // Find the throw's description (if applicable).
                  if (jsdocBuilder.shouldParseDocumentation()) {
                    ExtractionInfo descriptionInfo =
                        extractMultilineTextualBlock(token);

                    String description = descriptionInfo.string;

                    if (description.length() > 0) {
                      jsdocBuilder.recordThrowDescription(type, description);
                    }

                    token = descriptionInfo.token;
                  } else {
                    token = eatTokensUntilEOL(token);
                  }
                  continue retry;

                case PARAM:
                  skipEOLs();
                  token = next();
                  lineno = stream.getLineno();
                  charno = stream.getCharno();
                  type = null;

                  if (token == JsDocToken.LC) {
                    type = createJSTypeExpression(
                        parseAndRecordParamTypeNode(token));

                    if (type == null) {
                      // parsing error reported during recursive descent
                      // recovering parsing
                      token = eatTokensUntilEOL();
                      continue retry;
                    }
                    skipEOLs();
                    token = next();
                    lineno = stream.getLineno();
                    charno = stream.getCharno();
                  }

                  String name = null;
                  boolean isBracketedParam = JsDocToken.LB == token;
                  if (isBracketedParam) {
                    token = next();
                  }

                  if (JsDocToken.STRING != token) {
                    parser.addTypeWarning("msg.missing.variable.name",
                        lineno, charno);
                  } else {
                    name = stream.getString();

                    if (isBracketedParam) {
                      token = next();

                      // Throw out JsDocToolkit's "default" parameter
                      // annotation.  It makes no sense under our type
                      // system.
                      if (JsDocToken.EQUALS == token) {
                        token = next();
                        if (JsDocToken.STRING == token) {
                          token = next();
                        }
                      }

                      if (JsDocToken.RB != token) {
                        reportTypeSyntaxWarning("msg.jsdoc.missing.rb");
                      } else if (type != null) {
                        // Make the type expression optional, if it isn't
                        // already.
                        type = JSTypeExpression.makeOptionalArg(type);
                      }
                    }

                    // If the param name has a DOT in it, just throw it out
                    // quietly. We do not handle the JsDocToolkit method
                    // for handling properties of params.
                    if (name.indexOf('.') > -1) {
                      name = null;
                    } else if (!jsdocBuilder.recordParameter(name, type)) {
                      if (jsdocBuilder.hasParameter(name)) {
                        parser.addTypeWarning("msg.dup.variable.name", name,
                            lineno, charno);
                      } else {
                        parser.addTypeWarning("msg.jsdoc.incompat.type", name,
                            lineno, charno);
                      }
                    }
                  }

                  if (name == null) {
                    token = eatTokensUntilEOL(token);
                    continue retry;
                  }

                  jsdocBuilder.markName(name, sourceFile, lineno, charno);

                  // Find the parameter's description (if applicable).
                  if (jsdocBuilder.shouldParseDocumentation()) {
                    ExtractionInfo paramDescriptionInfo =
                        extractMultilineTextualBlock(token);

                    String paramDescription = paramDescriptionInfo.string;

                    if (paramDescription.length() > 0) {
                      jsdocBuilder.recordParameterDescription(name,
                          paramDescription);
                    }

                    token = paramDescriptionInfo.token;
                  } else {
                    token = eatTokensUntilEOL(token);
                  }
                  continue retry;

                case PRESERVE_TRY:
                  if (!jsdocBuilder.recordPreserveTry()) {
                    parser.addParserWarning("msg.jsdoc.preservertry",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case PRIVATE:
                  if (!jsdocBuilder.recordVisibility(Visibility.PRIVATE)) {
                    parser.addParserWarning("msg.jsdoc.visibility.private",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case PROTECTED:
                  if (!jsdocBuilder.recordVisibility(Visibility.PROTECTED)) {
                    parser.addParserWarning("msg.jsdoc.visibility.protected",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case PUBLIC:
                  if (!jsdocBuilder.recordVisibility(Visibility.PUBLIC)) {
                    parser.addParserWarning("msg.jsdoc.visibility.public",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case NO_SHADOW:
                  if (!jsdocBuilder.recordNoShadow()) {
                    parser.addParserWarning("msg.jsdoc.noshadow",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case NO_SIDE_EFFECTS:
                  if (!jsdocBuilder.recordNoSideEffects()) {
                    parser.addParserWarning("msg.jsdoc.nosideeffects",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case MODIFIES:
                  token = parseModifiesTag(next());
                  continue retry;

                case IMPLICIT_CAST:
                  if (!jsdocBuilder.recordImplicitCast()) {
                    parser.addTypeWarning("msg.jsdoc.implicitcast",
                        stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case SEE:
                  if (jsdocBuilder.shouldParseDocumentation()) {
                    ExtractionInfo referenceInfo = extractSingleLineBlock();
                    String reference = referenceInfo.string;

                    if (reference.length() == 0) {
                      parser.addParserWarning("msg.jsdoc.seemissing",
                          stream.getLineno(), stream.getCharno());
                    } else {
                      jsdocBuilder.addReference(reference);
                    }

                    token = referenceInfo.token;
                  } else {
                    token = eatTokensUntilEOL(token);
                  }
                  continue retry;

                case SUPPRESS:
                  token = parseSuppressTag(next());
                  continue retry;

                case TEMPLATE:
                  ExtractionInfo templateInfo = extractSingleLineBlock();
                  List<String> names = Lists.newArrayList(
                      Splitter.on(',')
                          .trimResults()
                          .split(templateInfo.string));

                  if (names.size() == 0 || names.get(0).length() == 0) {
                    parser.addTypeWarning("msg.jsdoc.templatemissing",
                          stream.getLineno(), stream.getCharno());
                  } else if (!jsdocBuilder.recordTemplateTypeNames(names)) {
                    parser.addTypeWarning("msg.jsdoc.template.at.most.once",
                        stream.getLineno(), stream.getCharno());
                  }

                  token = templateInfo.token;
                  continue retry;

                case IDGENERATOR:
                  if (!jsdocBuilder.recordIdGenerator()) {
                    parser.addParserWarning("msg.jsdoc.idgen",
                      stream.getLineno(), stream.getCharno());
                  }
                  token = eatTokensUntilEOL();
                  continue retry;

                case VERSION:
                  ExtractionInfo versionInfo = extractSingleLineBlock();
                  String version = versionInfo.string;

                  if (version.length() == 0) {
                    parser.addParserWarning("msg.jsdoc.versionmissing",
                          stream.getLineno(), stream.getCharno());
                  } else {
                    if (!jsdocBuilder.recordVersion(version)) {
                       parser.addParserWarning("msg.jsdoc.extraversion",
                          stream.getLineno(), stream.getCharno());
                    }
                  }

                  token = versionInfo.token;
                  continue retry;

                case DEFINE:
                case RETURN:
                case THIS:
                case TYPE:
                case TYPEDEF:
                  lineno = stream.getLineno();
                  charno = stream.getCharno();

                  Node typeNode = null;
                  if (!lookAheadForTypeAnnotation() &&
                      annotation == Annotation.RETURN) {
                    // If RETURN doesn't have a type annotation, record
                    // it as the unknown type.
                    typeNode = newNode(Token.QMARK);
                  } else {
                    skipEOLs();
                    token = next();
                    typeNode = parseAndRecordTypeNode(token);
                  }

                  if (annotation == Annotation.THIS) {
                    typeNode = wrapNode(Token.BANG, typeNode);
                    if (typeNode != null && token != JsDocToken.LC) {
                      typeNode.putBooleanProp(Node.BRACELESS_TYPE, true);
                    }
                  }
                  type = createJSTypeExpression(typeNode);

                  if (type == null) {
                    // error reported during recursive descent
                    // recovering parsing
                  } else {
                    switch (annotation) {
                      case DEFINE:
                        if (!jsdocBuilder.recordDefineType(type)) {
                          parser.addParserWarning("msg.jsdoc.define",
                              lineno, charno);
                        }
                        break;

                      case RETURN:
                        if (!jsdocBuilder.recordReturnType(type)) {
                          parser.addTypeWarning(
                              "msg.jsdoc.incompat.type", lineno, charno);
                          break;
                        }

                        // Find the return's description (if applicable).
                        if (jsdocBuilder.shouldParseDocumentation()) {
                          ExtractionInfo returnDescriptionInfo =
                              extractMultilineTextualBlock(token);

                          String returnDescription =
                              returnDescriptionInfo.string;

                          if (returnDescription.length() > 0) {
                            jsdocBuilder.recordReturnDescription(
                                returnDescription);
                          }

                          token = returnDescriptionInfo.token;
                        } else {
                          token = eatTokensUntilEOL(token);
                        }
                        continue retry;

                      case THIS:
                        if (!jsdocBuilder.recordThisType(type)) {
                          parser.addTypeWarning(
                              "msg.jsdoc.incompat.type", lineno, charno);
                        }
                        break;

                      case TYPE:
                        if (!jsdocBuilder.recordType(type)) {
                          parser.addTypeWarning(
                              "msg.jsdoc.incompat.type", lineno, charno);
                        }
                        break;

                      case TYPEDEF:
                        if (!jsdocBuilder.recordTypedef(type)) {
                          parser.addTypeWarning(
                              "msg.jsdoc.incompat.type", lineno, charno);
                        }
                        break;
                    }
                  }

                  token = eatTokensUntilEOL();
                  continue retry;
              }
            }
          }
          break;

        case EOC:
          if (hasParsedFileOverviewDocInfo()) {
            fileOverviewJSDocInfo = retrieveAndResetParsedJSDocInfo();
          }
          checkExtendedTypes(extendedTypes);
          return true;

        case EOF:
          // discard any accumulated information
          jsdocBuilder.build(null);
          parser.addParserWarning("msg.unexpected.eof",
              stream.getLineno(), stream.getCharno());
          checkExtendedTypes(extendedTypes);
          return false;

        case EOL:
          if (state == State.SEARCHING_NEWLINE) {
            state = State.SEARCHING_ANNOTATION;
          }
          token = next();
          continue retry;

        default:
          if (token == JsDocToken.STAR && state == State.SEARCHING_ANNOTATION) {
            token = next();
            continue retry;
          } else {
            state = State.SEARCHING_NEWLINE;
            token = eatTokensUntilEOL();
            continue retry;
          }
      }

      // next token
      token = next();
    }
  }