package imamJmartMR;

interface FileParser
{
    public boolean read(String Content);
    default Object write(){
        return null;
    }
    static Object newInstance(String Content){
        return null;
    }
}
