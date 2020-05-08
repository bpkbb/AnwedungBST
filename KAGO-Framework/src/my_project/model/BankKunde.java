package my_project.model;

public class BankKunde {

    private String name;
    private String nachname;
    private int kontonummer;
    private String[] namen;
    private String[] nachnamen;

    public BankKunde(){
        namen = new String[10];
        nachnamen = new String[10];
        fuelleNamen();
        setzeNamen();
        setzeKontonummer();
    }

    private void fuelleNamen(){
        namen[0] = "Gertrud";
        namen[1] = "Dörte";
        namen[2] = "Ulf";
        namen[3] = "Pia";
        namen[4] = "Markus";
        namen[5] = "Frank";
        namen[6] = "Lukas";
        namen[7] = "Toni";
        namen[8] = "Mia";
        namen[9] = "Beatrix";
        nachnamen[0] = "Müller";
        nachnamen[1] = "Schmidt";
        nachnamen[2] = "Kneblewski";
        nachnamen[3] = "Bieder";
        nachnamen[4] = "Rambo";
        nachnamen[5] = "Haller";
        nachnamen[6] = "Meisner";
        nachnamen[7] = "Mustermann";
        nachnamen[8] = "Miller";
        nachnamen[9] = "Smith";
    }

    private void setzeNamen(){
        name = namen[(int) (Math.random()*10)];
        nachname = nachnamen[(int) (Math.random()*10)];
    }

    public void setzeKontonummer(){
        kontonummer = (int) (Math.random()*10000+10000);
    }

    public String getName() {
        return name;
    }

    public String getNachname() {
        return nachname;
    }

    public int getKontonummer() {
        return kontonummer;
    }
}
