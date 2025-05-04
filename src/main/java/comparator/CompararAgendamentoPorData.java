

package comparator;

import com.mycompany.oficina.Agendamento;
import java.util.Comparator;

/**
 * Comparador para ordenar objetos Agendamento com base na data e hora.
 * Agendamentos mais antigos vêm primeiro.
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */

public class CompararAgendamentoPorData implements Comparator<Agendamento> {

    @Override
    public int compare(Agendamento a1, Agendamento a2) {
        return a1.getDataHora().compareTo(a2.getDataHora());
    }
}

    

