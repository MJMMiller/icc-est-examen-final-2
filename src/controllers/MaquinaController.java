package controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import models.Maquina;

public class MaquinaController {

    public static Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> pila = new Stack<>();
        for (Maquina m : maquinas) {
            if (m.getSubred() > umbral) {
                pila.push(m);
            }
        }
        return pila;
    }

    public static TreeSet<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
        Comparator<Maquina> comp = Comparator
            .comparingInt(Maquina::getSubred).reversed()
            .thenComparing(Maquina::getNombre);

        TreeSet<Maquina> treeSet = new TreeSet<>(comp);
        treeSet.addAll(pila);
        return treeSet;
    }

    public static Map<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
        Map<Integer, Queue<Maquina>> mapa = new TreeMap<>();
        for (Maquina m : maquinas) {
            int riesgo = m.getRiesgo();
            mapa.computeIfAbsent(riesgo, k -> new LinkedList<>()).add(m);
        }
        return mapa;
    }

    public static Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa){
        int max = 0;
        int riesgp = 0;

        Queue<Maquina> grupo = mapa.get(riesgp);
        Stack<Maquina> pila = new Stack<>();

        for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
            int cantidad = entry.getValue().size();
            int riesgo = entry.getKey();

            if (cantidad > max || (cantidad == max && riesgo > riesgp)) {
                max = cantidad;
                riesgp = riesgo;
            }
        }

        for (Maquina m : grupo) {
            pila.push(m);}

        Collections.reverse(pila);

        return pila;
    }
    
}
