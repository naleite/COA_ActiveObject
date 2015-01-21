package async.capteur.strategie;

import async.capteur.Capteur;

/**
 * Created by naleite on 15/1/7.
 */
public class DiffustionEpoque implements AlgoDiffusion{
    @Override
    public void configure() {

    }

    @Override
    public void execute() {

        System.out.println("Algo epoque");
    }

    @Override
    public void setCapteur(Capteur c) {

    }

    @Override
    public String toString(){
        return "Gestion par epoque";
    }
}
