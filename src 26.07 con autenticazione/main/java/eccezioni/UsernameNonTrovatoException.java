package OfficineTreniAuth.eccezioni;

public class UsernameNonTrovatoException extends UtenteException {
	
    public UsernameNonTrovatoException(String message, String username) {
        super(message, username);
    }
}
