package OfficineTreniAuth.eccezioni;

public class PasswordErrataException extends UtenteException {
    private String password;

    public PasswordErrataException(String message, String username, String password) {
        super(message, username);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
