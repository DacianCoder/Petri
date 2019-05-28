import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tranzitie {
    private List<Arc> intrari = new ArrayList<>();
    private List<Arc> iesiri = new ArrayList<>();

    private Map<String, Integer> puncte;

    public Tranzitie(Map<String, Integer> puncte) {
        this.puncte = puncte;
    }

    public void adaugaIntrare(String origine, Integer valoare) {
        intrari.add(new Arc(origine, valoare));
    }

    public void adaugaIesire(String origine, Integer valoare) {
        iesiri.add(new Arc(origine, valoare));
    }


    public boolean poateRula() {
        return intrari.stream().allMatch(arc -> puncte.get(arc.numePunct) >= arc.valoare);
    }

    public void ruleaza() {
        if (!poateRula()) {
            return;
        }
        intrari.forEach(arc -> {
            int valoarePunctNoua = puncte.get(arc.numePunct) - arc.valoare;
            puncte.put(arc.numePunct, valoarePunctNoua);
        });
        iesiri.forEach(arc -> {
            int valoarePunctNoua = puncte.get(arc.numePunct) + arc.valoare;
            puncte.put(arc.numePunct, valoarePunctNoua);
        });
    }
}
