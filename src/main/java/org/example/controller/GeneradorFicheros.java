package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.wrapper.CampanaAceituna;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

public class GeneradorFicheros {

    public static void generateJSON(CampanaAceituna campanaAceituna) {
        File jsonFilePath = new File("Ficheros/AceitunaDB.json");

        try {
            // Creamos un objeto que utiliza la biblioteca Jackson para convertir objetos Java a formatos como JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Habilitar opción del objectMapper para formatear el fichero JSON adecuadamente.
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            // Serializar la lista de objetos a JSON
            objectMapper.writeValue(jsonFilePath, campanaAceituna);

            System.out.println("\nFichero JSON confeccionado exitosamente\n");

        } catch (IOException e) {
            System.out.println("Error: Ha surgido un error relacionado con el archivo: "+e.getMessage());
        }
    }

    public static void generateXML(CampanaAceituna campanaAceituna){
        File xmlFilePath = new File("Ficheros/AceitunaDB.xml");

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(CampanaAceituna.class);

            Marshaller marshaller = jaxbContext.createMarshaller();

            // Generar formato XML correcto e indentado
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(campanaAceituna,xmlFilePath);

            System.out.println("Fichero XML confeccionado exitosamente");

        } catch (JAXBException e) {
            System.out.println("Error: Ha surgido un error relacionado con el archivo: "+e.getMessage());
        }
    }
}
