package imamJmartMR;

public class Account extends Recognizable implements FileParser
{
    public String name, email, password;
    
    public Account(int id, String name, String email, String password){
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    @Override
    public boolean read(String Content){
        return false;
    }
    
    public String toString(){
        return "name: Ramadhan\nemail: ramadhanganteng@gmail.com\npassword: gu3G4ntEnG";
    }
}
