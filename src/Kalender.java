import java.util.ArrayList;

public class Kalender {
    private String name;
    private ArrayList<Termin> termine;
    private ArrayList<Terminserie> serien;
    public void init(){
        name = "Kalender";
        termine = new ArrayList<>();
        serien = new ArrayList<>();
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addTermin(Termin termin){
        if (termin == null) {throw new NullPointerException();}
        if (termine == null) {termine = new ArrayList<>();}
        termine.add(termin);
    }
    public void addSerie(Terminserie terminserie){
        if (terminserie == null) {throw new NullPointerException();}
        if (this.serien == null) {throw new NullPointerException();}
        this.serien.add(terminserie);
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + ":\n");
        for (var termin : termine){
            stringBuilder.append(termin).append("\n");
        }
        for (var terminSerien : serien){
            for (int index = 0; index < terminSerien.getAnzahl(); index++) {
                stringBuilder.append(terminSerien.getTermin(index)).append("\n");
            }
        }
        return stringBuilder.toString();
    }
    public String ausgeben(){
        return this.toString();
    }
}
