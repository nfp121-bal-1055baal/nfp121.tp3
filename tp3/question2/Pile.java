package question2;

import question1.PilePleineException;
import question1.PileVideException;

/**
 * A remplacer en partie par votre classe Pile de la question 1.
 * 
 * @Antonio Semaan (votre nom)
 * @25-3-2019 (un numéro de version ou une date)
 */
public class Pile implements PileI, Cloneable {

    private Object[] zone;
    private int ptr;

    public Pile(int taille) {
        zone= taille>0?new Object[taille]:new Object[CAPACITE_PAR_DEFAUT];
        ptr=0;
    }

    public Pile() {
        this(CAPACITE_PAR_DEFAUT);
    }

    public void empiler(Object o) throws PilePleineException {
        if(estPleine())
            throw new PilePleineException();
        if(o==null)
            return;
        zone[ptr++]=o;
    }

    public Object depiler() throws PileVideException {
        if(estVide())
            throw new PileVideException();
        return zone[--ptr];
    }

    public Object sommet() throws PileVideException {
        if(estVide())
            throw new PileVideException();
        return zone[ptr-1];
    }

    public int capacite() {
        return zone.length;
    }

    public int taille() {
        return ptr;
    }

    public boolean estVide() {
        return ptr==0;
    }

    public boolean estPleine() {
        return ptr == zone.length;
    }

    public boolean equals(Object o) {
        if(o instanceof PileI){
            PileI p = (PileI) o;
            if(taille()!=p.taille()||capacite()!=p.capacite())
                return false;
            try{
                if(taille()==0)
                    return true;
                PileI pile1temp = (Pile)clone();
                PileI pile2temp = findClassAndClone(o);
                for(int i=0;i<taille();i++){
                    
                    if(!pile1temp.depiler().equals(pile2temp.depiler())){
                        return false;
                    }
                }
                
            }
            catch(CloneNotSupportedException e){
                e.printStackTrace();
            }
            catch(PileVideException e){
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    // fonction fournie
    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        String out = "[";
        for (int i = ptr - 1; i >= 0; i--) {
            out+=zone[i].toString();
            if (i > 0)
                out+=", ";
        }
        out+="]";
        return out;
        
    }
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
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
}