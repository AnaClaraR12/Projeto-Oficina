// Arquivo: comparator/CompararClientePorCpf.java
package com.mycompany.oficina.comparator;

import com.mycompany.oficina.entidade.Cliente; // Importa sua classe Cliente
import java.util.Comparator;

/**
 * Comparador para ordenar objetos Cliente com base no seu CPF.
 * A ordenação é alfabética (lexicográfica) padrão para Strings.
 *
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class CompararClientePorCPF implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {
        if (c1 == null && c2 == null) {
            return 0;
        }
        if (c1 == null || c1.getCpf() == null) {
            return -1; 
        }
        if (c2 == null || c2.getCpf() == null) {
            return 1;  // Considera nulos menores
        }
        return c1.getCpf().compareTo(c2.getCpf());
    }
}
