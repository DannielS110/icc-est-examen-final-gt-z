package controllers;

import models.Maquina;
import java.util.*;

public class MaquinaController {

public Stack filtrarPorSubred(List maquinas, int umbral) {
Stack pila = new Stack<>();
for (Maquina m : maquinas) {
if (m.getSubred() < umbral) {
pila.push(m);
}
}
return pila;
}

public Set ordenarPorSubred(Stack pila) {
Set set = new TreeSet<>(Comparator
.comparingInt(Maquina::getSubred)
.thenComparing(Maquina::getNombre));
set.addAll(pila);
return set;
}

public Map> agruparPorRiesgo(List maquinas) {
Map> mapa = new TreeMap<>();
for (Maquina m : maquinas) {
int riesgo = m.getRiesgo();
mapa.putIfAbsent(riesgo, new LinkedList<>());
mapa.get(riesgo).add(m);
}
return mapa;
}

public Stack explotarGrupo(Map> mapa) {
int mayorTam = -1;
int mayorRiesgo = -1;
Queue grupoObjetivo = null;

for (Map.Entry> entry : mapa.entrySet()) {
int riesgo = entry.getKey();
Queue cola = entry.getValue();
if (cola.size() > mayorTam || (cola.size() == mayorTam && riesgo > mayorRiesgo)) {
mayorTam = cola.size();
mayorRiesgo = riesgo;
grupoObjetivo = cola;
}
}

Stack pila = new Stack<>();
if (grupoObjetivo != null) {
for (Maquina m : grupoObjetivo) {
pila.push(m);
}
}
return pila;
}
}



 