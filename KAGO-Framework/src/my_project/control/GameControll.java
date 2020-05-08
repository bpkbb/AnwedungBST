package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.BinarySearchTree;
import my_project.model.BankKunde;
import my_project.view.Spieloberfläche;

public class GameControll {
    private BinarySearchTree<BankKunde> tree;
    private BankKunde neuerKunde;
    private BankKunde randomKunde;
    private Spieloberfläche spieloberfläche;

    public GameControll(){
        spieloberfläche = new Spieloberfläche();
    }



    public void erstelleNeuenKunden(){
        tree.insert(neuerKunde);
        neuerKunde = new BankKunde();
        spieloberfläche.aktualisiereDarstellung(tree);
    }

    public BankKunde waehleRandomKunden(){
        if (!tree.isEmpty()){
            int randomTiefe = (int) Math.random() * spieloberfläche.getBaumTiefe();
            BinarySearchTree<BankKunde> tmp = tree;
            randomKunde = tree.getContent();
            while (randomTiefe != 0){
                if (Math.random() > 0.5){
                    if (tmp.getLeftTree().isEmpty()){
                        return randomKunde;
                    }else {
                        tmp = tmp.getLeftTree();
                        randomKunde = tmp.getLeftTree().getContent();
                    }
                }else{
                    if (tmp.getRightTree().isEmpty()){
                        return randomKunde;
                    }else {
                        tmp = tmp.getRightTree();
                        randomKunde = tmp.getRightTree().getContent();
                    }
                }
                randomTiefe--;
            }
        }
        return randomKunde;
    }

    public BankKunde getNeuerKunde() {
        return neuerKunde;
    }

    public BankKunde getRandomKunde() {
        return randomKunde;
    }
}
