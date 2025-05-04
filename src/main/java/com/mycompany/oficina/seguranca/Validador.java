/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina.seguranca;

/**
 * Classe responsável por validar campos como e-mail e senha.
 * 
 * Contém métodos utilitários estáticos que verificam se o e-mail está em um
 * formato válido e se a senha atende ao critério mínimo de segurança.
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class Validador {

    /**
     * Valida se um e-mail está em um formato correto.
     * 
     * @param email o e-mail a ser validado
     * @return true se o e-mail for não nulo e corresponder ao padrão de e-mail;
     *         false caso contrário
     */
    public static boolean validarEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    /**
     * Valida se uma senha atende ao requisito mínimo de comprimento.
     * 
     * @param senha a senha a ser validada
     * @return true se a senha for não nula e tiver pelo menos 6 caracteres;
     *         false caso contrário
     */
    public static boolean validarSenha(String senha) {
        return senha != null && senha.length() >= 6;
    }
}


