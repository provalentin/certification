public class Trash {

    public static int seed = 3;
    public static int result = 0;
    public static int tm[][] = new int[1000][1000];
    public static int tc[] = new int[3];

    public static int r() {
        seed = seed * 214013 + 2531011;
        return (seed >> 16) & 0x7FFF;
    }

    public static void buildMap() {
        //fill with zeros
        for(int i=0;i<1000;i++)
            for(int j=0;j<1000;j++)
                tm[i][j] = 0;
        //generate trash
        for(int c = 0; c < 10000;) {
            int x = r() % 1000;
            int y = r() % 1000;

            if(tm[y][x] == 0) {
                tm[y][x] = -1;
                c++;
            }
        }
        //put trash can
        for(int c = 1; c<=3; c++) {
            int x = r() % 1000;
            int y = r() % 1000;

            if(tm[y][x] == 0) {
                tm[y][x] = c;
                c++;
            }
        }
        //init counter for each trash can
        tc[0] = tc[1] = tc[2] = 0;
    }

    public static void main(String args[]) {
       System.out.println("run test");
       buildMap();
    }
}
