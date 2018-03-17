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
    //helper method
    public static void move(int y, int x, int d) {
        if(result==1_000_000_000) return;
        result++;
        int ox = x;
        int oy = y;
        switch(d) {
            case 0: y--;break;
            case 1: y++;break;
            case 2: x--;break;
            case 3: x++;break;
        }
        if((x<0)||(ox<0)||(x>=1000)||(ox>=1000)) return;
        if((y<0)||(oy<0)||(y>=1000)||(oy>=1000)) return;
        if((tm[y][x]==-1)||(tm[oy][ox]!=-1)) return;
        if(tm[y][x]==0) {
            tm[oy][ox]=0;
            tm[y][x] = -1;
        } else {
            int trashNo = tm[y][x] - 1;
            if(tc[trashNo]==3500) return;
            tm[oy][ox] = 0;
            tc[trashNo]++;
        }
    }

    //TODO: implement
    public static void test() {

    }

    public static int errorCount() {
        int counter = 0;
        for(int i = 0;i<1000;i++)
            for(int j=0;j<1000;j++)
                if(tm[i][j]==-1)
                    counter++;
        return counter;
    }

    public static void main(String args[]) {
       System.out.println("test setup");
       buildMap();
       System.out.println("test run");
       long startTime = System.currentTimeMillis();
       test();
       long endTime = System.currentTimeMillis();
       System.out.println("errors: " + errorCount());
       System.out.println("test time: " + (endTime - startTime));

    }
}
