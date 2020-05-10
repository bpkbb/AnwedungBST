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
        ebenenListe.toFirst();
        int breite = 1000;
        double y = 200;
        int ebene = 0;
        while(ebenenListe.hasAccess()){
            y+= 50;
            breite = breite/2;
            //System.out.println("ebene" + ebene);
            for(int i = 0; i< ebenenListe.getContent().length; i++){
                if(ebenenListe.getContent() != null && ebenenListe.getContent()[i] != null){
                    //System.out.print(ebenenListe.getContent()[i].getKontonummer()+", ");
                    //drawTool.drawText(breite*i+breite, y, ""+ebenenListe.getContent()[i].getKontonummer());
                    //drawTool.drawRectangle(breite*i+breite, y, 50, 20);
                }else{
                   // System.out.print("null, ");
                }
            }
            ebene ++;
            ebenenListe.next();
        }
        ebenenListe.toFirst();
        if(ebenenListe.getContent() != null && ebenenListe.getContent()[0] != null) {
            drawTool.drawText(400, 200, "" + ebenenListe.getContent()[0].getKontonummer());
        }

        ebenenListe.next();
        if(ebenenListe.getContent() != null && ebenenListe.getContent()[0] != null) drawTool.drawText(200, 250, ""+ebenenListe.getContent()[0].getKontonummer());
        if(ebenenListe.getContent() != null && ebenenListe.getContent()[1] != null)drawTool.drawText(600, 250, ""+ebenenListe.getContent()[1].getKontonummer());

        ebenenListe.next();
        if(ebenenListe.getContent() != null && ebenenListe.getContent()[0] != null)drawTool.drawText(100, 300, ""+ebenenListe.getContent()[0].getKontonummer());
        if(ebenenListe.getContent() != null && ebenenListe.getContent()[1] != null)drawTool.drawText(300, 300, ""+ebenenListe.getContent()[1].getKontonummer());
        if(ebenenListe.getContent() != null && ebenenListe.getContent()[2] != null)drawTool.drawText(500, 300, ""+ebenenListe.getContent()[2].getKontonummer());
        if(ebenenListe.getContent() != null && ebenenListe.getContent()[3] != null)drawTool.drawText(700, 300, ""+ebenenListe.getContent()[3].getKontonummer());

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

        /*ebenenListe.toFirst();
        int ebene = 0;
        while(ebenenListe.hasAccess()){
            y+= 50;
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
            System.out.println("links"+a+b/2);
            int tmp = b/2+1;
            System.out.println("rechts"+tmp+b);
            gibEineBaumebeneAus(tree.getLeftTree(), a, (a+b)/2, ebene-1);
            gibEineBaumebeneAus(tree.getRightTree(), (a+b)/2+1, b, ebene-1);
        }
    }

    public int getBaumTiefe() {
        return baumTiefe;
    }
}
