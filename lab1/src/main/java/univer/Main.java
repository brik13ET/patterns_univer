package univer;

public class Main {
    public static void main(String[] args) {
        ConfigSingleton cs = ConfigSingleton.getInstanse();

        var props = cs.getProps();

        System.out.println("Props:");
        props.forEach(
            (p, v) -> {
                System.out.printf("\t%s: ", p);
                var colsplit = v.toString().split(",");
                if (colsplit.length > 1)
                {
                    System.out.printf("\n");
                    for (String vo : colsplit ) {
                        System.out.printf("\t\t%s\n", vo);
                    }
                }
                else
                    System.out.printf("%s\n", v);
            }
        );

        Automobile cars = new Automobile("asdf", 0);

        String v = props.getProperty("cars");

        for (String m : v.split(",")) {
            var kv = m.split(":");
            cars.addModel(kv[0], Double.valueOf(kv[1]));
        }
        System.out.println(cars);

        Motorcycle motos = new Motorcycle("asdf");
        
        String vm = props.getProperty("cars");

        for (String m : vm.split(",")) {
            var kv = m.split(":");
            motos.addModel(kv[0], Double.valueOf(kv[1]));
        }
        System.out.println(motos);
    }
}