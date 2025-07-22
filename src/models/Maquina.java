package models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Maquina {

    private String nombre;
    private String ip;
    private List<Integer> codigos;
    private int subred;
    private int riesgo;

    public Maquina(String nombre, String ip, List<Integer> codigos){
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
        this.subred = calcularSubred();
        this.riesgo = calcularRiesgo();
    }

    private int calcularSubred() {
        String[] cadenaIP = this.ip.split("\\.");
        return Integer.parseInt(cadenaIP[2]);
    }

    private int calcularRiesgo() {
        int i = 0;
        for (Iterator<Integer> iterator = this.codigos.iterator(); iterator.hasNext(); ) {
            int j = ((Integer) iterator.next()).intValue();
            if (j % 5 == 0)
                i += j;
        }
        HashSet<Character> hash = new HashSet();
        for (char c : this.nombre.toCharArray()) {
            if (c != ' ')
                hash.add(Character.valueOf(c));
        }
        return i * hash.size();
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getIp() {return ip;}
    public void setIp(String ip) {this.ip = ip;}
    public List<Integer> getCodigos() {return codigos;}
    public void setCodigos(List<Integer> codigos) {this.codigos = codigos;}
    public int getSubred() {return subred;}
    public void setSubred(int subred) {this.subred = subred;}
    public int getRiesgo() {return riesgo;}
    public void setRiesgo(int riesgo) {this.riesgo = riesgo;}

    public String toString() {
        return this.nombre + " - Subred: " + calcularSubred() + " - Riesgo: " + calcularRiesgo();
    }

    public boolean equals(Object paramObject) {
        if (this == paramObject)
            return true;
        if (!(paramObject instanceof Maquina))
            return false;
        Maquina maquina = (Maquina) paramObject;
        return (this.subred == maquina.subred && Objects.equals(this.nombre, maquina.nombre));
    }

    public int hashCode() {
        return Objects.hash(new Object[] { this.nombre, Integer.valueOf(this.subred) });
    }
}
