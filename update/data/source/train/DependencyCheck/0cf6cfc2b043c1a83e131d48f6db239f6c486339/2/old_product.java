public void parse(File file) {
        InputStreamReader fr = null;
        BufferedReader br = null;
        Pattern rxEntry = Pattern.compile("^\\s*<entry\\s*id\\=\\\"([^\\\"]+)\\\".*$");
        Pattern rxEntryEnd = Pattern.compile("^\\s*</entry>.*$");
        Pattern rxFact = Pattern.compile("^\\s*<cpe\\-lang\\:fact\\-ref name=\\\"([^\\\"]+).*$");
        //Pattern rxSummary = Pattern.compile("^\\s*<vuln:summary>([^\\<]+).*$");
        try {

            fr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder(7000);
            String str = null;
            String id = null;
            Document doc = new Document();
            boolean skipEntry = true;
            boolean started = false;

            while ((str = br.readLine()) != null) {
                Matcher matcherEntryEnd = rxEntryEnd.matcher(str);

                if (started && !matcherEntryEnd.matches()) {
                    sb.append(str);
                }
                //facts occur more often, do them first.
                Matcher matcherFact = rxFact.matcher(str);
                if (matcherFact.matches()) {
                    String cpe = matcherFact.group(1);
                    if (cpe != null && cpe.startsWith("cpe:/a:")) {
                        skipEntry = false;
                        addVulnerableCpe(cpe, doc);
                    }
                    continue;
                }
                Matcher matcherEntry = rxEntry.matcher(str);
                if (matcherEntry.matches()) {
                    started = true;
                    id = matcherEntry.group(1);

                    sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                    sb.append("<vulnerabilityType ");
                    //sb.append("xmlns=\"http://scap.nist.gov/schema/feed/vulnerability/2.0\" ");
                    //sb.append("xmlns:vuln=\"http://scap.nist.gov/schema/vulnerability/0.4\" ");
                    sb.append("xmlns=\"http://scap.nist.gov/schema/vulnerability/0.4\" ");
                    sb.append("xmlns:vuln=\"http://scap.nist.gov/schema/vulnerability/0.4\" ");
                    //sb.append("xmlns:vulnerability=\"http://scap.nist.gov/schema/feed/vulnerability/2.0\" ");
                    sb.append("xmlns:cpe-lang=\"http://cpe.mitre.org/language/2.0\" ");
                    sb.append("xmlns:cvss2=\"http://scap.nist.gov/schema/cvss-v2/0.2\" ");
                    sb.append("xmlns:cvss=\"http://scap.nist.gov/schema/cvss-v2/0.2\" ");
                    sb.append("xmlns:scap-core=\"http://scap.nist.gov/schema/scap-core/0.1\"  ");
                    sb.append("xmlns:scap_core=\"http://scap.nist.gov/schema/scap-core/0.1\"  ");
                    sb.append("xmlns:patch=\"http://scap.nist.gov/schema/patch/0.1\"  ");
                    sb.append("xmlns:cve=\"http://scap.nist.gov/schema/cve/0.1\"  ");
                    sb.append("xmlns:cce=\"http://scap.nist.gov/schema/cce/0.1\"  ");

                    sb.append("id=\"").append(id).append("\">");
                    //sb.append(str); //need to do the above to get the correct schema generated from files.

                    Field name = new StringField(Fields.CVE_ID, id, Field.Store.NO);
                    doc.add(name);
                    continue;
                }
//                Matcher matcherSummary = rxSummary.matcher(str);
//                if (matcherSummary.matches()) {
//                    String summary = matcherSummary.group(1);
//                    Field description = new Field(Fields.DESCRIPTION, summary, Field.Store.NO);
//                    doc.add(description);
//                    continue;
//                }

                if (matcherEntryEnd.matches()) {
                    sb.append("</vulnerabilityType>");
                    Field xml = new StoredField(Fields.XML, sb.toString());
                    doc.add(xml);

                    if (!skipEntry) {
                        Term name = new Term(Fields.CVE_ID, id);
                        indexWriter.deleteDocuments(name);
                        indexWriter.addDocument(doc);
                        //indexWriter.updateDocument(name, doc);
                    }
                    //reset the document
                    doc = new Document();
                    sb = new StringBuilder(7000);
                    id = null;
                    skipEntry = true;
                    started = false;
                }
            }


        } catch (FileNotFoundException ex) {
            Logger.getLogger(NvdCveParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NvdCveParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(NvdCveParser.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(NvdCveParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }