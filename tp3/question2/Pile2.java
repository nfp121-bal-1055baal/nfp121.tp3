package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile2 implements PileI,Cloneable {
    /** par delegation : utilisation de la class Stack */
    private Stack<Object> stk;

    /** la capacite de la pile */
    private int capacite;
    private int taille;
    /**
     * Creation d'une pile.
     * 
     * @param taille
     *            la taille de la pile, la taille doit etre > 0
     */
    public Pile2(int capacite) {
        this.capacite=capacite>0?capacite:CAPACITE_PAR_DEFAUT;
        stk=new Stack<Object>();
    }

    // constructeur fourni
    public Pile2() {
        this(CAPACITE_PAR_DEFAUT);
    }

    public void empiler(Object o) throws PilePleineException {
        if(taille==capacite)
            throw new PilePleineException();
        stk.push(o);
        taille++;
    }

    public Object depiler() throws PileVideException {
        if(stk.isEmpty())
            throw new PileVideException();
        taille--;
        return stk.pop();
    }

    public Object sommet() throws PileVideException {
        if(stk.isEmpty())
            throw new PileVideException();
        return stk.peek();
    }

    /**
     * Effectue un test de l'etat de la pile.
     * 
     * @return vrai si la pile est vide, faux autrement
     */
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
        if(o instanceof PileI){
            PileI p = (PileI) o;
            if(taille()!=p.taille()||capacite()!=p.capacite())
                return false;
            try{
                if(taille()==0)
                    return true;
                PileI pile1temp = (Pile2)clone();
                PileI pile2temp = findClassAndClone(o);
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
    private PileI findClassAndClone(Object o) throws CloneNotSupportedException{
        if(o instanceof Pile){
            return (PileI)((Pile)o).clone();
        }else if(o instanceof Pile2){
            return (PileI)((Pile2)o).clone();
        }else if(o instanceof Pile3){
            return (PileI)((Pile3)o).clone();
        }else{
            return (PileI)((Pile4)o).clone();
        }
    }
} // Pile2.java
