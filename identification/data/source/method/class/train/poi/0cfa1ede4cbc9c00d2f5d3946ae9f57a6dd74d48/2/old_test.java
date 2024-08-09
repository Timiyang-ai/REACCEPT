@Test
    public void read() throws IOException {
        HSLFSlideShow ppt = HSLFTestDataSamples.getSlideShow("text_shapes.ppt");

        List<String> lst1 = new ArrayList<String>();
        HSLFSlide slide = ppt.getSlides().get(0);
        for (HSLFShape shape : slide.getShapes()) {
            assertTrue("Expected TextShape but found " + shape.getClass().getName(), shape instanceof HSLFTextShape);
            HSLFTextShape tx = (HSLFTextShape)shape;
            List<HSLFTextParagraph> paras = tx.getTextParagraphs();
            assertNotNull(paras);
            int runType = paras.get(0).getRunType();

            ShapeType type = shape.getShapeType();
            String rawText = HSLFTextParagraph.getRawText(paras);
            switch (type){
                case TEXT_BOX:
                    assertEquals("Text in a TextBox", rawText);
                    break;
                case RECT:
                    if(runType == TextHeaderAtom.OTHER_TYPE) {
                        assertEquals("Rectangle", rawText);
                    } else if(runType == TextHeaderAtom.TITLE_TYPE) {
                        assertEquals("Title Placeholder", rawText);
                    }
                    break;
                case OCTAGON:
                    assertEquals("Octagon", rawText);
                    break;
                case ELLIPSE:
                    assertEquals("Ellipse", rawText);
                    break;
                case ROUND_RECT:
                    assertEquals("RoundRectangle", rawText);
                    break;
                default:
                    fail("Unexpected shape: " + shape.getShapeName());

            }
            lst1.add(rawText);
        }

        List<String> lst2 = new ArrayList<String>();
        for (List<HSLFTextParagraph> paras : slide.getTextParagraphs()) {
            lst2.add(HSLFTextParagraph.getRawText(paras));
        }

        assertTrue(lst1.containsAll(lst2));
        ppt.close();
    }