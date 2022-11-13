package clients;

public class Generator {
    public static Courier getDefault(){
        return new Courier("pos", "positive123", "Nas");
    }
    public static Courier getWithPasswordsOnly(){
        return new Courier(null, "positive123", null);
    }
    public static Courier getWithLoginOnly(){
        return new Courier("pos", null, null);
    }
    public static Courier getWithFirstNameOnly(){
        return new Courier(null, null, "Nas");
    }
    public static Courier getWithoutCredentials(){
        return new Courier(null, null, null);
    }

    public static Credentials getLoginWithoutPassword(){
        return new Credentials("pos",null);
    }
    public static Credentials getLoginWithoutLogin(){
        return new Credentials(null,"positive123");
    }
    public static Credentials getLoginWithoutParams(){
        return new Credentials(null,null);
    }
    public static Credentials getLoginWithoutIncorrectParams(){
        return new Credentials("null","null");
    }
    public static Credentials getLoginWithIncorrectPassword(){
        return new Credentials("pos","null");
    }
    public static Credentials getLoginWithIncorrectLogin(){
        return new Credentials("null","positive123");
    }
    public static Credentials getDefaultCredentials(){
        return new Credentials("pos", "positive123");
    }
}
