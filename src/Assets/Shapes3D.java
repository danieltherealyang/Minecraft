package Assets;

public class Shapes3D {
    public float[] getCube(int x, int y, int z, int len) {
        float offset = (float) len / 2f;
        //   .5------8
        // .' |    .'|
        //1------4'  |
        //|   |  |   |
        //|  ,6--|---7
        //|.'    | .'
        //2------3'
        return new float[] {
                (float) x - offset, (float) y + offset, (float) z + offset, //1
                (float) x - offset, (float) y - offset, (float) z + offset, //2
                (float) x + offset, (float) y - offset, (float) z + offset, //3
                (float) x + offset, (float) y + offset, (float) z + offset, //4
                (float) x - offset, (float) y + offset, (float) z - offset, //5
                (float) x - offset, (float) y - offset, (float) z - offset, //6
                (float) x + offset, (float) y - offset, (float) z - offset, //7
                (float) x + offset, (float) y + offset, (float) z - offset  //8
        };
    }
}
