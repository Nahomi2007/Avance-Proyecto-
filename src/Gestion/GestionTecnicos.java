package Gestion;

import Clases.Tecnico;
import java.util.ArrayList;

public class GestionTecnicos {

    private ArrayList<Tecnico> tecnicos;

    public GestionTecnicos() {
        tecnicos = new ArrayList<>();
    }

    public boolean agregarTecnico(Tecnico tecnico) {

        for (Tecnico t : tecnicos) {
            if (t.getCedula().equals(tecnico.getCedula())) {
                return false;
            }
        }

        tecnicos.add(tecnico);
        return true;
    }

    public ArrayList<Tecnico> getTecnicos() {
        return tecnicos;
    }
}