package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Vector;

/**
 * Décrivez votre classe PileVector ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Pile3 implements PileI,Cloneable {

    private Vector<Object> v;
    
    private int ptr;
    
    public Pile3() {
        this(0);
    }

    public Pile3(int taille) {
        
        v = taille>0?new Vector<Object>(taille):new Vector<Object>(CAPACITE_PAR_DEFAUT);
        ptr=0;
    }

    public void empiler(Object o) throws PilePleineException {
        if(ptr==v.capacity())
            throw new PilePleineException();
        v.add(ptr++, o);
    }

    public Object depiler() throws PileVideException {
        if(ptr==0)  //on ne peut pas utiliserv.isEmpty() car on utilise un
                    //index de contenu du pile et on ne depile PAS vraiment 
                    //les contenu du Vector
            throw new PileVideException();
        return v.get(--ptr);
    }

    public Object sommet() throws PileVideException {
        if(ptr==0)
            throw new PileVideException();
        return v.get(ptr-1);
    }

    public int taille() {
        return ptr;
    }

    public int capacite() {
        return v.capacity();
    }

    public boolean estVide() {
        return ptr==0;
    }

    public boolean estPleine() {
        return ptr==v.capacity();
    }

    public String toString() {
        String s = "[";
         
        for(int i=ptr-1;i>=0;i--){
            s+=v.get(i).toString();
            if (i>0)
                s+=", ";
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
                PileI pile1temp = (Pile3)clone();
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
    
    //implementing deep cloning:
    public Object clone() throws CloneNotSupportedException{
        Pile3 p = (Pile3) super.clone();
        p.setVector((Vector) v.clone());
        return p;
    }
    private void setVector(Vector v){
        this.v=v;
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
