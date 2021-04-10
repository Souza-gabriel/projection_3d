package ObjectReader;

import Vectors.Vector3D;
import Vectors.Form;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObjectLoarder {
    private final List<Vector3D> vectores = new ArrayList<>();
    private final List<VectorOrder> orders = new ArrayList<>();

    public ObjectLoarder(){

    }

    public void parseObjectFile(String src){
        String aux;
        VectorOrder vectorOrderAux;
        try {
            Scanner fileReader = new Scanner(new File(src));
            while (fileReader.hasNext()){
                aux = fileReader.nextLine();
                vectorOrderAux = new VectorOrder();
                try{
                    if(aux.startsWith("v ")){
                        aux = aux.substring(3);
                        vectores.add(new Vector3D().parseString(aux.split(" ")));
                    }else if(aux.startsWith("f ")){
                        aux = aux.substring(2);
                        for (String fx : aux.split(" ")){
                            vectorOrderAux.add(fx.split("/")[0]);
                            vectorOrderAux.isAdded = true;
                        }
                    }
                    if(vectorOrderAux.isAdded){
                        orders.add(vectorOrderAux);
                    }
                }catch (StringIndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Form> getForm(){
        List<Form> projectedObject = new ArrayList<>();
        Form aux;

        for (VectorOrder vo : orders){
            aux = new Form();
            for (Integer index : vo.order){
                aux.getVector3D().add(vectores.get(index));
            }
            projectedObject.add(aux);
        }

        return projectedObject;
    }

    private class VectorOrder{
        private final List<Integer> order = new ArrayList<>();
        private boolean isAdded = false;

        public void add (String value){
            order.add(Integer.parseInt(value) - 1);
        }

        @Override
        public String toString() {
            return "vectorOrder{" +
                    "order=" + order +
                    '}';
        }
    }
}
