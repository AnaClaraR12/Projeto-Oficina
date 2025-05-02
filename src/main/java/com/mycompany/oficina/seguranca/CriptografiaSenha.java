/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.oficina.seguranca;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe responsável por realizar a criptografia de senhas utilizando o algoritmo SHA-256.
 * 
 * <p>Esta classe fornece um método estático que recebe uma senha em formato de {@code String}
 * e retorna sua representação criptografada em hexadecimal.</p>
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class CriptografiaSenha {

    /**
     * Criptografa a senha fornecida utilizando o algoritmo SHA-256.
     * 
     * @param senha a senha em texto plano que será criptografada
     * @return a representação hexadecimal da senha criptografada ou {@code null} em caso de erro
     */
    public static String criptografarSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(senha.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}


