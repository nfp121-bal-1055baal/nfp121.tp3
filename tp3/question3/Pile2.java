package question3;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile2<T> implements PileI<T>{
    /** par délégation : utilisation de la class Stack */
    private Stack<T> stk;
    /** la capacité de la pile */
    private int capacite;
    private int taille;

    /** Création d'une pile.
     * @param taille la "taille maximale" de la pile, doit être > 0
     */
    public Pile2(int taille){
        capacite = taille>=0?taille:CAPACITE_PAR_DEFAUT;
        stk = new Stack<T>();
        taille=0;
    }

    public Pile2(){
        this(CAPACITE_PAR_DEFAUT);
    }

    public void empiler(T o) throws PilePleineException{
        if(taille==capacite)
            throw new PilePleineException();
        stk.push(o);
        taille++;
    }

    public T depiler() throws PileVideException{
        if(stk.isEmpty())
            throw new PileVideException();
        taille--;
        return stk.pop();
    }

    public T sommet() throws PileVideException{
        if(stk.isEmpty())
            throw new PileVideException();
        return stk.peek();
    }

    // recopier ici toutes les autres méthodes
    // qui ne sont pas modifiées en fonction
    // du type des éléments de la pile
    public boolean estVide() {
        return taille==0;
    }

    /**
     * Effectue un test de l'etat de la pile.
     * 
     * @return vrai si la pile est pleine, faux autrement
     */
    public boolean estPleine() {
        return taille==capacite;
    }

    /**
     * Retourne une representation en String d'une pile, contenant la
     * representation en String de chaque element.
     * 
     * @return une representation en String d'une pile
     */
    public String toString() {
        String s = "[";
        if(!estVide()){
            try{
                Pile2 temp = (Pile2)this.clone();
                int temptaille=temp.taille();
                System.out.println("IN THE BEGINNING:"+temptaille);
                for(int i=0;i<temptaille;i++){
                    System.out.println("IN FOR:"+temptaille+"   "+i+"   "+temp.taille());
                    s+=temp.depiler().toString();
                    if (!temp.estVide())
                        s+=", ";
                }
            }
            catch(CloneNotSupportedException e){
                e.printStackTrace();
            }
            catch(PileVideException e){
                e.printStackTrace();
            }
        }
        
        return s + "]";
    }

    public boolean equals(Object o) {
        if(o instanceof Pile2){
            Pile2 p = (Pile2) o;
            if(taille()!=p.taille()||capacite()!=p.capacite())
                return false;
            try{
                if(taille()==0)
                    return true;
                Pile2 pile1temp = (Pile2)clone();
                Pile2 pile2temp = (Pile2)p.clone();
                for(int i=0;i<taille();i++){
                    
                    if(!pile1temp.depiler().equals(pile2temp.depiler())){
                        return false;
                    }
                }
                return true;
            }
            catch(CloneNotSupportedException e){
                e.printStackTrace();
            }
            catch(PileVideException e){
                e.printStackTrace();
            }
            
        }
        return false;
    }

    // fonction fournie
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * Retourne le nombre d'element d'une pile.
     * 
     * @return le nombre d'element
     */
    public int taille() {
        return taille;
    }

    /**
     * Retourne la capacite de cette pile.
     * 
     * @return le nombre d'element
     */
    public int capacite() {
        return capacite;
    }
    
    //implementing deep clone:
    public Object clone() throws CloneNotSupportedException{
        Pile2 p = (Pile2) super.clone();
        p.setstk((Stack)stk.clone());
        return p;
    }
    //methode essentielle pour deep cloning
    private void setstk(Stack s){
        stk=s;
    }
    
} // Pile2