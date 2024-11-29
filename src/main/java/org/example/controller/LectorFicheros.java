package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.wrapper.CampanaAceituna;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class LectorFicheros {

    public static void readXML(){
        File xmlFilePath = new File("Ficheros/AceitunaDB.xml");

        if (xmlFilePath.exists()) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(CampanaAceituna.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

                // Unmarshalling del fichero XML
                CampanaAceituna campanaAceituna = (CampanaAceituna) unmarshaller.unmarshal(xmlFilePath);

                /* Muestra el objeto de manera legible, linea por linea.
                   Haciendo un marshalling que se muestre directamente en consola. */
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(campanaAceituna, System.out);

                System.out.println("Fichero XML leído correctamente");

            } catch (JAXBException e) {
                System.out.println("Error: Fichero no existe. " + e.getMessage());
            }
        } else {
            System.out.println("No existe ningún fichero XML. Por favor genere uno primero.");
        }
    }

    public static void readJSON() {
        // Declaración del fichero JSON a ser utilizado
        File jsonFilePath = new File("Ficheros/AceitunaDB.json");

        if (jsonFilePath.exists()) {
            try {
                // Crear el ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();

                // Deserializar el JSON al objeto CampanaAceituna
                CampanaAceituna campanaAceituna = objectMapper.readValue(jsonFilePath, CampanaAceituna.class);

                // Mostrar el objeto deserializado del fichero JSON
                System.out.println("Contenido del JSON deserializado:");
                System.out.println(campanaAceituna.toString());

            } catch (IOException e) {
                System.out.println("Error: Fichero no existe. " + e.getMessage());
            }
        } else {
            System.out.println("No existe ningún fichero JSON. Por favor genere uno primero.");
        }
    }
}
