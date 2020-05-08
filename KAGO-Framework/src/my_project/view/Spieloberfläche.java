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
        int tiefe = 1;
        double x = 400;
        while(ebenenListe.hasAccess()){
            x = 500 / tiefe;
            for(int i = 0; i< ebenenListe.getContent().length; i++){
                x = x*(i+1);
                if(ebenenListe.getContent() != null && ebenenListe.getContent()[i] != null) drawTool.drawText(x, 250+20*tiefe, ""+ebenenListe.getContent()[i].getKontonummer());
            }
            tiefe++;
            ebenenListe.next();
        }

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
        System.out.println("Tiefe: " + baumTiefe);
        erstelleArrays();
        ebenenListe.toFirst();
        gibEineBaumebeneAus(tree, 0, 0, 1);
        System.out.println("Arrayinhalt" + ebenenListe.getContent()[0].getName());
        for(int i = 2; i <= baumTiefe; i++){
            ebenenListe.next();
            gibEineBaumebeneAus(tree, 0, (int)(Math.pow(2,i-1)), i);
        }
    }

    private void ermittleMaxTiefe(BinarySearchTree<BankKunde> tree){
        if(tree.isEmpty()) return;
        ermittleMaxTiefe(tree, 1);
    }

    private void ermittleMaxTiefe(BinarySearchTree<BankKunde> tree, int tiefe){
        if(!tree.isEmpty()) {
            System.out.println("Hallo");
            if (tiefe > baumTiefe) baumTiefe = tiefe;
            System.out.println(""+tree.getContent().getName());
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
            System.out.println("Neuer Kunde:"+ tree.getContent().getName());
            ebenenListe.getContent()[a] = tree.getContent();
        }else{
            gibEineBaumebeneAus(tree.getLeftTree(), a, b/2, ebene--);
            gibEineBaumebeneAus(tree.getRightTree(), b/2, b, ebene--);
        }
    }

    public int getBaumTiefe() {
        return baumTiefe;
    }
}
