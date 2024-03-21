import java.time.LocalDateTime;

public class Terminserie {
    private String name;
    private Termin[] termine;
    public void init(String name, int number, LocalDateTime start, LocalDateTime end, int interval){
        if(number<1||interval<0||start==null||end==null){
            throw new IllegalArgumentException("Cant initialize the next meeting serie: " + this);
        }
        termine = new Termin[number];
        for (int index = 0; index < number; index++) {
            termine[index] = new Termin();
            termine[index].init(name, start.plusDays(interval*index), end.plusDays(interval*index));
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Termin getTermin(int index){
        return termine[index];
    }
    public int getAnzahl(){
        return termine.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Termin termin : termine){
            stringBuilder.append(termin.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
