public class Paleta {
    private final short[][] cor = new short[255][3];

    public Paleta(){
        createPalet();
    }

    private void createPalet(){
        for(int i = 0; i < 255;i++){
            cor[i][0] = (short)i;
            cor[i][1] = (short)i;
            cor[i][2] = (short)i;
        }
    }

    public short[][] getPalet() {
        return cor;
    }

    public short[] getColor(int r, int g, int b){
        return new short[]{valid(r), valid(g), valid(b)};
    }

    private short valid(int color){
        if( color <= 0) return 1;
        if(color >= 255) return 254;
        return (short) (color - 1);
    }
}
