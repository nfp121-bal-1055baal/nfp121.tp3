package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile4 implements PileI, Cloneable {
    /** la liste des Maillons/Elements */
    private Maillon stk;
    /** la capacité de la pile */
    private int capacite;
    /** le nombre */
    private int nombre;

    /**
     * Classe interne "statique" contenant chaque élément de la chaine c'est une
     * proposition, vous pouvez l'ignorer !
     */
    private static class Maillon implements Cloneable {
        private Object element;
        private Maillon suivant;

        public Maillon(Object element, Maillon suivant) {
            this.element = element;
            this.suivant = suivant;
        }
        
        public void setSuivant(Maillon m){
            this.suivant=m;
        }
        
        public Maillon suivant() {
            return this.suivant;
        }

        public Object element() {
            return this.element;
        }

        public Object clone() throws CloneNotSupportedException {
            Maillon m = (Maillon) super.clone();
            m.element = element;
            return m;
        }
    }

    /**
     * Création d'une pile.
     * 
     * @param taille
     *            la taille de la pile, la taille doit être > 0
     */
    public Pile4(int taille) {
        if (taille <= 0)
            taille = CAPACITE_PAR_DEFAUT;
        this.stk = null;
        this.capacite = taille;
        this.nombre=0;
    }

    public Pile4() {
        this(PileI.CAPACITE_PAR_DEFAUT);
    }

    public void empiler(Object o) throws PilePleineException {
        if (estPleine())
            throw new PilePleineException();
        if(estVide())
            stk=new Maillon(o,null);
        else
            stk=new Maillon(o,stk);
        nombre++;
        
    }

    public Object depiler() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        Maillon m = stk;
        stk=stk.suivant();
        nombre--;
        return m.element();
    }

    public Object sommet() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        return stk.element(); // à compléter
    }

    /**
     * Effectue un test de l'état de la pile.
     * 
     * @return vrai si la pile est vide, faux autrement
     */
    public boolean estVide() {
        return nombre==0; // à compléter
    }

    /**
     * Effectue un test de l'état de la pile.
     * 
     * @return vrai si la pile est pleine, faux autrement
     */
    public boolean estPleine() {
        return nombre==capacite; // à compléter
    }

    /**
     * Retourne une représentation en String d'une pile, contenant la
     * représentation en String de chaque élément.
     * 
     * @return une représentation en String d'une pile
     */
    public String toString() {

        String s = "[";
        if(!estVide()){
            Maillon m = stk;
            while(true){
                s+=m.element().toString();
                m=m.suivant();
                if(m==null)
                    return s + "]";
                s+=", ";
            }
        }
        return s + "]";
    }

    public boolean equals(Object o) {
        if (o instanceof Pile4) {
            Pile4 p = (Pile4) o;
            if(taille()!=p.taille()||capacite()!=p.capacite())
                return false;
            try{
                if(capacite()==0)
                    return true;
                Pile4 pile1temp = (Pile4)clone();
                Pile4 pile2temp = (Pile4) p.clone();
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

    public int capacite() {
        return this.capacite;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public int taille() {
        return nombre;
    }
    
    //implementing deep cloning:
    public Object clone() throws CloneNotSupportedException{
        Pile4 p = (Pile4) super.clone();
        if(!p.estVide())
            p.setLinkedList(stk);
        return p;
    }
    
    //methode essentielle pour le deep cloning
    private void setLinkedList(Maillon m){
        Maillon newM=new Maillon(m.element(),null);
        
        Maillon iteratorM=newM;
        this.stk=newM;
        
        Maillon main = m.suivant();
        
        while(main!=null){
            
            iteratorM.setSuivant(new Maillon(main.element(),null));
            System.out.println(iteratorM.suivant());
            main=main.suivant();
            iteratorM=iteratorM.suivant();
            System.out.println(iteratorM.suivant());
            
            
        }
        
    }
}