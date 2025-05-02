
package comparator;

/**
 * Comparador para ordenar objetos Agendamento com base no nome do cliente.
 * A ordenação é alfabética crescente.
 * 
 * @author Ana Clara
 * @version 1.0
 */
import com.mycompany.oficina.Agendamento;
import java.util.Comparator;

public class CompararAgendamentoPorNomeCliente implements Comparator<Agendamento> {

    @Override
    public int compare(Agendamento a1, Agendamento a2) {
        return a1.getCliente().getNome().compareToIgnoreCase(a2.getCliente().getNome());
    }
}

