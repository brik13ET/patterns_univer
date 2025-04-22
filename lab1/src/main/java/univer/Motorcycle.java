package univer;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Motorcycle {
    
    private Model head;

    @Getter
    @AllArgsConstructor
    public class Model
    {
        private Model next;

        @Setter
        private String name;
        @Setter
        private Double price;
    }

    private String mark;

    public Motorcycle(String mark)
    {
        this.mark = mark;
        head = new Model(null, "", Double.NaN);
    }

    public String[] getModelNames()
    {
        var ret = new String[0];
        for (Model m = head.next; m != null; m = m.getNext()) {
            ret = Arrays.copyOf(ret, ret.length + 1);
            ret[ret.length - 1] = m.getName();
        }
        return ret;
    }

    public void addModel(String name, Double price) {
        Model m = head.getNext();
        for (; m != null && m.getNext() != null;m = m.getNext());
        m.next = new Model(null, name, price);
    }
}
