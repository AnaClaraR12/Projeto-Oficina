package com.mycompany.oficina.comparator;

import com.mycompany.oficina.entidade.Cliente;
import java.util.Comparator;

/**
 * Comparador para ordenar objetos Cliente com base no seu nome.
 * A ordenação é alfabética (lexicográfica) padrão para Strings.
 *
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class CompararClientePorNome implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {
        if (c1 == null && c2 == null) {
            return 0;
        }
        if (c1 == null || c1.getNome() == null) {
            return -1; 
        }
        if (c2 == null || c2.getNome() == null) {
            return 1;  // Considera nulos menores
        }
        return c1.getNome().compareTo(c2.getNome());
    }
} 