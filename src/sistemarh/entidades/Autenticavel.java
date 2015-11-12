/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.entidades;

/**
 *
 * @author Alisson
 */
interface Autenticavel {
    public boolean autentica(String nomeSistema, String usuario, String senha);
}
