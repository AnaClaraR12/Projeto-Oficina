package com.mycompany.oficina.seguranca;

/**
 * Enumeração que define os tipos de permissões de acesso para usuários da oficina.
 * 
 * - ACESSO_TOTAL: Permissão concedida aos administradores, permitindo acesso total a todas as funcionalidades do sistema.
 * - ACESSO_LIMITADO: Permissão concedida a funcionários comuns, permitindo acesso restrito às funcionalidades relacionadas ao seu cargo.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public enum TipoPermissao {
    ACESSO_TOTAL,  // Permissão para administrador, com acesso irrestrito a todas as funcionalidades
    ACESSO_LIMITADO // Permissão para funcionários comuns, com acesso restrito a determinadas funcionalidades
}

