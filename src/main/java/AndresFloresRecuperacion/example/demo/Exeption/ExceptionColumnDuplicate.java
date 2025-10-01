package AndresFloresRecuperacion.example.demo.Exeption;

public class ExceptionColumnDuplicate extends RuntimeException {
    public ExceptionColumnDuplicate(String message) {
        super(message);
    }

    public String getColumDuplicate() {
        return null;
    }
}
