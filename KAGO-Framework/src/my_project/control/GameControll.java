package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.BinarySearchTree;
import my_project.model.BankKunde;
import my_project.view.Spieloberfläche;

public class GameControll {
    private BinarySearchTree<BankKunde> tree;
    private BankKunde neuerKunde;
    private BankKunde randomKunde;
    private Spieloberfläche spieloberfläche;
    private ViewController vC;

    public GameControll(ViewController viewControll){
        vC = viewControll;
        spieloberfläche = new Spieloberfläche(this);
        vC.draw(spieloberfläche);
        vC.register(spieloberfläche);
        neuerKunde = new BankKunde();
        tree = new BinarySearchTree<>();
    }



    public void erstelleNeuenKunden(){
        tree.insert(neuerKunde);
        neuerKunde = new BankKunde();
        ueberpruefeKN(neuerKunde);
        spieloberfläche.aktualisiereDarstellung(tree);
        waehleRandomKunde(tree);
    }

    public void waehleRandomKunde(BinarySearchTree<BankKunde> tree){
        if (!tree.isEmpty()){
            BinarySearchTree<BankKunde> tmp = tree;
            randomKunde = tmp.getContent();
            if (Math.random()>0.3){
                if(Math.random()>0.5){
                    if (!tree.getLeftTree().isEmpty()){
                        tmp = tree.getLeftTree();
                        randomKunde = tmp.getContent();
                        waehleRandomKunde(tmp);
                    }else{
                        return;
                    }
                }else{
                    if (!tree.getRightTree().isEmpty()){
                        tmp = tree.getRightTree();
                        randomKunde = tmp.getContent();
                        waehleRandomKunde(tmp);
                    }else{
                        return;
                    }
                }
            }
        }
    }

    public void ueberpruefeKN(BankKunde bK){
        while (tree.search(bK) != null){
            bK.setzeKontonummer();
        }
    }

    public void entferneEs(){
        if (!tree.isEmpty()) {
            tree.remove(randomKunde);
            waehleRandomKunde(tree);
            spieloberfläche.aktualisiereDarstellung(tree);
        }
    }

    public BankKunde getNeuerKunde() {
        return neuerKunde;
    }

    public BankKunde getRandomKunde() {
        return randomKunde;
    }

    public BinarySearchTree<BankKunde> getTree() {
        return tree;
    }
}
