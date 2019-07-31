

public class Account {

    public String site;
    public String login;
    public String password;
    public String comment;
    
    public boolean show_pass = false;

    public Account(String sit, String log, String pas, String com) {
        site = sit;
        login = log;
        password = pas;
        comment = com;
    }

    public void revers_show() {
        show_pass = !show_pass;
    }
}
