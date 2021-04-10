package Vectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Form {
    private List<Vector3D> vector3D;

    public Form(){
        vector3D = new ArrayList<>();
    }

    public Form(Vector3D... positions){
        vector3D = Arrays.asList(positions);
    }

    public List<Vector3D> getVector3D() {
        return vector3D;
    }

    public void setVector3D(List<Vector3D> vector3D) {
        this.vector3D = vector3D;
    }

    @Override
    public String toString() {
        return "Vectors.Form{" +
                "vector3D=" + vector3D +
                '}';
    }
}
