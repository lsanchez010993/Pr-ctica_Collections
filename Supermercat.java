import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Supermercat {
    public static ArrayList<Producte> productes = new ArrayList<>();
    public static ArrayList<Alimentacio> alimentacio = new ArrayList<>();
    public static ArrayList<Textil> textil = new ArrayList<>();
    public static ArrayList<Electronica> electronica = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        menu();

    }

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
                pasar_X_Caja();
                break;
            case 3:

                break;
            default:
                System.out.println("Introduce una opcion valida");
        }


    }

    public static void pasar_X_Caja() {
        for (Producte prod : alimentacio) {
            System.out.println("Alimentacio:");

        }
        for (Producte prod : textil) {
            System.out.println("Textil:");

        }
        for (Producte prod : electronica) {
            System.out.println("Electronica:");

        }
    }

    public static void afegirTextil() {
        float preu;
        String nom;
        int codiBarres;
        String composicio;
        if (noSuperaNumMaxProd(productes)) {
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
            //Añade el producto/alimento al arrayList de textil
            textil.add(new Textil(preu, nom, codiBarres, composicio));
            //Luego añado el producte al array list productes.
            //En productes se encuentran todos los productos. Mediante este array compruebo el
            //numero de productos introducidos
            productes.add(new Textil(preu, nom, codiBarres, composicio));
        } else System.out.println("Se ha alcanzado el numero maximo de productos que se pueden introducir. Pasa por caja");


    }

    public static void afegirElectronica() {
        float preu;
        String nom;
        int codiBarres;
        int dias_garantia;
        if (noSuperaNumMaxProd(productes)) {
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
        } else System.out.println("Se ha alcanzado el numero maximo de productos que se pueden introducir. Pasa por caja");


    }

    public static void afegirAliment() {
        float preu;
        String nom;
        int codiBarres;
        LocalDate dataCaducitat;
        //Mediante el array pruductes compruebo el numero de productos totales introducidos
        //En caso de que se exceda el numero de productos muestra mensaje de error.
        if (noSuperaNumMaxProd(productes)) {
            System.out.println("Alimentacion:");
            System.out.println(" Introduce el precio:");
            preu = scan.nextFloat();
            System.out.println(" Introduce el nombre:");
            nom = scan.nextLine();
            scan.nextLine();
            System.out.println(" Introduce el codigo de barras:");
            codiBarres = scan.nextInt();
            System.out.println(" Introduce la fecha de caducidad (aaaa-MM-dd):");
            scan.nextLine();
            dataCaducitat = LocalDate.parse(scan.nextLine());
            //Añade el producto/alimento al arrayList de alimentacio
            alimentacio.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
            //Luego añado el producte al array list productes.
            //En productes se encuentran todos los productos. Mediante este array compruebo el
            //numero de productos introducidos
            productes.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
        } else System.out.println("Se ha alcanzado el numero maximo de productos que se pueden introducir. Pasa por caja");
    }

    /**
     * @param p ArrayList de productos
     * @return Devuelve true o false en funcion del numero de productos
     */
    public static boolean noSuperaNumMaxProd(ArrayList<Producte> p) {
        return (p.size() <= 100);


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
