package async.capteur.strategie;

import async.capteur.Capteur;

import java.util.ArrayList;

/**
 * Created by naleite on 15/1/7.
 */
public class DiffusionSeq implements AlgoDiffusion {
    private int value;
    private Capteur capteur;
    private ArrayList<Integer> valueCopie; //tableau des valeurs non envoyées

    @Override
    public void configure(int nbAfficheur, int newValue) {

    }

    @Override
    public void execute() {

    }

    @Override
    public void setCapteur(Capteur c) {

    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public String toString(){
        return "Diffustion Sequentielle";
    }
}
