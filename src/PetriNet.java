import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PetriNet {
    public static final String p0 = "p0";
    public static final String p1 = "p1";
    public static final String p2 = "p2";
    public static final String p3 = "p3";

    public static final String t0 = "t0";
    public static final String t1 = "t1";

    public static void main(String[] args) throws IOException {
        Map<String, Integer> puncte = new HashMap<>();
        Map<String, Tranzitie> tranzitii = new HashMap<>();

        puncte.put(p0, 1);
        puncte.put(p1, 2);
        puncte.put(p2, 0);
        puncte.put(p3, 0);


        Tranzitie tranzitie = new Tranzitie(puncte);
        tranzitie.adaugaIntrare(p0, 1);
        tranzitie.adaugaIntrare(p1, 1);
        tranzitie.adaugaIesire(p2, 1);

        Tranzitie tranzitie2 = new Tranzitie(puncte);
        tranzitie2.adaugaIntrare(p2, 1);
        tranzitie2.adaugaIesire(p0, 2);
        tranzitie2.adaugaIesire(p3, 1);


        tranzitii.put(t0, tranzitie);
        tranzitii.put(t1, tranzitie2);

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String read;
        boolean rulat = false;

        while (true) {
            boolean tranzitiiValide = tranzitii.values().stream().anyMatch(Tranzitie::poateRula);
            if (!tranzitiiValide) {
                System.out.println("Nici o tranzitie valida, programul va iesi");
                if (rulat) {
                    printPuncte(puncte);
                }
                return;
            }
            System.out.println("Puncte valide");
            printPuncte(puncte);

            System.out.println("Introduceti o trazitie valida");
            printTranzitii(tranzitii);

            read = consoleReader.readLine();

            if (!tranzitii.containsKey(read)) {
                System.out.println("Please enter a valid transition");
                continue;
            }
            Tranzitie tranzitiaCurenta = tranzitii.get(read);
            if (!tranzitiaCurenta.poateRula()) {
                System.out.println("Please enter a valid transition");
            }
            tranzitiaCurenta.ruleaza();
            rulat = true;
        }
    }

    public static void printTranzitii(Map<String, Tranzitie> tranzitii) {
        System.out.print("Tranzitii active :");
        tranzitii.forEach((s, tranzitie) -> {
            if (tranzitie.poateRula()) {
                System.out.print(s + "  ");
            }
        });
        System.out.println();
    }

    public static void printPuncte(Map<String, Integer> puncte) {
        System.out.println("Puncte");
        puncte.forEach((s, integer) -> System.out.println(s + " : " + integer));
        System.out.println();

    }
}
