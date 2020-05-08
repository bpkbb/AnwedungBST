package my_project.view;

import KAGO_framework.model.abitur.datenstrukturen.BinarySearchTree;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.BankKunde;

public class Spieloberfläche {

    private List<BankKunde[]> ebenenListe;
    private int baumTiefe;





    public void aktualisiereDarstellung(BinarySearchTree<BankKunde> tree){
        ebenenListe.toFirst();
        while(!ebenenListe.isEmpty()){
            ebenenListe.remove();
        }
        baumTiefe = 0;
        ermittleMaxTiefe(tree);
        erstelleArrays();
        ebenenListe.toFirst();

        for(int i = 0; i < baumTiefe; i++){

        }
    }

    private void ermittleMaxTiefe(BinarySearchTree<BankKunde> tree){
        if(!tree.isEmpty()) return;
        ermittleMaxTiefe(tree, 1);
    }

    private void ermittleMaxTiefe(BinarySearchTree<BankKunde> tree, int tiefe){
        if(tiefe > baumTiefe) baumTiefe = tiefe;
        ermittleMaxTiefe(tree.getLeftTree(), tiefe++);
        ermittleMaxTiefe(tree.getRightTree(), tiefe++);
    }

    private void erstelleArrays(){
        for(int i = 1; i <= baumTiefe; i++){
            ebenenListe.append(new BankKunde[(int)(Math.pow(2,i))]);
        }
    }

    public int getBaumTiefe() {
        return baumTiefe;
    }
}
