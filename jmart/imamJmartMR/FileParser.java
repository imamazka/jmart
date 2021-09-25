package imamJmartMR;


interface FileParser
{
    public boolean read(String Content);
    default Object write(){
        return null;
    }
    default Object newInstance(String Content){
        return null;
    }
}
