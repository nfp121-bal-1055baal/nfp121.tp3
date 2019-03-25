package question1;

/**
 * Décrivez votre classe PilePleineException ici.
 * 
 * @Antonio Semaan (votre nom)
 * @25-3-2019 (un numéro de version ou une date)
 */
public class PilePleineException extends Exception {
    public PilePleineException(String s){
        super(s);
    }
    public PilePleineException(){
        this("Pile est Pleine");
    }
}
