import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Kalender kalender1 = new Kalender();
        Kalender kalender2 = new Kalender();
        kalender1.init();
        kalender2.init();
        Termin termin1 = new Termin();
        termin1.init("OOS", LocalDateTime.of(2024, 3, 21, 16, 30), LocalDateTime.of(2024, 3,21, 18, 30));
        Termin termin2 = new Termin();
        termin2.init("BS", LocalDateTime.of(2024, 3, 18, 16, 30), LocalDateTime.of(2024, 3,18, 18, 30));
        Termin termin3 = new Termin();
        termin3.init("KI", LocalDateTime.of(2024, 3, 16, 16, 30), LocalDateTime.of(2024, 3,16, 18, 30));
        Terminserie terminserie1 = new Terminserie();
        terminserie1.init("GITS", 5, LocalDateTime.of(2024, 3, 16, 9, 30),
                LocalDateTime.of(2024, 3,16, 11, 30), 7);
        Terminserie terminserie2 = new Terminserie();
        terminserie2.init("PSE-Praktikum", 5, LocalDateTime.of(2024, 3, 14, 9, 30),
                LocalDateTime.of(2024, 3,16, 11, 30), 14);
        kalender1.addSerie(terminserie1);
        kalender2.addSerie(terminserie2);
        kalender1.addTermin(termin1);
        kalender1.addTermin(termin2);
        kalender1.addTermin(termin3);
        kalender2.addTermin(termin1);
        System.out.println(kalender1.ausgeben());
        System.out.println(kalender2.ausgeben());
    }
}
