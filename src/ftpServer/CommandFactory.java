package ftpServer;
public class CommandFactory {
    public static Command createCommand(String type) {

        type = type.toUpperCase();
        switch (type) {
            case "XMKD":
                return new MkdCommand();
            case "XRMD":
                return new RmdCommand();
            case "USER":
                return new UserCommand();
            case "XPWD":
                return new PwdCommand();
            case "PASS":
                return new PassComand();

            case "LIST":
                return new ListComand();

            case "PORT":
                return new PortCommand();

            case "QUIT":
                return new QuitCommand();

            case "RETR":
                return new RetrCommand();

            case "CWD":
                return new CwdCommand();

            case "STOR":
                return new StoreCommand();
            case "APPE":
                return new AppeCommand();
            default:
                return null;
        }
    }
}
