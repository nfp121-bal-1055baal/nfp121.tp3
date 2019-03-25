package question1;

public class UneUtilisation {

    public static void main(String[] args) throws Exception {
        Pile p1 = new Pile(6);
        Pile p2 = new Pile(10);
        // p1 est ici une pile de polygones r√©guliers PolygoneRegulier.java

        p1.empiler(new PolygoneRegulier(5, 100));
        p1.empiler("polygone");
        p1.empiler(new Integer(100));
        System.out.println(" la pile p1 = " + p1); // Quel est le r√©sultat ?
        //resultat: la pile p1 = [100, polygone, <5,100>]

        p2.empiler(new Integer(1000));
        p1.empiler(p2);
        System.out.println(" la p1 = " + p1); // Quel est le r√©sultat ?
        //resultat: la p1 = [[1000], 100, polygone, <5,100>]
        
        try {
            p1.empiler(new PolygoneRegulier(4,100));
            // ....
            String s = (String) p1.depiler(); // v√©rifiez qu'une exception se
                                                // produit
            //ClassCastException levÈe cela est due a l'existence d'objet de type 
            //qui n'est pas String.
            //dans ce cas il faut fair cast ‡ Object 
            //dans le cas gÈnÈrique <E> on doit faire le cast au type E
        } catch (Exception e) {
            e.printStackTrace();
        } // catch
    } // main()
} // UneUtilisation
