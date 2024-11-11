package core.data;

import com.google.common.collect.ImmutableMap;
import core.model.Challenge;

import java.util.Collections;
import java.util.Map;

import static core.data.Constants.STAR;

public class HtmlPatchProvider {
    private static final Map<String, Map<String, String>> HTML_PATCHES_PER_CHALLENGE =
            ImmutableMap.<String, Map<String, String>>builder()
                    .put("2022_05", Map.of("fontclass", "font class"))
                    .put("2021_02", Map.of("(only the \"*\" part)", ""))
                    .put("2014_03", Map.of("Pa</font></b><b><font color=\"#0000FF\">l", "Pal"))
                    .put("2013_12", Map.of("Nas</font></b><b><font color=\"#0000FF\">h", "Nash"))
                    .put("2013_07", Map.of("Heineke</font></b><b><font color=\"#0000FF\">n", "Heineken"))
                    .put("2013_05", Map.of("J</font></b><b><font color=\"#0000FF\">oseph", "Joseph",
                            "Biti</font></b><b><font color=\"#0000FF\">n", "Bitin"))
                    .put("2013_04", Map.of("Mark Stucke</font></b><b><font color=\"#0000FF\">l", "Mark Stuckel",
                            "Chuck Carrol</font></b><b><font color=\"#0000FF\">l", "Chuck Carroll",
                            "Ambooj Mitta</font></b><b><font color=\"#0000FF\">l", "Ambooj Mittal",
                            "Soma Bhanute</font></b><b><font color=\"#0000FF\">j", "Soma Bhanutej"))
                    .put("2013_02", Map.of("Albert Stadle</font></b><b><font color=\"#0000FF\">r", "Albert Stadler"))
                    .put("2013_01", Map.of("&amp; </font></b><b><font color=\"#0000FF\">", "&amp; ",
                            "*</font></b><b><font color=\"#0000FF\">Johannes Waldmann", "*Johannes Waldmann",
                            "*</font></b><b><font color=\"#0000FF\">", STAR,
                            "</font></b><b><font color=\"#0000FF\">t", "t"))
                    .put("2012_12", Map.of("**</font></b><b><font color=\"#0000FF\">*Matej Kollar", "***Matej Kollar",
                            "***</font></b><b><font color=\"#0000FF\">Peter Gerritson", "***Peter Gerritson",
                            "***</font></b><b><font color=\"#0000FF\">Michael Rosola", "***Michael Rosola"))
                    .put("2012_11", Map.of("Roberto Tauraso", "<b><font color=\"#0000FF\">Roberto Tauraso</font></b>",
                            "Khodadad</font></b><b><font color=\"#0000FF\">i", "Khodadadi",
                            "Hyung Sik Hwang", "<b><font color=\"#0000FF\">Hyung Sik Hwang</font></b>"))
                    .put("2012_05", Map.of("Hisaj</font></b><b><font color=\"#0000FF\">i", "Hisaji"))
                    .put("2011_08", Map.of("N</font></b><b><font color=\"#0000FF\">oah Easterly", "Noah Easterly"))
                    .put("2011_05", Map.of("Kipp Johnson</font></b> <b><font color=\"#0000FF\">&amp; Nick Vigo", "Kipp Johnson &amp; Nick Vigo"))
                    .put("2011_04", Map.of("Andrei Sipo</font></b><b><font color=\"#0000FF\">s", "Andrei Sipos"))
                    .put("2010_09", Map.of("Gergely</font></b> <b><font color=\"#0000FF\">&amp;", "Gergely&amp;"))
                    .put("2010_05", Map.of("Dan C</font></b><b><font color=\"#0000FF\">olestock", "Dan Colestock",
                            "J</font></b><b><font color=\"#0000FF\">onathan", "Jonathan"))
                    .put("2010_03", Map.of("T</font></b><b><font color=\"#0000FF\">om Sirgedas", "Tom Sirgedas",
                            "B</font></b><b><font color=\"#0000FF\">rian Chen", "Brian Chen"))
                    .put("2010_01", Map.of("A</font></b><b><font color=\"#0000FF\">dam Daire", "Adam Daire",
                            "M</font></b><b><font color=\"#0000FF\">olochlike", "Molochlike",
                            "H</font></b><b><font color=\"#0000FF\">eesung Shin", "Heesung Shin"))
                    .put("2009_12", Map.of("V</font></b><b><font color=\"#0000FF\">ladimir", "Vladimir"))
                    .put("2009_11", Map.of("A</font></b><b><font color=\"#0000FF\">lan", "Alan"))
                    .put("2009_10", Map.of("V</font></b><b><font color=\"#0000FF\">ictor", "Victor"))
                    .put("2009_09", Map.of("J</font></b><b><font color=\"#0000FF\">oe", "Joe"))
                    .put("2009_08", Map.of("E</font></b><b><font color=\"#0000FF\">li", "Eli"))
                    .put("2009_07", Map.of("Reiner</font></b> <b><font color=\"#0000FF\">Martin", "Reiner Martin",
                            "T</font></b><b><font color=\"#0000FF\">ravis", "Travis"))
                    .put("2009_06", Map.of("K</font></b><b><font color=\"#0000FF\">arthik", "Karthik"))
                    .put("2009_05", Map.of("S</font></b><b><font color=\"#0000FF\">achin", "Sachin",
                            "A</font></b><b><font color=\"#0000FF\">marpal", "Amarpal"))
                    .put("2009_01", Map.of("EST)<b><font color=\"#0000FF\">", "EST)"))
                    .put("2007_12", Map.of("<b><font color=\"#0000FF\">Eyal", "Eyal"))
                    .put("2007_08", Map.of("Eryil</font></b><b><font color=\"#0000FF\">maz", "Eryilmaz"))
                    .put("2006_05", Map.of("Jam</font></b><b><font color=\"#0000FF\">es", "James"))
                    .put("2005_09", Map.of("S</font></b><b><font color=\"#0000FF\">teve", "Steve"))
                    .put("2005_05", Map.of("</font></b> <b><font color=\"#0000FF\">Fendel", " Fendel",
                            "</font></b> <b><font color=\"#0000FF\">Smith", " Smith"))
                    .put("2005_02", Map.of("</font></b> <b><font color=\"#0000FF\">Visegrady", " Visegrady",
                            "</font></b> <b><font color=\"#0000FF\">Fendel", " Fendel"))
                    .put("2005_01", Map.of("</font></b> <b><font color=\"#0000FF\">Mallepally", "",
                            "</font></b> <b><font color=\"#0000FF\">Fendel", " Fendel"))
                    .put("2004_11", Map.of("Chakravarthy</font></b><b><font color=\"#0000FF\">s", "Chakravarthys",
                            "</font></b><b><font color=\"#0000FF\">Benedek", "Benedek",
                            "</font></b><b><font color=\"#0000FF\">Vedantam", "Vedantam",
                            "</font></b><b><font color=\"#0000FF\">Andrew", "Andrew",
                            "T</font></b><b><font color=\"#0000FF\">ikhonov", "Tikhonov",
                            "</font></b> <b><font color=\"#0000FF\">Kim", " Kim"))
                    .put("2004_09", Map.of("</font></b><b><font color=\"#0000FF\">Fletcher", "Fletcher",
                            "</font></b> <b><font color=\"#0000FF\">K.", " K."))
                    .put("2004_08", Map.of("I</font></b><b><font color=\"#0000FF\">gor", "Igor",
                            "Cha</font></b><b><font color=\"#0000FF\">kravarthy", "Chakravarthy",
                            "C</font></b><b><font color=\"#0000FF\">hris", "Chris",
                            "</font></b><b><font color=\"#0000FF\">Fletcher", "Fletcher",
                            "</font></b><b><font color=\"#0000FF\">Jinhong", "Jinhong"))
                    .put("2004_07", Map.of("D</font></b><b><font color=\"#0000FF\">harma", "Dharma"))
                    .put("2004_05", Map.of("</font></b><b><font color=\"#0000FF\">Fletcher", "Fletcher"))
                    .put("2004_04", Map.of("K</font></b><b><font color=\"#0000FF\">alyana", "Kalyana",
                            "Kenned</font></b><b><font color=\"#0000FF\">y", "Kennedy",
                            "</font></b> <b><font color=\"#0000FF\">Fendel", " Fendel",
                            "K</font></b><b><font color=\"#0000FF\">rishna", "Krishna",
                            "</font></b><font size=\"2\"> </font><b><font color=\"#0000FF\">Vessey", " Vessey",
                            "</font></b><font size=\"2\"> </font><b><font color=\"#0000FF\">Stewart", " Stewart",
                            "A</font></b><b><font color=\"#0000FF\">nanda", "Ananda",
                            "</font></b><b><font color=\"#0000FF\">Markandan", " Markandan"))
                    .put("2004_03", Map.of("</font></b><b><font color=\"#0000FF\">", ""))
                    .put("2004_01", Map.of("Grotmo</font></b><b><font color=\"#0000FF\">l", "Grotmol"))
                    /***
                     * Deliberately manipulating XPath to cause a mismatch with {@link Constants#XPATH_OPTIONS_OF_CORRECT_SUBMISSIONS}
                     * **/
                    .put("2003_10", Map.of("High Score:", "<div>", "Attention:", "</div>"))
                    /***
                     * Deliberately manipulating XPath to cause a mismatch with {@link Constants#XPATH_OPTIONS_OF_CORRECT_SUBMISSIONS}
                     * **/
                    .put("2003_08", Map.of("High Scores:", "<div>", "Attention:", "</div>"))
                    .put("2003_05", Map.of("<tt>", "", "</tt>", ""))
                    .put("2003_04", Map.of("Eit</font></b><b><font color=\"#0000FF\">zen", "Eitzen"))
                    .put("2003_01", Map.of("Mallepaly</font></b><b><font color=\"#0000FF\">ly", "Mallepalyly",
                            "Tiwa</font></b><b><font color=\"#0000FF\">ri", "Tiwari",
                            "Max</font></b><b><font color=\"#0000FF\">i</font></b><b><font color=\"#0000FF\">m", "Maxim"))
                    .put("2002_12", Map.of("D</font></b><b><font color=\"#0000FF\">as", "Das",
                            "Bhar</font></b><b><font color=\"#0000FF\">ath", "Bharath"))
                    .put("2002_10", Map.of("Pe</font></b><b><font color=\"#0000FF\">ter", "Peter"))
                    .put("2001_05", Map.of("</font></b> <b><font color=\"#0000FF\">Flies", "Flies"))
                    /***
                     * Deliberately manipulating XPath to cause a mismatch with {@link Constants#XPATH_OPTIONS_OF_CORRECT_SUBMISSIONS}
                     * **/
                    .put("2000_10", Map.of("Eureka!", "<div>", "Attention:", "</div>"))
                    .build();

    public Map<String, String> retrievePatchFor(Challenge challenge) {
        String key = challenge.toString();
        if (HTML_PATCHES_PER_CHALLENGE.containsKey(key)) {
            return HTML_PATCHES_PER_CHALLENGE.get(key);
        }
        return Collections.emptyMap();
    }
}
