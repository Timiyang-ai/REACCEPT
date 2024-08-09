    @Test
    public void getColorTest() {
        verifyEqualColor(Color.RED, "#FF0000");
        verifyEqualColor(Color.RED, "#FFFF0000");
        verifyEqualColor(Color.GREEN, "#00ff00");
        verifyEqualColor(Color.BLUE, "#Ff0000Ff");

        verifyEqualColor(Color.BLACK, "#ff000000");
        verifyEqualColor(Color.WHITE, "#ffFFff");
        verifyEqualColor(Color.TRANSPARENT, "#00000000");

        verifyInvalidgetColor("#FF000");
        verifyInvalidgetColor("#00FF000");

        verifyInvalidgetColor("FF0000");
        verifyInvalidgetColor("00FF0000");

        verifyInvalidgetColor("#FF00000");
        verifyInvalidgetColor("#00FF00000");

        verifyInvalidgetColor(" #FF0000");
        verifyInvalidgetColor("# FF0000");
        verifyInvalidgetColor("#FF0000 ");

        verifyInvalidgetColor("#FFGGFF");
        verifyInvalidgetColor("#FFggFF");

        verifyInvalidgetColor("");
    }