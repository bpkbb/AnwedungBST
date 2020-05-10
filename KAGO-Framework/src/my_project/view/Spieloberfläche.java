package my_project.view;

import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.BinarySearchTree;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.view.DrawTool;
import my_project.control.GameControll;
import my_project.model.BankKunde;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Spieloberfläche extends InteractiveGraphicalObject {

    private List<BankKunde[]> ebenenListe;
    private int baumTiefe;
    private GameControll gC;

    public Spieloberfläche(GameControll gameControll){
        gC = gameControll;
        baumTiefe = 0;
        ebenenListe = new List<>();
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_SPACE){
            gC.erstelleNeuenKunden();
        }
        if(key == KeyEvent.VK_ENTER){
            gC.entferneEs();
        }
    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void draw(DrawTool drawTool) {
        double y = 200;
        double breite = 1000;
        ebenenListe.toFirst();
        int i = 0;
        while(ebenenListe.hasAccess()){
            for(int j = 0; j < ebenenListe.getContent().length;j++) {
                drawTool.drawRectangle(breite * j, y + 50 * i, breite, 50);
                if(ebenenListe.getContent() != null && ebenenListe.getContent()[j] != null){
                    drawTool.drawText(breite*j+breite/2, y+50*i+20, ""+ebenenListe.getContent()[j].getName());
                    drawTool.drawText(breite*j+breite/2, y+50*i+30, ""+ebenenListe.getContent()[j].getNachname());
                    drawTool.drawText(breite*j+breite/2, y+50*i+10, ""+ebenenListe.getContent()[j].getKontonummer());
                }
            }
            breite = breite/2;
            i++;
            ebenenListe.next();
        }
        oberenLeisten(drawTool);
    }

    @Override
    public void update(double dt) {

    }

    public void aktualisiereDarstellung(BinarySearchTree<BankKunde> tree){
        ebenenListe.toFirst();
        while(!ebenenListe.isEmpty()){
            ebenenListe.remove();
        }
        baumTiefe = 0;
        ermittleMaxTiefe(tree);
        erstelleArrays();
        ebenenListe.toFirst();
        gibEineBaumebeneAus(tree, 1, 0, 1);
        for(int i = 2; i <= baumTiefe; i++){
            ebenenListe.next();
            gibEineBaumebeneAus(tree, 1, (int)(Math.pow(2,i-1)), i);
        }

       /* ebenenListe.toFirst();
        int ebene = 0;
        while(ebenenListe.hasAccess()){
            System.out.println("ebene " + ebene);
            for(int i = 0; i< ebenenListe.getContent().length; i++){
                if(ebenenListe.getContent() != null && ebenenListe.getContent()[i] != null){
                    System.out.print(ebenenListe.getContent()[i].getKontonummer()+", ");
                }else{
                    System.out.print("null, ");
                }
            }
            ebene ++;
            System.out.println("");
            ebenenListe.next();
    }
    */


}

    private void ermittleMaxTiefe(BinarySearchTree<BankKunde> tree){
        if(tree.isEmpty()) return;
        ermittleMaxTiefe(tree, 1);
    }

    private void ermittleMaxTiefe(BinarySearchTree<BankKunde> tree, int tiefe){
        if(!tree.isEmpty()) {
            if (tiefe > baumTiefe) baumTiefe = tiefe;
            ermittleMaxTiefe(tree.getLeftTree(), tiefe++);
            ermittleMaxTiefe(tree.getRightTree(), tiefe++);
        }
    }

    private void erstelleArrays(){
        for(int i = 1; i <= baumTiefe; i++){

            ebenenListe.append(new BankKunde[(int)(Math.pow(2,i-1))]);
        }
    }

    private void gibEineBaumebeneAus(BinarySearchTree<BankKunde> tree, int a, int b, int ebene){
        if(tree == null || ebene < 0 || tree.isEmpty()) return;
        if(ebene == 1){
                ebenenListe.getContent()[a-1] = tree.getContent();

        }else{
            gibEineBaumebeneAus(tree.getLeftTree(), a, (a+b)/2, ebene-1);
            gibEineBaumebeneAus(tree.getRightTree(), (a+b)/2+1, b, ebene-1);
        }
    }

    public void oberenLeisten(DrawTool drawTool) {
        drawTool.drawText(100, 100, gC.getNeuerKunde().getName() + " " + gC.getNeuerKunde().getNachname());
        drawTool.drawText(100, 120, "KN: " + gC.getNeuerKunde().getKontonummer());
        if (!gC.getTree().isEmpty() && gC.getRandomKunde() != null) {
            drawTool.drawText(700, 100, "Random Kunde: " + gC.getRandomKunde().getName() + " " + gC.getRandomKunde().getNachname() + " " + gC.getRandomKunde().getKontonummer());
        }
    }

    public int getBaumTiefe() {
        return baumTiefe;
    }
}
