package OfficineTreniAuth.eccezioni;

public abstract class UtenteException extends RuntimeException {
    private String username;

    public UtenteException(String message, String username) {
        super(message);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}