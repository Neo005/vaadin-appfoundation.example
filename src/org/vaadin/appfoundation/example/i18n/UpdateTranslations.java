package org.vaadin.appfoundation.example.i18n;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.vaadin.appfoundation.i18n.FillXml;

/**
 * This class keeps the translations xml file up-to-date
 * 
 * @author Kim
 * 
 */
public class UpdateTranslations {

    public static void main(String[] args) {
        File file = new File("src/translations.xml");

        List<String> tuids = new ArrayList<String>();
        for (SystemMsg msg : SystemMsg.values()) {
            tuids.add(msg.name());
        }

        try {
            FillXml.updateTranslations(file, new String[] { "en", "fi", "se" },
                    tuids);
        } catch (ValidityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParsingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
