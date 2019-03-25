package question1;

/**
 * Décrivez votre classe PilePleineException ici.
 * 
 * @Antonio Semaan (votre nom)
 * @25-3-2019 (un numéro de version ou une date)
 */
public class PileVideException extends Exception {
    public PileVideException(String s){
        super(s);
    }
    public PileVideException(){
        this("Pile est Vide");
    }
}
