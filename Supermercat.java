import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Supermercat {
    public static ArrayList<Producte> productes = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int opcio;
        int opcioSubMenu;


        opcio = menuInicial();

        switch (opcio) {
            case 0:
                System.exit(1);
                break;
            case 1:

                do {


                    System.out.println("""
                            1 Alimentacio
                            2 Textil
                            3 Electronica
                            0 Tornar
                             """);
                    opcioSubMenu = scan.nextInt();
                    switch (opcioSubMenu) {
                        case 0:
                            menuInicial();
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
                            System.out.println("Introduce una opcion valida");
                    }
                } while (opcioSubMenu != 0);


            case 2:

                break;
            case 3:

                break;
            default:
                System.out.println("Introduce una opcion valida");
        }

    }

    public static void afegirTextil() throws Exception {
        float preu;
        String nom;
        int codiBarres;
        String composicio;

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
        if (productes.size() >= 100) throw new Exception("No pueden introducirse más de 100 productos");
        productes.add(new Textil(preu, nom, codiBarres, composicio));
    }

    public static void afegirElectronica() throws Exception {
        float preu;
        String nom;
        int codiBarres;
        int dias_garantia;

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
        if (productes.size() >= 100) throw new Exception("No pueden introducirse más de 100 productos");
        productes.add(new Electronica(preu, nom, codiBarres, dias_garantia));
    }

    public static void afegirAliment() throws Exception {
        float preu;
        String nom;
        int codiBarres;
        LocalDate dataCaducitat;

        System.out.println("Alimentacion:");
        System.out.println(" Introduce el precio:");
        preu = scan.nextFloat();
        System.out.println(" Introduce el nombre:");
        nom = scan.nextLine();
        scan.nextLine();
        System.out.println(" Introduce el codigo de barras:");
        codiBarres = scan.nextInt();
        System.out.println(" Introduce la fecha de caducidad (dd/mm/aaaa):");
        dataCaducitat = LocalDate.parse(scan.nextLine());
        scan.nextLine();
        if (productes.size() >= 100) throw new Exception("No pueden introducirse más de 100 productos");
        productes.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
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

}
