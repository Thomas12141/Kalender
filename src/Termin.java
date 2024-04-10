import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Termin {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;

    public Termin(String name, LocalDateTime start, LocalDateTime end){
        if(name==null||start==null||end==null){
            throw new IllegalArgumentException("Cant initialize the next meeting: " + this);
        }
        this.name = name;
        this.start = start;
        this.end = end;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "\tName: " + name + "\n\t\t" + String.format("start date: %s\t\tend date: %s", start.format(dateTimeFormatter), end.format(dateTimeFormatter), dateTimeFormatter);
    }
}
