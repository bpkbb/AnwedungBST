package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.BinarySearchTree;
import my_project.model.BankKunde;

public class GameControll {
    private BinarySearchTree<BankKunde> tree;
    private BankKunde neuerKunde;
    private BankKunde randomKunde;





    public void erstelleNeuenKunden(){
        tree.insert(neuerKunde);
        neuerKunde = new BankKunde();

    }
}
