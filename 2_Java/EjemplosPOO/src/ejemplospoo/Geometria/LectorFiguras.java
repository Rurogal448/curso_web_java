/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplospoo.Geometria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author German
 */
public class LectorFiguras {

    public static void coleccionesFiguras() {
        Figura[] figuras = {
            new Rectangulo(5, 5),
            new Circulo(5, "blanco"),
            new Triangulo("Triangulo: base=5.5, altura=3.3, color=morado")
        };
        for (Figura fig : figuras) {
            System.out.println(fig.toString());
            System.out.println(" - Area: " + fig.calcArea());
        }
        ArrayList<Figura> arrayList = new ArrayList<>();
        arrayList.add(new Rectangulo(4, 3, "verde"));
        arrayList.add(new Circulo(5, "verde"));
        //array.add("Texto cualquiera");
        //array.add(200);

        Figura[] figurasArray = new Figura[10];
        arrayList.toArray(figurasArray);

        for (Figura fig : arrayList) {
            System.out.println(fig.toString());
            System.out.println("- Area: " + fig.calcArea());
        }
        IMostrable[] objMos = {
            new Triangulo(5, 7, null),
            new Cuadrado(6), //new Linea(),
        //new Triangulo(20,5,null)
        };
        for (IMostrable mostrable : objMos) {
            mostrable.mostrar();
        }
        Cuadrado cuadrado = new Cuadrado(5, "negro");
        Rectangulo cuadR = cuadrado;
        FiguraConLados cuadFL = cuadrado;
        Figura cuadF = cuadrado;
        cuadF.setColor("gris");
        IImprimible cuadI = cuadrado;
        cuadI.imprimir();
        IMostrable cuadM = cuadrado;
        cuadM.mostrar();

        arrayList.add(cuadrado);
        arrayList.add(cuadrado);

        Set<Figura> setFig = new HashSet<>();
        setFig.add(new Triangulo(3, 5, null));
        setFig.addAll(arrayList);

        System.out.println(">> ARRAY LIST: ");
        for (Figura fig : arrayList) {
            System.out.println(fig.toString());
        }
        System.out.println(">> SET: ");
        for (Figura fig : setFig) {
            System.out.println(fig.toString());
        }

        Map<String, Figura> dicc = new HashMap<String, Figura>();
        dicc.put("tri 1", new Triangulo(3, 9, null, "cyan"));
        dicc.put("cuad 1", new Cuadrado(3));
        dicc.put("tri 2", new Triangulo(2, 4, null, "cyan"));
        dicc.put("cuad 2", new Cuadrado(2));
        dicc.get("tri 1").toString();
        System.out.println("Buscando tri 1: " + dicc.get("tri 1").toString());
        for (Map.Entry<String, Figura> entrada : dicc.entrySet()) {
            System.out.println("Clave: " + entrada.getKey() + "; Valor" + entrada.getValue());
        }
        Map<Figura, String> mapaFig = new TreeMap<>();
        mapaFig.put(new Triangulo(3, 9, null, "cyan"), "tri 1");
        mapaFig.put(new Cuadrado(2), "cuad 2");
        mapaFig.put(new Cuadrado(7), "cuad 7");
        mapaFig.put(new Cuadrado(5), "cuad 5");
        mapaFig.put(new Circulo(5), "circ 5");
        mapaFig.put(new Circulo(2), "circ 2");

        System.out.println(">>> MAPA ORDENADO: ");
        mapaFig.forEach((Figura clave, String valor) -> {
            System.out.println("CLAVE: " + clave /*toString()*/
                    + "; AREA: " + clave.calcArea()
                    + "; VALOR: " + valor.toString());
        });
        mapaFig.forEach(LectorFiguras::funcMostrarFigValor);
    }

    static void funcMostrarFigValor(Figura clave, String valor) {
        System.out.println("CLAVE: " + clave /*toString()*/
                + "; AREA: " + clave.calcArea()
                + "; VALOR: " + valor.toString());
    }
}
