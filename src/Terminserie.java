import java.time.LocalDateTime;

public class Terminserie {
    private String name;
    private final Termin[] termine;

    public Terminserie(String name, int number, LocalDateTime start, LocalDateTime end, int interval){
        if(number<1||interval<0||start==null||end==null){
            throw new IllegalArgumentException("Cant initialize the next meeting serie: " + this);
        }
        termine = new Termin[number];
        for (int index = 0; index < number; index++) {
            termine[index] = new Termin(name, start.plusDays((long) interval *index), end.plusDays((long) interval *index));
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name==null||name.isEmpty()){
            return;
        }
        this.name = name;
        for (int i = 0; i < termine.length; i++) {
            termine[i].setName(name);
        }
    }
    public Termin getTermin(int index){
        return termine[index];
    }

    public int getNumber(){
        return termine.length;
    }

    public Termin[] getTermine() {
        return termine;
    }

    @Override
    public String toString() {
        if(termine==null){return "";}
        StringBuilder stringBuilder = new StringBuilder();
        for (Termin termin : termine){
            stringBuilder.append(termin.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
