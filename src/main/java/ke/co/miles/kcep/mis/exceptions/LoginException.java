/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.exceptions;

/**
 *
 * @author siech
 */
public class LoginException extends MilesException {

    private static final long serialVersionUID = 1L;

    public LoginException(String code) {
        super(code);
    }

    public LoginException(String code, String prepend, String append) {
        super(code, prepend, append);
    }

}
