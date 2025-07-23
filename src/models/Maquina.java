package models;

import java.util.*;

public class Maquina {
private String nombre;
private String ip;
private List codigos;
private int subred;
private int riesgo;

public Maquina(String nombre, String ip, List codigos) {
this.nombre = nombre;
this.ip = ip;
this.codigos = codigos;
this.subred = calcularSubred();
this.riesgo = calcularRiesgo();
}

private int calcularSubred() {
String[] octetos = ip.split("\\.");
return Integer.parseInt(octetos[2]); 
}

private int calcularRiesgo() {
int suma = 0;
for (int codigo : codigos) {
if (codigo % 3 == 0) {
suma += codigo;
}
}
String nombreSinEspacios = nombre.replace(" ", "");
Set unicos = new HashSet<>();
for (char c : nombreSinEspacios.toCharArray()) {
unicos.add(c);
}
return suma * unicos.size();
}

public String getNombre() { return nombre; }
public String getIp() { return ip; }
public List getCodigos() { return codigos; }
public int getSubred() { return subred; }
public int getRiesgo() { return riesgo; }

@Override
public String toString() {
return nombre + " - " + ip + " (subred: " + subred + ", riesgo: " + riesgo + ")";
}

@Override
public boolean equals(Object obj) {
if (this == obj) return true;
if (!(obj instanceof Maquina)) return false;
Maquina other = (Maquina) obj;
return this.nombre.equals(other.nombre) && this.subred == other.subred;
}

@Override
public int hashCode() {
return Objects.hash(nombre, subred);
}
}


