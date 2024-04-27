import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;
import java.io.BufferedReader;

import java.io.InputStreamReader;


public class Supermercat {
    public static ArrayList<Producte> productes = new ArrayList<>();
    public static ArrayList<Alimentacio> alimentacio = new ArrayList<>();
    public static ArrayList<Textil> textil = new ArrayList<>();
    public static ArrayList<Electronica> electronica = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);


    public static void menu() throws Exception {
        int opcio;
        int opcioSubMenu;
        opcio = menuInicial();

        switch (opcio) {
            case 0:
                System.exit(1);
                break;
            case 1:
                do {
                    opcioSubMenu = subMenu();
                    switch (opcioSubMenu) {
                        case 0:
                            menu();
                            break;
                        case 1:
                            afegirAliment();

                            break;
                        case 2:
                            afegirTextil();

                            break;
                        case 3:
                            afegirElectronica();
                            break;
                        default:
                            System.out.println("Introduce una opcion valida:\n");
                    }
                } while (opcioSubMenu != 0);
                break;

            case 2:
                leerArchivo("UpdateTextilPrices.dat");
                pasar_X_Caja();
                break;
            case 3:
                carroCompra();
                buscarNomPerCodiBarres();
                break;
            case 4:

            default:
                System.out.println("Introduce una opcion valida");
        }


    }

    /**
     * La funcion se encarga de contar y eliminar los productos repetidos mediante un bucle for anidado.
     * La razón por la que no he utilizado HasSet para eliminar repetidos se debe a que previamente he tenido que contarlos
     * para saber la cantidad.
     *
     * @param listaProductos Lista de productos pasada por parametro.
     */
    public static <T extends Producte> void contar_Y_EliminarRepetidos(List<T> listaProductos, boolean carroCompra) {
        Map<String, Integer> numRepetidos = new HashMap<>();

        // Cuento la cantidad de productos repetidos por clave utilizando un operador ternario.
        for (Producte producto : listaProductos) {
            String clave = carroCompra ? String.valueOf(producto.getCodiBarres()) : producto.getNom() + "-" + producto.getCodiBarres();
            numRepetidos.put(clave, numRepetidos.getOrDefault(clave, 0) + 1);
        }

        // Actualizo la cantidad de productos y elimino los repetidos utilizando un operador ternario.
        for (int i = 0; i < listaProductos.size(); i++) {
            Producte producto = listaProductos.get(i);
            String clave = carroCompra ? String.valueOf(producto.getCodiBarres()) : producto.getNom() + "-" + producto.getCodiBarres();
            int cantidad = numRepetidos.get(clave);
            producto.setCantidad(cantidad);
            for (int j = i + 1; j < listaProductos.size(); j++) {
                Producte otroProducto = listaProductos.get(j);
                String otraClave = carroCompra ? String.valueOf(otroProducto.getCodiBarres()) : otroProducto.getNom() + "-" + otroProducto.getCodiBarres();
                if (clave.equals(otraClave)) {
                    listaProductos.remove(j);
                    j--;
                }
            }
        }
    }


    /**
     * @param listaProductos Lista de productos almacenas en arrayList
     * @return Devuelve el resultado de la suma de los precios de todos los productos
     */
    public static float calcularTotal(ArrayList<Producte> listaProductos) {
        float total = 0;
        for (Producte p : listaProductos) {
            total += p.getPreu();
        }
        return total;
    }

    public static void mostrarTicket(Producte prod) {

        // Muestra todos los productos que pertenecen a Alimentacio:

        System.out.printf("%-20s %-10d %-15.2f %-15.2f \n", prod.getNom(), prod.getCantidad(), prod.getPreu(), prod.getPreu() * prod.getCantidad());


    }


    public static void vaciarCarro() {
        productes.clear();

    }

    public static void pasar_X_Caja() {

        //La funcion 'calcularTotal' guarda en una variable la suma total del precio de los productos almacenados en el carro
        //previamente a que la funcion 'contar_Y_EliminarRepetidos' cuente y elimine los productos repetidos.
        productes.addAll(Alimentacio.getProductesAlimentacio());
        productes.addAll(Textil.getProductesTextils());
        productes.addAll(Electronica.getProductesElectronics());

        float total = calcularTotal(productes);
        contar_Y_EliminarRepetidos(Alimentacio.getProductesAlimentacio(), false);
        contar_Y_EliminarRepetidos(Textil.getProductesTextils(), false);
        contar_Y_EliminarRepetidos(Electronica.getProductesElectronics(), false);

        //
        System.out.println("___________________");
        System.out.println("Fecha de compra: " + LocalDate.now());
        System.out.println("SapaMercat");
        System.out.println("-------------------");
        System.out.println("Detall:");
        System.out.printf("%-20s %-10s %-15s %s\n", "Nom", "Cantidad", "Preu unitari", "Preu Total");

        System.out.println("Textil:");
        //Ordeno del productos textiles por composicion
        Collections.sort(Textil.getProductesTextils());
        for (Textil textil : Textil.getProductesTextils()) {
            mostrarTicket(textil);

        }
        System.out.println("Alimentacio");
        for (Alimentacio ali : Alimentacio.getProductesAlimentacio()) {
            //Muestra todos los productos que pertenece a Alimentacio:
            mostrarTicket(ali);
        }

        System.out.println("Electronica:");
        for (Electronica elec : Electronica.getProductesElectronics()) {

            mostrarTicket(elec);


        }
        System.out.println();
        System.out.printf("%-20s %-10s %-5s %-1s %1.2f €\n", "", "", "", "Total a pagar:", total);


        vaciarCarro();

    }

    /**
     * Funcion que implementa la clase Stream. Permite buscar el nombre de un objeto a partir del codigo de barras
     */
    public static void buscarNomPerCodiBarres() {
        int codigoBarras;
        System.out.println("Introduce el codigo de barras para buscar el nombre de un producto:");
        codigoBarras = scan.nextInt();
        Stream<Producte> listaProductes = productes.stream();
        String nom = listaProductes
                .filter(producto -> producto.getCodiBarres() == codigoBarras)
                .map(Producte::getNom)
                .findFirst()
                .orElse("Producto no encontrado");
        System.out.println(nom);
    }

    public static void buscarCodBarrasYActualizarPrecio(Map<Integer, Float> codiPreuMap) {
        for (Textil textil1 : Textil.getProductesTextils()) {
            // Verificar si el código de barras del producto está en el HashMap
            if (codiPreuMap.containsKey(textil1.getCodiBarres())) {
                // Obtener el precio del producto del HashMap y asignarlo a Textil
                float precio = codiPreuMap.get(textil1.getCodiBarres());
                textil1.setPreu(precio);
            }
        }
    }

    public static void leerArchivo(String nomArchivo) {
        String rutaArchivo = "./updates/" + nomArchivo;
        Map<Integer, Float> codiPreuMap = new HashMap<>();
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        try {
            File file = new File(rutaArchivo);
            if (file.exists()) {
                inputStream = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String linea;
                int contador = 0;
                while ((linea = reader.readLine()) != null) {
                    contador++;
                    if (contador == 1) continue;
                    String[] lineaProducto = linea.split(", ");
                    //Guardo los datos leidos en un Map donde cada codigo de barras unico tiene un precio.
                    codiPreuMap.put(Integer.parseInt(lineaProducto[0]), Float.parseFloat(lineaProducto[1]));
                }
            }
            //Funcion que que compara el codigo de barras de todos los productos introducidos en la lista textil.
            //Si encuentra una coincidencia, actualiza el precio del producto
            buscarCodBarrasYActualizarPrecio(codiPreuMap);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            // Cierro los objetos de lectura en el bloque finally
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el flujo de lectura: " + e.getMessage());
            }
        }
    }

    public static void carroCompra() {


        contar_Y_EliminarRepetidos(Alimentacio.getProductesAlimentacio(), true);
        contar_Y_EliminarRepetidos(Textil.getProductesTextils(), true);
        contar_Y_EliminarRepetidos(Electronica.getProductesElectronics(), true);
        //Ordeno del productos textiles por composicion
        Collections.sort(Textil.getProductesTextils());
        productes.addAll(Alimentacio.getProductesAlimentacio());
        productes.addAll(Textil.getProductesTextils());
        productes.addAll(Electronica.getProductesElectronics());

        //creo el HashMap.
        HashMap<Integer, Producte> hashMap = new HashMap<>();
        //Añado al HashMap los productos que hay en la lista
        for (int i = 0; i < productes.size(); i++) {
            hashMap.put(i, productes.get(i));
        }

        System.out.println("___________________");
        System.out.println("Productes");
        System.out.println("-------------------");
        System.out.println("Detall:");
        System.out.printf("%-20s %-10s\n", "Nom:", "Cantidad:\n");

        //Muestro los productos utilizando forEach con lambda expresions:
        hashMap.forEach((key, value) -> System.out.printf("%-20s %-10d \n", value.getNom(), value.getCantidad()));


    }

    public static void afegirTextil() {
        float preu = 0;
        String nom = "";
        int codiBarres = 0;
        String composicio = "";
        try {
            if (noSuperaNumMaxProd()) {
                System.out.println("Textil:");
                System.out.println(" Introduce el precio:");
                preu = scan.nextFloat();
                System.out.println(" Introduce el nombre:");
                nom = scan.nextLine();
                scan.nextLine();
                System.out.println(" Introduce el codigo de barras:");
                codiBarres = scan.nextInt();
                System.out.println(" Introduce la composicion:");
                composicio = scan.nextLine();
                scan.nextLine();
                //Añade el producto al arrayList de textil
                textil.add(new Textil(preu, nom, codiBarres, composicio));
                //Luego añado el producte al array list productes.
                //En productes se encuentran todos los productos. Mediante este array compruebo el
                //numero de productos introducidos
                productes.add(new Textil(preu, nom, codiBarres, composicio));
            } else {
                System.out.println("Se ha alcanzado el número máximo de productos que se pueden introducir. Pasa por caja");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida. Por favor, asegúrate de ingresar un número válido para el precio y el código de barras.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Cerrar el Scanner en el bloque finally
            if (scan != null) {
                scan.close();
            }
        }
    }

    public static void afegirElectronica() {
        float preu = 0;
        String nom = "";
        int codiBarres = 0;
        int dias_garantia = 0;
        try {
            if (noSuperaNumMaxProd()) {
                System.out.println("Electronica:");
                System.out.println(" Introduce el precio:");
                preu = scan.nextFloat();
                System.out.println(" Introduce el nombre:");
                nom = scan.nextLine();
                scan.nextLine();
                System.out.println(" Introduce el codigo de barras:");
                codiBarres = scan.nextInt();
                System.out.println(" Introduce los días de garantia:");
                dias_garantia = scan.nextInt();
                //Añade el producto/alimento al arrayList de electronica
                electronica.add(new Electronica(preu, nom, codiBarres, dias_garantia));
                //Luego añado el producte al array list productes.
                //En productes se encuentran todos los productos. Mediante este array compruebo el
                //numero de productos introducidos
                productes.add(new Electronica(preu, nom, codiBarres, dias_garantia));
            } else {
                System.out.println("Se ha alcanzado el número máximo de productos que se pueden introducir. Pasa por caja");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida. Por favor, asegúrate de ingresar un número válido para el precio, código de barras y los días de garantía.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Cerrar el Scanner en el bloque finally
            if (scan != null) {
                scan.close();
            }
        }
    }

    public static void afegirAliment() {
        float preu = 0;
        String nom = "";
        int codiBarres = 0;
        LocalDate dataCaducitat = null;
        try {
            if (noSuperaNumMaxProd()) {
                System.out.println("Alimentacion:");
                System.out.println(" Introduce el precio:");
                preu = scan.nextFloat();
                scan.nextLine();
                System.out.println(" Introduce el nombre:");
                nom = scan.nextLine();
                System.out.println(" Introduce el codigo de barras:");
                codiBarres = scan.nextInt();
                scan.nextLine();
                System.out.println(" Introduce la fecha de caducidad (aaaa-MM-dd):");
                dataCaducitat = LocalDate.parse(scan.nextLine());

                //Añade el producto/alimento al arrayList de alimentacio
                alimentacio.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
                //Luego añado el producte al array list productes.
                //En productes se encuentran todos los productos. Mediante este array compruebo el
                //numero de productos introducidos
                productes.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
            } else {
                System.out.println("Se ha alcanzado el número máximo de productos que se pueden introducir. Pasa por caja");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida. Por favor, asegúrate de ingresar un número válido para el precio y el código de barras, y una fecha de caducidad en el formato correcto (aaaa-MM-dd).");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Cerrar el Scanner en el bloque finally
            if (scan != null) {
                scan.close();
            }
        }
    }
    /**
     * @return Devuelve true o false en funcion del numero de productos
     */
    public static boolean noSuperaNumMaxProd() {
        int total = Textil.getProductesTextils().size() + Electronica.getProductesElectronics().size() +
                Alimentacio.getProductesAlimentacio().size();
        return (total < 10);


    }

    public static int menuInicial() {
        System.out.println("""
                Menu:
                1. Introduir producte
                2. Pasar per caixa
                3. Mostrar carro de la compra
                0. Sortir
                """);
        return scan.nextInt();
    }

    public static int subMenu() {
        System.out.println("""
                Menu agregar producto
                1 Alimentacio
                2 Textil
                3 Electronica
                0 Tornar
                 """);
        return scan.nextInt();
    }

}
