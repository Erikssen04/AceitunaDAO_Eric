package org.example.view;

import org.example.DAOimp.*;
import org.example.controller.FactoriaConexion;
import org.example.controller.GeneradorFicheros;
import org.example.controller.LectorFicheros;
import org.example.model.*;


import org.example.wrapper.*;
import java.io.*;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = FactoriaConexion.getConnection();

        if (conn != null) {
            Scanner sc = new Scanner(System.in);

            FactoriaConexion.MakeTrigger();

            AlmazaraDAOImp almazaraDAOImp = new AlmazaraDAOImp();
            CuadrillaDAOImp cuadrillaDAOImp = new CuadrillaDAOImp();
            OlivarDAOImp olivarDAOImp = new OlivarDAOImp();
            ProduccionDAOImp produccionDAOImp = new ProduccionDAOImp();
            TrabajadorDAOImp trabajadorDAOImp = new TrabajadorDAOImp();

            Almazaras almazarasWrapper = new Almazaras(almazaraDAOImp.findAllAlmazaras());
            Cuadrillas cuadrillasWrapper = new Cuadrillas(cuadrillaDAOImp.findAllCuadrillas());
            Olivares olivaresWrapper = new Olivares(olivarDAOImp.findAllOlivares());
            Producciones produccionesWrapper = new Producciones(produccionDAOImp.findAllProducciones());
            Trabajadores trabajadoresWrapper = new Trabajadores(trabajadorDAOImp.findAllTrabajadores());

            CampanaAceituna campanaAceituna = new CampanaAceituna(
                    almazarasWrapper, cuadrillasWrapper, olivaresWrapper, produccionesWrapper, trabajadoresWrapper);

            //Prueba del Trigger
            try {
                Produccion produccionPrueba1 = new Produccion(1, 1, 1, "2024-11-01", 1800.0);
                Produccion produccionPrueba2 = new Produccion(1, 1, 1, "2024-11-01", -1500.0);
                produccionDAOImp.createProduccion(produccionPrueba1);
                System.out.println("Producción insertada exitosamente.");

                produccionDAOImp.createProduccion(produccionPrueba2);
                System.out.println("Producción insertada exitosamente.");

            } catch (RuntimeException e) {
                System.out.println("Error al insertar la producción: " + e.getMessage());

            }

            /*
            // Crear y agregar 10 trabajadores
            trabajadorDAOImp.createTrabajador(new Trabajador("Carlos López", 30, "Recolector", 1500.0));
            trabajadorDAOImp.createTrabajador(new Trabajador("Ana Pérez", 28, "Recolectora", 1400.0));
            trabajadorDAOImp.createTrabajador(new Trabajador("Juan García", 35, "Encargado", 2000.0));
            trabajadorDAOImp.createTrabajador(new Trabajador("Marta Rodríguez", 25, "Recolectora", 1350.0));
            trabajadorDAOImp.createTrabajador(new Trabajador("Luis Martínez", 40, "Conductor", 1800.0));
            trabajadorDAOImp.createTrabajador(new Trabajador("Sofía Gómez", 32, "Recolectora", 1450.0));
            trabajadorDAOImp.createTrabajador(new Trabajador("Pedro Sánchez", 29, "Recolector", 1500.0));
            trabajadorDAOImp.createTrabajador(new Trabajador("Clara Fernández", 34, "Contable", 2100.0));
            trabajadorDAOImp.createTrabajador(new Trabajador("Raúl Torres", 27, "Recolector", 1400.0));
            trabajadorDAOImp.createTrabajador(new Trabajador("Lucía Morales", 31, "Recolectora", 1550.0));

            // Crear y agregar 3 almazaras
            almazaraDAOImp.createAlmazara(new Almazara("Almazara El Olivo", "Granada", 2254));
            almazaraDAOImp.createAlmazara(new Almazara("Aceites del Sur", "Jaén", 800));
            almazaraDAOImp.createAlmazara(new Almazara("La Joya de Andalucía", "Sevilla", 400));

            // Crear y agregar 5 cuadrillas
            cuadrillaDAOImp.createCuadrilla(new Cuadrilla("Cuadrilla 1", 1));
            cuadrillaDAOImp.createCuadrilla(new Cuadrilla("Cuadrilla 2", 2));
            cuadrillaDAOImp.createCuadrilla(new Cuadrilla("Cuadrilla 3", 3));
            cuadrillaDAOImp.createCuadrilla(new Cuadrilla("Cuadrilla 4", 4));
            cuadrillaDAOImp.createCuadrilla(new Cuadrilla("Cuadrilla 5", 5));

            // Crear y agregar 5 olivares
            olivarDAOImp.createOlivar(new Olivar("Granada", 50.0, 1200.0));
            olivarDAOImp.createOlivar(new Olivar("Jaén", 70.0, 1500.0));
            olivarDAOImp.createOlivar(new Olivar("Sevilla", 60.0, 1400.0));
            olivarDAOImp.createOlivar(new Olivar("Córdoba", 80.0, 1800.0));
            olivarDAOImp.createOlivar(new Olivar("Málaga", 45.0, 1000.0));

            // Crear y agregar 5 producciones
            produccionDAOImp.createProduccion(new Produccion(1, 1, 1, "2024-11-01", 1500.0));
            produccionDAOImp.createProduccion(new Produccion(2, 2, 2, "2024-11-05", 1800.0));
            produccionDAOImp.createProduccion(new Produccion(3, 3, 3, "2024-11-10", 2000.0));
            produccionDAOImp.createProduccion(new Produccion(4, 4, 1, "2024-11-15", 1700.0));
            produccionDAOImp.createProduccion(new Produccion(5, 5, 2, "2024-11-20", 1900.0));

            // Asociar cuadrillas a olivares
            cuadrillaDAOImp.asociarCuadrillaOlivar(1, 1); // Cuadrilla 1 con Olivar 1
            cuadrillaDAOImp.asociarCuadrillaOlivar(2, 2); // Cuadrilla 2 con Olivar 2
            cuadrillaDAOImp.asociarCuadrillaOlivar(3, 3); // Cuadrilla 3 con Olivar 3
            cuadrillaDAOImp.asociarCuadrillaOlivar(4, 4); // Cuadrilla 4 con Olivar 4
            cuadrillaDAOImp.asociarCuadrillaOlivar(5, 5); // Cuadrilla 5 con Olivar 5

            // Asociar trabajadores a cuadrillas

            // Cuadrilla 1
            trabajadorDAOImp.asociarTrabajadorCuadrilla(1, 1);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(2, 1);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(3, 1);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(4, 1);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(5, 1);

            // Cuadrilla 2
            trabajadorDAOImp.asociarTrabajadorCuadrilla(6, 2);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(7, 2);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(8, 2);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(9, 2);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(10, 2);

            // Cuadrilla 3
            trabajadorDAOImp.asociarTrabajadorCuadrilla(1, 3);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(2, 3);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(3, 3);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(4, 3);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(5, 3);

            // Cuadrilla 4
            trabajadorDAOImp.asociarTrabajadorCuadrilla(6, 4);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(7, 4);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(8, 4);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(9, 4);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(10, 4);

            // Cuadrilla 5
            trabajadorDAOImp.asociarTrabajadorCuadrilla(1, 5);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(2, 5);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(3, 5);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(4, 5);
            trabajadorDAOImp.asociarTrabajadorCuadrilla(5, 5);

             */


            System.out.println("Bienvenido a AceitunaDAO (By Eric Macià): ");

            String menu = "Seleccione una opción válida: \n" +
                    "1. Mostrar trabajadores de una determinada cuadrilla \n" +
                    "2. Mostrar cuadrillas que supervisa un determinado trabajador \n" +
                    "3. Mostrar olivares donde trabaja una determinada cuadrilla \n" +
                    "4. Mostrar cuadrillas que trabajan en un determinado olivar \n" +
                    "5. Mostrar almazaras donde lleva aceituna una determinada cuadrilla \n" +
                    "6. Mostrar la produccion en una fecha concreta, de una cuadrilla concreta en una almazara concreta \n" +
                    "7. Mostrar la produccion hasta una determinada fecha de una almazara determinada \n" +
                    "8. Mostrar la produccion hasta una determinada fecha de un olivar determinado  \n" +
                    "9. Mostrar la produccion hasta una determinada fecha de una cuadrilla determinada \n" +
                    "10. Hacer JSON de la Base de Datos \n" +
                    "11. Hacer XML de la Base de Datos \n" +
                    "12. Leer fichero JSON  \n" +
                    "13. Leer fichero XML \n" +
                    "0. Salir";

            boolean opcionValida = false;
            int opcion;

            // Bucle que muestra el menú y solo sale de la aplicación si la opción no corresponde a la condición
            do{
                try {
                    System.out.println(menu);
                    opcion = sc.nextInt();
                    if (opcion >= 0 && opcion <= 13) {
                        opcionValida = true;

                             // Se ejecutará la opción que hayamos introducido
                             switch (opcion) {
                                case 1:
                                    System.out.print("Se procederá a mostrar los trabajadores" +
                                            " que hay en una cuadrilla. \nIngrese el id de la cuadrilla: ");
                                    int idCuadrilla1 = sc.nextInt();

                                    System.out.println("Lista de Trabajadores de la cuadrilla " + idCuadrilla1 + ":");
                                    List<Trabajador> trabajadores = trabajadorDAOImp.getTrabajadoresByCuadrilla(idCuadrilla1);
                                    for (Trabajador trabajador : trabajadores) {
                                        System.out.println(trabajador.toString());
                                    }
                                    break;
                                case 2:
                                    System.out.print("Se mostrarán las cuadrillas supervisadas" +
                                            " por un determinado trabajador. \nIngrese el id del trabajador: ");
                                    int idTrabajador1 = sc.nextInt();

                                    System.out.println("Cuadrillas supervisadas por el trabajador con id " + idTrabajador1 + ":");
                                    List<Cuadrilla> cuadrillas = cuadrillaDAOImp.getCuadrillasBySupervisor(idTrabajador1);
                                    for (Cuadrilla cuadrilla : cuadrillas) {
                                        System.out.println(cuadrilla.toString());
                                    }
                                    break;
                                case 3:
                                    System.out.print("Se mostrarán los olivares donde trabaja" +
                                            " una determinada cuadrilla. \nPor favor, Ingrese el id de la cuadrilla: ");
                                    int idCuadrilla2 = sc.nextInt();

                                    System.out.println("Olivares donde trabaja la cuadrilla " + idCuadrilla2 + ":");
                                    List<Olivar> olivares = olivarDAOImp.getOlivaresByCuadrilla(idCuadrilla2);
                                    for (Olivar olivar : olivares) {
                                        System.out.println(olivar.toString());
                                    }
                                    break;
                                case 4:
                                    System.out.print("Se mostrarán las cuadrillas que trabajan " +
                                            "un determinado olivar. \nIngrese el id del olivar: ");
                                    int idOlivar1 = sc.nextInt();

                                    System.out.println("Cuadrillas que trabajan en el olivar " + idOlivar1 + ":");
                                    List<Cuadrilla> cuadrillasByOlivar = cuadrillaDAOImp.getCuadrillasByOlivar(idOlivar1);
                                    for (Cuadrilla cuadrilla : cuadrillasByOlivar) {
                                        System.out.println(cuadrilla.toString());
                                    }
                                    break;
                                case 5:
                                    System.out.print("Se mostrarán las almazaras donde lleva aceituna " +
                                            "a una determinada cuadrilla. \nPor favor, ingrese el id de la cuadrilla: ");
                                    int idCuadrilla3 = sc.nextInt();

                                    System.out.println("Almazaras donde lleva aceituna la cuadrilla " + idCuadrilla3 + ":");
                                    List<Almazara> almazaras = almazaraDAOImp.getAlmazarasByCuadrilla(idCuadrilla3);
                                    for (Almazara almazara : almazaras) {
                                        System.out.println(almazara.toString());
                                    }
                                    break;
                                case 6:
                                    System.out.println("Se mostrará la producción según fecha, cuadrilla y almazara concreta");

                                    // Parámetros necesarios para encontrar la producción concreta
                                    System.out.print("Por favor, ingrese la fecha (YYYY-MM-DD): ");
                                    String fecha1 = sc.next();

                                    System.out.print("Ahora introduzca el id de la cuadrilla: ");
                                    int idCuadrilla4 = sc.nextInt();

                                    System.out.print("Por último, ingrese el id de la almazara: ");
                                    int idAlmazara2 = sc.nextInt();

                                    System.out.println("Producción según fecha, cuadrilla y almazara específica: ");
                                    System.out.println(produccionDAOImp.getProduccionByFechaCuadrillaAlmazara(fecha1, idCuadrilla4, idAlmazara2));
                                    break;
                                case 7:
                                    System.out.println("Se mostrará la producción por almazara, hasta una fecha determinada");

                                    // Parámetros necesarios para encontrar la producción concreta
                                    System.out.println("Por favor, ingrese el id de la almazara: ");
                                    int idAlmazara3 = sc.nextInt();

                                    System.out.println("Ahora, Ingrese la fecha (YYYY-MM-DD): ");
                                    String fecha2 = sc.next();

                                    System.out.println("Producción hasta la fecha " + fecha2 + ":");
                                    System.out.println(produccionDAOImp.getProduccionByAlmazaraUntilFecha(fecha2, idAlmazara3));
                                    break;
                                case 8:
                                    System.out.println("Se mostrará la producción por olivar, hasta una fecha determinada");

                                    // Parámetros necesarios para encontrar la producción concreta
                                    System.out.println("Por favor, ingrese el id del olivar: ");
                                    int idOlivar8 = sc.nextInt();

                                    System.out.println("Ahora, ingrese la fecha (YYYY-MM-DD): ");
                                    String fecha3 = sc.next();

                                    System.out.println("Producción hasta la fecha " + fecha3 + ":");
                                    System.out.println(produccionDAOImp.getProduccionByOlivarUntilFecha(fecha3, idOlivar8));
                                    break;
                                case 9:
                                    System.out.println("Se mostrará la producción por cuadrilla, hasta una fecha determinada");

                                    // Parámetros necesarios para encontrar la producción concreta
                                    System.out.println("Por favor, ingrese el id de la cuadrilla: ");
                                    int idCuadrilla5 = sc.nextInt();

                                    System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
                                    String fecha4 = sc.nextLine();

                                    System.out.println("Producción hasta la fecha " + fecha4 + ":");
                                    System.out.println(produccionDAOImp.getProduccionByCuadrillaUntilFecha(fecha4, idCuadrilla5));
                                    break;
                                 case 10:
                                     GeneradorFicheros.generateJSON(campanaAceituna);
                                     break;
                                 case 11:
                                     GeneradorFicheros.generateXML(campanaAceituna);
                                     break;
                                 case 12:
                                     LectorFicheros.readJSON();
                                     break;
                                 case 13:
                                     LectorFicheros.readXML();
                                     break;
                                case 0:
                                    System.out.println("Se ha decidido salir del programa AceitunaDAO");
                                    opcionValida = false;
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println();
                                    break;
                            }
                    } else {
                        System.out.println("Por favor, ingrese una opción válida");
                    }
                    System.out.println();
                } catch (IllegalArgumentException e) {
                    System.out.println(("Los datos introducidos no son válidos, por favor intente de nuevo"));
                } catch (InputMismatchException f) {
                    System.out.println(("Acaba de colocar un tipo de dato que no corresponde a lo que se ha pedido"));
                }
            } while (opcionValida);
            FactoriaConexion.closeConnection();
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
}